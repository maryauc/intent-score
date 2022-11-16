package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    private static final  String TAG = MainActivity.class.getCanonicalName();
    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;

    private String hometeam;
    private String awayteam;
    private EditText round;
    private EditText homeTeamInput;
    private EditText awayTeamInput;
    private ImageView homeLogo;
    private ImageView awayLogo;
    private Button buttonTeam;
    private Uri homeImg;
    private Uri awayImg;
    private boolean change_img_home = false;
    private boolean change_img_away = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        round = findViewById(R.id.rondke);
        homeTeamInput = findViewById(R.id.home_team);
        awayTeamInput = findViewById(R.id.away_team);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        buttonTeam = findViewById(R.id.btn_team);

        homeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), HOME_REQUEST_CODE);
            }
        });

        awayLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AWAY_REQUEST_CODE);
            }
        });

        buttonTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sronde = round.getText().toString();
                hometeam = homeTeamInput.getText().toString();
                awayteam = awayTeamInput.getText().toString();
                if (hometeam.isEmpty()){
                    homeTeamInput.setError("Home Team is Empty!");
                } else if (awayteam.isEmpty()){
                    awayTeamInput.setError("Away Team is Empty");
                } else if (!change_img_home){
                    Toast.makeText(MainActivity.this, "Image " + hometeam + "Must be Changed!", Toast.LENGTH_SHORT).show();
                } else if (!change_img_away) {
                    Toast.makeText(MainActivity.this, "Image " + awayteam + "Must be Changed!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent (MainActivity.this, MatchActivity.class);
                    intent.putExtra("rondee", sronde);
                    intent.putExtra("home", hometeam);
                    intent.putExtra("away", awayteam);
                    intent.putExtra("homeImg", homeImg.toString());
                    intent.putExtra("awayImg", awayImg.toString());
                    startActivity(intent);
                }
            }
        });

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
          super.onActivityResult (requestCode, resultCode, data);
          if (resultCode == RESULT_CANCELED){
              Log.d(TAG, "Select Image is Camceled");
              return;
          } else if (requestCode == HOME_REQUEST_CODE){
              if (data !=null){
                  try{
                      change_img_home = true;
                      Uri imageUri = data.getData();
                      homeImg = imageUri;
                      Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                      homeLogo.setImageBitmap(bitmap);
                  }catch (IOException error){
                      Toast.makeText(this, "Image Could Not be Loaded", Toast.LENGTH_SHORT).show();
                      Log.e(TAG, error.getMessage());
                  }
              }
          } else if (requestCode == AWAY_REQUEST_CODE){
              if (data !=null){
                  try{
                      change_img_away = true;
                      Uri imageUri = data.getData();
                      awayImg = imageUri;
                      Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                      homeLogo.setImageBitmap(bitmap);
                  }catch (IOException error){
                      Toast.makeText(this, "Image Could Not be Loaded", Toast.LENGTH_SHORT).show();
                      Log.e(TAG, error.getMessage());
                  }
              }
          }
        }
    }
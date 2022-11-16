package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchActivity extends AppCompatActivity {

    private TextView ronde, homeScore, awayScore, homeName, awayName;
    private int scoreHome, scoreAway;
    private ImageView homeLogo, awayLogo;
    private Button check, add1team1, add2team1, add3team1, resettim1, add1team2, add2team2, add3team2, resettim2;
    private String winner, homeTeam, awayTeam, round;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        ronde = findViewById(R.id.rondke);
        homeScore = findViewById(R.id.skorteam1;
        awayScore = findViewById(R.id.skorteam2);
        homeName = findViewById(R.id.nmtv1);
        awayName = findViewById(R.id.nmtv2);
        homeLogo = findViewById(R.id.team1_logo);
        awayLogo = findViewById(R.id.team2_logo);
        check = findViewById(R.id.btn_cek);
        add1team1 = findViewById(R.id.tambah1);
        add2team1 = findViewById(R.id.tambah2);
        add3team1 = findViewById(R.id.tambah3);
        resettim1 = findViewById(R.id.reset);
        add1team2 = findViewById(R.id.tambah1_2);
        add2team2 = findViewById(R.id.tambah2_2);
        add3team2 = findViewById(R.id.tambah3_2);
        resettim2 = findViewById(R.id.reset2);

        scoreHome = 0;
        scoreAway = 0;

        homeScore.setText(String.valueOf(scoreHome));
        awayScore.setText(String.valueOf(scoreAway));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            if (bundle.getString("homeImg") != null && bundle.get("awayImg") != null){
                homeLogo.setImageURI(Uri.parse(bundle.getString("homeImg")));
                awayLogo.setImageURI(Uri.parse(bundle.getString("awayImg")));
            }
            round = bundle.getString("rondee");
            homeTeam = bundle.getString("home");
            awayTeam = bundle.getString("away");
            ronde.setText(round);
            homeScore.setText(homeTeam);
            awayScore.setText(awayTeam);
        }else {
            Toast.makeText(MatchActivity.this, "Intent is Missing", Toast.LENGTH_SHORT).show();
            return;
        }

        add1team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreHome += 1;
                homeScore.setText(String.valueOf(scoreHome));

            }
        });

        add2team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreHome += 2;
                homeScore.setText(String.valueOf(scoreHome));

            }
        });

        add3team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreHome += 3;
                homeScore.setText(String.valueOf(scoreHome));

            }
        });

        resettim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreHome = 0;
                homeScore.setText(String.valueOf(scoreHome));

            }
        });

        add1team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreAway += 1;
                awayScore.setText(String.valueOf(scoreAway));

            }
        });

        add2team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreAway += 2;
                awayScore.setText(String.valueOf(scoreAway));

            }
        });

        add3team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreAway += 3;
                awayScore.setText(String.valueOf(scoreAway));

            }
        });

        resettim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreAway = 0;
                awayScore.setText(String.valueOf(scoreAway));

            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winner = "empty";
                if (scoreHome > scoreAway){
                    winner = homeTeam;
                }else if (scoreHome == scoreAway){
                    winner = "Seri";
                }else{
                    winner = awayTeam;
                }

                Intent intent = new Intent (MatchActivity.this, ResultActivity.class);
                intent.putExtra("winner", winner);
                intent.putExtra("rondee", round);
            }
        });

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }
}

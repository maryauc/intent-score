package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView winronde, wintim;
    private ImageView btnhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        winronde = findViewById(R.id.winnerrond);
        wintim = findViewById(R.id.winnerteam);
        btnhome = findViewById(R.id.home);

        Bundle bundle = getIntent().getExtras();
        String swinronde = bundle.getString("rondee");
        winronde.setText("Round : " + swinronde);
        String swintim = bundle.getString("winner");
        wintim.setText(swintim);

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

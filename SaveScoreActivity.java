package info.shaneferrell.rps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SaveScoreActivity extends AppCompatActivity {

    int scores[];
    String names[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_score);
    }

    private void getScoresAndNames(byte buffer[]) {

        int i, scoreI = 0, nameI = 0;

        String str = new String(buffer);
        String strs[] = str.split(" ");

        for(i = 0; i < strs.length; i++){

            scores[scoreI] = Integer.parseInt(strs[i]);
            scoreI++;
            i++;

            names[nameI] = strs[i];
            nameI++;
        }
    }

    private void insertScore(int index, int score) {

        int i;

        for(i = 9; i < index; i--) scores[i] = scores[i - 1];
        scores[index] = score;
    }

    private void insertName(int index, String name){

        int i;

        for(i = 9; i < index; i--) names[i] = names[i - 1];
        names[index] = name;
    }

    public void submitScore(View v) {

        int i, newScore;
        String newName;

        FileInputStream in;
        FileOutputStream out;
        Bundle bundle = getIntent().getExtras();

        EditText editText = (EditText) findViewById(R.id.nameText);
        scores = new int[10];
        names = new String[10];

        // Set the new score
        if(bundle != null) newScore = bundle.getInt("score");
        else newScore = 0;

        // Set the new name
        newName = editText.getText().toString();
        newName = newName.replaceAll(" ", "");

        // Get scores/names
        try{

            byte buffer[] = new byte[1000];

            in = openFileInput("scores");
            if(in.read(buffer) == -1);

            getScoresAndNames(buffer);

            in.close();
        }
        catch(Exception e){ e.printStackTrace(); }

        // Insert new score/name
        for(i = 0; i < 10; i++){

            if(newScore >= scores[i]){

                insertScore(i, newScore);
                insertName(i, newName);

                break;
            }
        }

        // Save scores/names
        try{

            String scoreStr = "";

            out = openFileOutput("scores", Context.MODE_PRIVATE);

            for(i = 0; i < 10; i++){

                scoreStr += scores[i] + " " + names[i];
                if(i < 9) scoreStr += " ";
            }

            out.write(scoreStr.getBytes());

            out.close();
        }
        catch(Exception e){ e.printStackTrace(); }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

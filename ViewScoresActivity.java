package info.shaneferrell.rps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;
import java.io.FileInputStream;

public class ViewScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);

        // This was a test. Need to change in order to read and display scores.
        byte strB[];
        int i;

        try{

            FileInputStream fileIn = openFileInput("scores");

            strB = new byte[fileIn.available()];
            fileIn.read(strB);

            final TextView v = (TextView) findViewById(R.id.scoresText);

            // Get the str, then split it up.
            String str = new String(strB);
            String strs[] = str.split(" ");

            // Reconstruct str with spaces and newlines
            str = "";
            for(i = 0; i < strs.length; i++){

                if(strs[i] == null || strs[i].equals("0")) break;

                str += strs[i];

                if(i % 2 == 0) str += "  -  ";
                else str += "\n";
            }

            v.setText(str);

            fileIn.close();
        }
        catch(Exception e){ e.printStackTrace(); }

    }
}

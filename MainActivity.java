// Shane Ferrell
// 08/29/16

package info.shaneferrell.rps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String score, rounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button rockBut = (Button) findViewById(R.id.rockBut);
        final Button paperBut = (Button) findViewById(R.id.paperBut);
        final Button scissorsBut = (Button) findViewById(R.id.scissorsBut);

        final TextView scoreView = (TextView) findViewById(R.id.scoreText);
        final TextView roundView = (TextView) findViewById(R.id.roundText);
        final TextView statusView = (TextView) findViewById(R.id.statusText);

        final RPSGame game = new RPSGame();

        score = "" + game.getScore();
        rounds = "" + game.getRounds();

        if(scoreView != null && roundView != null) {

            scoreView.setText(score);
            roundView.setText(rounds);
        }

        // On Clicks for buttons

        // Rock
        if(rockBut != null) {
            rockBut.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    game.play(1);
                    score = "" + game.getScore();
                    rounds = "" + game.getRounds();

                    // Game goes on
                    if(scoreView != null && roundView != null) {

                        scoreView.setText(score);
                        roundView.setText(rounds);
                    }

                    // End of game
                    if(game.getRounds() <= 0 && scissorsBut != null && paperBut != null){

                        rockBut.setVisibility(View.GONE);
                        paperBut.setVisibility(View.GONE);
                        scissorsBut.setVisibility(View.GONE);

                        Intent intent = new Intent(v.getContext(), SaveScoreActivity.class);
                        Bundle bundle = new Bundle();

                        // Pass score to Save Score Activity
                        bundle.putInt("score", Integer.parseInt(score));
                        intent.putExtras(bundle);

                        // Start Save Score Activity
                        startActivity(intent);
                    }

                    if(statusView != null) statusView.setText(game.getStatus());
                }
            });
        }

        // Paper
        if(paperBut != null) {
            paperBut.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    game.play(2);
                    score = "" + game.getScore();
                    rounds = "" + game.getRounds();

                    // Game goes on
                    if(scoreView != null && roundView != null) {

                        scoreView.setText(score);
                        roundView.setText(rounds);
                    }

                    // End of game
                    if(game.getRounds() <= 0 && rockBut != null && scissorsBut != null){

                        rockBut.setVisibility(View.GONE);
                        paperBut.setVisibility(View.GONE);
                        scissorsBut.setVisibility(View.GONE);

                        Intent intent = new Intent(v.getContext(), SaveScoreActivity.class);
                        Bundle bundle = new Bundle();

                        // Pass score to Save Score Activity
                        bundle.putInt("score", Integer.parseInt(score));
                        intent.putExtras(bundle);

                        // Start Save Score Activity
                        startActivity(intent);
                    }

                    if(statusView != null) statusView.setText(game.getStatus());
                }
            });
        }

        // Scissors
        if(scissorsBut != null) {
            scissorsBut.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    game.play(3);
                    score = "" + game.getScore();
                    rounds = "" + game.getRounds();

                    // Game goes on
                    if(scoreView != null && roundView != null) {

                        scoreView.setText(score);
                        roundView.setText(rounds);
                    }

                    // End of game
                    if(game.getRounds() <= 0 && rockBut != null && paperBut != null){

                        rockBut.setVisibility(View.GONE);
                        paperBut.setVisibility(View.GONE);
                        scissorsBut.setVisibility(View.GONE);

                        Intent intent = new Intent(v.getContext(), SaveScoreActivity.class);
                        Bundle bundle = new Bundle();

                        // Pass score to Save Score Activity
                        bundle.putInt("score", Integer.parseInt(score));
                        intent.putExtras(bundle);

                        // Start Save Score Activity
                        startActivity(intent);
                    }

                    if(statusView != null) statusView.setText(game.getStatus());
                }
            });
        }
    }

    public void viewScores(View v) {

        Intent intent = new Intent(this, ViewScoresActivity.class);
        startActivity(intent);
    }
}
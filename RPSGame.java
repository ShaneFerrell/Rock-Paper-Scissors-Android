// Shane Ferrell
// 08/29/16

package info.shaneferrell.rps;

import java.util.Random;

public class RPSGame {

    private int score;
    private int rounds;
    private int playerChoice;
    private int computerChoice;
    private String status;

    public RPSGame(){

        score = 0;
        rounds = 10;
    }

    private void computerChoose(){

        Random rand = new Random();
        computerChoice = rand.nextInt(3) + 1;
    }

    public void play(int choice){

        if(rounds <= 0)return;

        computerChoose();
        playerChoice = choice;

        // Tie
        if(playerChoice == computerChoice){

            status = "Tie";
            score += 5;
        }

        // Rock
        else if(playerChoice == 1){

            // Paper
            if(computerChoice == 2) status =  "Loss. Computer chose paper";

            // Scissors
            else{

                status = "Win. Computer chose scissors";
                score += 10;
            }
        }

        // Paper
        else if(playerChoice == 2){

            // Scissors
            if(computerChoice == 3) status = "Loss. Computer chose scissors";

            // Rock
            else{

                status = "Win. Computer chose rock";
                score += 10;
            }
        }

        // Scissors
        else{

            // Rock
            if(computerChoice == 1) status = "Loss. Computer chose rock";

            // Paper
            else{

                status = "Win. Computer chose paper";
                score += 10;
            }
        }

        rounds--;
    }

    public int getScore(){ return score; }

    public int getRounds(){ return rounds; }

    public String getStatus(){ return status; }
}
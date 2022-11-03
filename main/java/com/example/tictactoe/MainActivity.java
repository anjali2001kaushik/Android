package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {
    boolean gameActive= true;
//    player representations:
    //0-X
    //1=o
    int activeplayer=0;
    int[] gamestate={2, 2, 2, 2, 2, 2, 2, 2, 2};
//    State Meanings:
//    0-X
//    1-O
//    2-null
    int[][] winPositions={{0, 1, 2}, {3, 4, 5 }, {6, 7, 8},
                         {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                         {0, 4, 8}, {2, 4, 6}};


    public void playerTap(View view){
        ImageView img=(ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if(!gameActive)
           gameReset(view);
        if(gamestate[tappedImage]==2){
            gamestate[tappedImage]= activeplayer;
            img.setTranslationY(-1000f);
            if(activeplayer==0) {
                img.setImageResource(R.drawable.x);
                activeplayer = 1;
                TextView status=findViewById(R.id.status);
                status.setText("O's Turn- Tap to play");
            }
            else {
                img.setImageResource(R.drawable.o);
                activeplayer = 0;
                TextView status=findViewById(R.id.status);
                status.setText("X's Turn- Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(30);
        }
        //check if any player has won
        for(int[] winposition: winPositions){
            if(gamestate[winposition[0]]==gamestate[winposition[1]] &&
                    gamestate[winposition[1]]==gamestate[winposition[2]] &&
                                  gamestate[winposition[0]]!=2){
                    //Somebody has won
                String winnerstr;
                gameActive=false;
                if(gamestate[winposition[0]]==0){
                    winnerstr="X has Won";
                }
                else {
                    winnerstr="O has Won";
                }
                // Update the Status bar wor winner Announcement
                TextView status=findViewById(R.id.status);
                status.setText(winnerstr);
            }
            // when game is draw
            boolean emptySquare = false;
            for (int squareState : gamestate) {
                if (squareState == 2) {
                    emptySquare = true;
                    break;
                }
            }
            if (!emptySquare && gameActive) {
                // Game is a draw
                gameActive = false;
                String winnerStr;
                winnerStr = "No one won";
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }


    }
    public void gameReset(View view){

        gameActive=true;
        activeplayer=0;
        for(int i=0;i<gamestate.length; i++){
        gamestate[i]=2;
        }

        ( (ImageView)findViewById(R.id.imageView12)).setImageResource(0);
        ( (ImageView)findViewById(R.id.imageView13)).setImageResource(0);
        ( (ImageView)findViewById(R.id.imageView14)).setImageResource(0);
        ( (ImageView)findViewById(R.id.imageView15)).setImageResource(0);
        ( (ImageView)findViewById(R.id.imageView16)).setImageResource(0);
        ( (ImageView)findViewById(R.id.imageView17)).setImageResource(0);
        ( (ImageView)findViewById(R.id.imageView18)).setImageResource(0);
        ( (ImageView)findViewById(R.id.imageView19)).setImageResource(0);
        ( (ImageView)findViewById(R.id.imageView20)).setImageResource(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
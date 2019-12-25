package com.gamedd.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//for game restart
    Boolean gameActive = true;

    //onClick Method here....

    //Player Representations
    //0 - X
    //1 - O(Not Zero it's O Alphabatic)
    int activePlayer = 0;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        //gameState Meanings:
        //0 - X
        //1 - O
        //2 - Null

    //2d Array Wins Positions
    int[][] winsPositions = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};

    public void playerTab(View view){

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        //if game not running
        if(!gameActive){
            gameReset(view);
        }

        if(gameState[tappedImage] == 2 && gameActive){
            gameState[tappedImage] = activePlayer;

            //Animation
            img.setTranslationY(-1000f);

            if(activePlayer == 0){
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to Play");
            }
            else{

            img.setImageResource(R.drawable.o);
            activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to Play");

            }

            //Animation
            img.animate().translationYBy(1000f).setDuration(300);

        }

            //Check if any Player has won
        for(int[] winPosition : winsPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] !=2){
                //Somebody has won!! Find out Who ?
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "X has Won!";

                }
                else{
                    winnerStr = "O has Won";

                }
                //Update the status bar to Announcement for Winner
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);


            }
        }
    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i = 0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        //for images reset when new game start
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to Play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

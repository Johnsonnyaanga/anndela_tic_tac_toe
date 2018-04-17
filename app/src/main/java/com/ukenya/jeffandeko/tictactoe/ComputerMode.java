/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ukenya.jeffandeko.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ComputerMode extends StyleActivity {
    int c[][];
    int i, j;
    Button b[][];
    TextView textView;
    ArtificialIntelligence ai;
    //Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setBoard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem item = menu.add("Reset"); return true;}

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intents = new Intent(ComputerMode.this, ComputerMode.class);
        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intents);
        setBoard();
        return true; }

    //Set up the game board.
    private void setBoard() {
        ai = new ArtificialIntelligence();
        b = new Button[4][4];
        c = new int[4][4];
        textView = (TextView) findViewById(R.id.dialogue);
        b[1][3] = (Button) findViewById(R.id.button_00);
        b[1][2] = (Button) findViewById(R.id.button_01);
        b[1][1] = (Button) findViewById(R.id.button_02);
        b[2][3] = (Button) findViewById(R.id.button_10);
        b[2][2] = (Button) findViewById(R.id.button_11);
        b[2][1] = (Button) findViewById(R.id.button_12);
        b[3][3] = (Button) findViewById(R.id.button_20);
        b[3][2] = (Button) findViewById(R.id.button_21);
        b[3][1] = (Button) findViewById(R.id.button_22);

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                c[i][j] = 2;
        }
        //textView to ready the player to start by clicking any button
        textView.setText("Click a button to start.");
// add the click listeners for each button
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if(!b[i][j].isEnabled()) {
                    b[i][j].setText(" ");
                    b[i][j].setEnabled(true);  }
            }
        }
    }
    class MyClickListener implements View.OnClickListener {
        int x;
        int y;
        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void onClick(View view) {
            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                b[x][y].setText("O");
                c[x][y] = 0;
                textView.setText("");
                if (!checkBoard()) {
                    ai.takeTurn();
                }
            }
        }
    } private class ArtificialIntelligence {
        public void takeTurn() {
            if(c[1][1]==2 &&((c[1][2]==0 && c[1][3]==0) || (c[2][2]  ==0 && c[3][3]==0) ||(c[2][1]==0 && c[3][1]==0))) { markSquare(1,1);
            } else if (c[1][2]==2 && ((c[2][2]==0 && c[3][2]==0) ||(c[1][1]==0 && c[1][3]==0))) { markSquare(1,2);
            } else if(c[1][3]==2 &&((c[1][1]==0 && c[1][2]==0) ||(c[3][1]==0 && c[2][2]==0) || (c[2][3] ==0 && c[3][3]==0))) { markSquare(1,3);
            } else if(c[2][1]==2 &&((c[2][2]==0 && c[2][3]==0) ||(c[1][1]==0 && c[3][1]==0))){markSquare(2,1);
            } else if(c[2][2]==2 &&((c[1][1]==0 && c[3][3]==0) ||(c[1][2]==0 && c[3][2]==0) ||(c[3][1]==0 && c[1][3]==0) ||(c[2][ 1]==0 && c[2][3]==0))) {markSquare(2,2);
            } else if(c[2][3]==2 &&((c[2][1]==0 && c[2][2]==0) ||(c[1][3]==0 && c[3][3]==0))) { markSquare(2,3);
            } else if(c[3][1]==2 &&((c[1][1]==0 && c[2][1]==0) ||(c[3][2] ==0 && c[3][3]==0) ||(c[2][2] ==0 && c[1][3]==0))){markSquare(3,1);
            } else if(c[3][2]==2 &&((c[1][2]==0 && c[2][2]==0) ||(c[3][1]==0 && c[3][3]==0))) {markSquare(3,2);
            }else if( c[3][3]==2 &&((c[1][1]==0 && c[2][2]==0) ||(c[1][3]==0 && c[2][3]==0) ||(c[3][1]==0 && c[3][2]==0))) { markSquare(3,3);
            } else {
                Random rand = new Random();
                int a = rand.nextInt(4);
                int b = rand.nextInt(4);
                while(a==0 || b==0 || c[a][b]!=2) {
                    a = rand.nextInt(4);
                    b = rand.nextInt(4);
                }markSquare(a,b);
            }
        }
        private void markSquare(int x, int y) {
            b[x][y].setEnabled(false);
            b[x][y].setText("X");
            c[x][y] = 1; checkBoard();
        }
    }
    //the code checks the players board to see if the player has won or the the computer
    private boolean checkBoard() {
        boolean gameOver = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0))
        {textView.setText("Game over. You win!");
            gameOver = true;
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            textView.setText("Game over. You lost!");
            gameOver = true;
        } else {
            boolean empty = false;
            for( i=1; i<=3; i++) {
                for(j=1; j<=3; j++) {
                    if(c[i][j]==2) { empty = true;
                        break;
                    }
                }
            }
            if(!empty) { gameOver = true;
                textView.setText("Game over. It's a draw!");
            }
        }return gameOver;
    }
 //Returns one to the home page
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            Intent intents = new Intent(ComputerMode.this, MainActivity.class);
            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intents);
            finish();
        } catch (Exception e) {
            e.printStackTrace();}
    }

}
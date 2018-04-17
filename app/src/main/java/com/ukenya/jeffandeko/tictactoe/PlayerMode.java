
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

public class PlayerMode extends StyleActivity {
    int  w[][];
    int i, j, k = 0;
    int z11=0,z12=0,z13=0,z21=0,z22=0,z23=0,z31=0,z32=0,z33=0;
    Button p[][];
    TextView textView;
    //Called when the activity is first created
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem item = menu.add("New Game"); return true;}
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intents = new Intent(PlayerMode.this, PlayerMode.class);
        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intents);
        setComputerBoard();
        return true; }
    //Set up the game board.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setComputerBoard();

    }

    public void setComputerBoard() {
        k = 0;
        p = new Button[4][4];
        w = new int[4][4];
        textView = findViewById(R.id.dialogue);
        p[1][3] = findViewById(R.id.button_00);
        p[1][2] = findViewById(R.id.button_01);
        p[1][1] = findViewById(R.id.button_02);
        p[2][3] = findViewById(R.id.button_10);
        p[2][2] = findViewById(R.id.button_11);
        p[2][1] = findViewById(R.id.button_12);
        p[3][3] = findViewById(R.id.button_20);
        p[3][2] = findViewById(R.id.button_21);
        p[3][1] = findViewById(R.id.button_22);
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                w[i][j] = 2;
        }
        //
        textView.setText(R.string.click_begin);
// add the click listeners for each button on the board
        p[1][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  if(z13==0) {
                if (k==1){ k=0; p[1][3].setText("O"); w[1][3] = 0; textView.setText("player 1 turn"); checkBoardvs();}
                else if (k==0) {k=1; p[1][3].setText("X"); w[1][3] = 1; textView.setText("player 2 turn"); checkBoardvs();} z13=1;  }    }
        });
        p[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { if(z12==0) {
                if (k==1){ k=0; p[1][2].setText("O"); w[1][2] = 0; textView.setText("player 1 turn"); checkBoardvs();}
                else if (k==0) {k=1; p[1][2].setText("X"); w[1][2] = 1; textView.setText("player 2 turn"); checkBoardvs();} z12=1;} }
        });
        p[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { if(z11==0) {
                if (k==1){ k=0; p[1][1].setText("O"); w[1][1] = 0; textView.setText("player 1 turn"); checkBoardvs();}
                else if (k==0) {k=1; p[1][1].setText("X"); w[1][1] = 1; textView.setText("player 2 turn"); checkBoardvs();} z11=1;} }
        });
        p[2][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { if(z23==0) {
                if (k==1){ k=0; p[2][3].setText("O"); w[2][3] = 0; textView.setText("player 1 turn"); checkBoardvs();}
                else if (k==0) {k=1; p[2][3].setText("X"); w[2][3] = 1; textView.setText("player 2 turn"); checkBoardvs();} z23=1;}}
        });
        p[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { if(z22==0) {
                if (k==1){ k=0; p[2][2].setText("O"); w[2][2] = 0; textView.setText("player 1 turn"); checkBoardvs();}
                else if (k==0) {k=1; p[2][2].setText("X"); w[2][2] = 1; textView.setText("player 2 turn"); checkBoardvs();} z22=1;}}
        });
        p[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { if(z21==0) {
                if (k==1){ k=0; p[2][1].setText("O"); w[2][1] = 0; textView.setText("player 1 turn"); checkBoardvs();}
                else if (k==0) {k=1; p[2][1].setText("X"); w[2][1] = 1; textView.setText("player 2 turn"); checkBoardvs();} z21=1;}}
        });
        p[3][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { if(z33==0) {
                if (k==1){ k=0; p[3][3].setText("O"); w[3][3] = 0; textView.setText("player 1 turn"); checkBoardvs();}
                else if (k==0) {k=1; p[3][3].setText("X"); w[3][3] = 1; textView.setText("player 2 turn"); checkBoardvs();} z33=1;}}
        });
        p[3][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { if(z32==0) {
                if (k==1){ k=0; p[3][2].setText("O"); w[3][2] = 0; textView.setText("player 1 turn"); checkBoardvs();}
                else if (k==0) {k=1; p[3][2].setText("X"); w[3][2] = 1; textView.setText("player 2 turn"); checkBoardvs();} z32=1;}}
        });
        p[3][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { if(z31==0) {
                if (k==1){ k=0; p[3][1].setText("O"); w[3][1] = 0; textView.setText("player 1 turn"); checkBoardvs();}
                else if (k==0) {k=1; p[3][1].setText("X"); w[3][1] = 1; textView.setText("player 2 turn"); checkBoardvs();} z31=1;}}
        });
    }
    public void checkBoardvs() {
        if ((w[1][1] == 0 && w[2][2] == 0 && w[3][3] == 0)
                || (w[1][3] == 0 && w[2][2] == 0 && w[3][1] == 0)
                || (w[1][2] == 0 && w[2][2] == 0 && w[3][2] == 0)
                || (w[1][3] == 0 && w[2][3] == 0 && w[3][3] == 0)
                || (w[1][1] == 0 && w[1][2] == 0 && w[1][3] == 0)
                || (w[2][1] == 0 && w[2][2] == 0 && w[2][3] == 0)
                || (w[3][1] == 0 && w[3][2] == 0 && w[3][3] == 0)
                || (w[1][1] == 0 && w[2][1] == 0 && w[3][1] == 0))
        {textView.setText("player 1 wins!");
        } else if ((w[1][1] == 1 && w[2][2] == 1 && w[3][3] == 1)
                || (w[1][3] == 1 && w[2][2] == 1 && w[3][1] == 1)
                || (w[1][2] == 1 && w[2][2] == 1 && w[3][2] == 1)
                || (w[1][3] == 1 && w[2][3] == 1 && w[3][3] == 1)
                || (w[1][1] == 1 && w[1][2] == 1 && w[1][3] == 1)
                || (w[2][1] == 1 && w[2][2] == 1 && w[2][3] == 1)
                || (w[3][1] == 1 && w[3][2] == 1 && w[3][3] == 1)
                || (w[1][1] == 1 && w[2][1] == 1 && w[3][1] == 1))
        {textView.setText("player 2 wins!");}

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            Intent intents = new Intent(PlayerMode.this, MainActivity.class);
            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intents);
            finish();
        } catch (Exception e) {
            e.printStackTrace();}
    }
}

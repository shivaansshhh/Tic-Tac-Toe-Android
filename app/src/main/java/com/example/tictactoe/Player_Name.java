package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Player_Name extends AppCompatActivity {

    private CardView single_player, multi_player, play_online;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);
        init();

//        start button for single player
        single_player.setOnClickListener(v -> singlePlayerDialog());


//        start button for multi player
        multi_player.setOnClickListener(v -> doublePlayerDialog());

//        start button for play online
        play_online.setOnClickListener(v -> Toast.makeText(Player_Name.this, "This feature is not available right now!", Toast.LENGTH_SHORT).show());

    }




//         function to show dialog box for single player
    private void singlePlayerDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.single_player_dialog);
        dialog.show();

//        initializing values
        EditText player1=dialog.findViewById(R.id.player1);
        Button btnStart=dialog.findViewById(R.id.btnStart);

//       intent for single player activity
        Intent iNext = new Intent(this, SinglePlayerActivity.class);

//        start button on dialog box
        btnStart.setOnClickListener(v -> {

//            to save players name in the variables
            String playerOne = player1.getText().toString();

//            to pass the names to the next activity
            iNext.putExtra( "playerOne", playerOne);

            startActivity(iNext);
        });
    }





    //    function to show custom dialog box for double player
    private void doublePlayerDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.double_player_dialog);
        dialog.show();

//        initializing values
       EditText player1=dialog.findViewById(R.id.player1);
       EditText player2=dialog.findViewById(R.id.player2);
       Button btnStart=dialog.findViewById(R.id.btnStart);

//       intent for double player activity
        Intent iNext = new Intent(this, DoublePlayerActivity.class);

//        start button on dialog box
        btnStart.setOnClickListener(v -> {

//            to save players name in the variables
            String playerOne = player1.getText().toString();
            String playerTwo = player2.getText().toString();

//            to pass the names to the next activity
            iNext.putExtra( "playerOne", playerOne);
            iNext.putExtra( "playerTwo", playerTwo);

            startActivity(iNext);
        });
    }

    private void init(){
        single_player=findViewById(R.id.single_player);
        multi_player=findViewById(R.id.multi_player);
        play_online = findViewById(R.id.play_online);
    }
}
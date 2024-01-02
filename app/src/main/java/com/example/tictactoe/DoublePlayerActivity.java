package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class DoublePlayerActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnRestart;
    private String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private TextView txtPlayer1, txtPlayer2, result;
    private int flag = 0;
    private int count = 0;
    private boolean gameEnded = false;
    private CountDownTimer restartTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        fetching players names from another activity
        Intent playerNames = getIntent();
        String playerOne = playerNames.getStringExtra("playerOne");
        String playerTwo = playerNames.getStringExtra("playerTwo");

//        Initialization
        init();


//        saving players names in variables
        txtPlayer1.setText(playerOne);
        txtPlayer2.setText(playerTwo);


//        button to restart
        btnRestart.setOnClickListener(v -> restart());
    }

    //     Initialization views
    private void init() {
        result = findViewById(R.id.result);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        txtPlayer1=findViewById(R.id.txtPlayer1);
        txtPlayer2=findViewById(R.id.txtPlayer2);
        btnRestart = findViewById(R.id.btnRestart);

    }


//    handle button click

    public void Check(View view) {
        if (!gameEnded) {
            Button btnCurrent = (Button) view;
            if (btnCurrent.getText().toString().isEmpty()) {
                count++;
                if (flag == 0) {
                    btnCurrent.setText("X");
                    result.setText(R.string.o_turn);
                    flag = 1;
                } else {
                    btnCurrent.setText("O");
                    result.setText(R.string.x_turn);
                    flag = 0;
                }

//            getting current button values

                if (count > 4) {
                    b1 = btn1.getText().toString();
                    b2 = btn2.getText().toString();
                    b3 = btn3.getText().toString();
                    b4 = btn4.getText().toString();
                    b5 = btn5.getText().toString();
                    b6 = btn6.getText().toString();
                    b7 = btn7.getText().toString();
                    b8 = btn8.getText().toString();
                    b9 = btn9.getText().toString();

//                check for winners

                    if (checkWinner()) {
                        String winner = flag == 0 ? txtPlayer2.getText().toString() : txtPlayer1.getText().toString();
                        showWinner(winner);
                        gameEnded = true;
                        restartWithDelay();
                    } else if (count == 9) {
                        result.setText(R.string.draw_message);
                        gameEnded = true;
                        restartWithDelay();
                    }
                }
            } else {
                Toast.makeText(this, "Not Allowed", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //                check for winner
    private boolean checkWinner(){
        return (b1.equals(b2) && b2.equals(b3) && !b1.isEmpty()) ||
                (b4.equals(b5) && b5.equals(b6) && !b4.isEmpty()) ||
                (b7.equals(b8) && b8.equals(b9) && !b7.isEmpty()) ||
                (b1.equals(b5) && b5.equals(b9) && !b1.isEmpty()) ||
                (b3.equals(b5) && b5.equals(b7) && !b3.isEmpty()) ||
                (b1.equals(b4) && b4.equals(b7) && !b1.isEmpty()) ||
                (b2.equals(b5) && b5.equals(b8) && !b2.isEmpty()) ||
                (b3.equals(b6) && b6.equals(b9) && !b3.isEmpty());
    }

    //                show winner
    private void showWinner(String winner) {
        String message = getString(R.string.winner_message, winner);
        result.setText(message);
    }


    // Restart the game with a delay
    private void restartWithDelay() {
        restartTimer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // No action needed
            }

            @Override
            public void onFinish() {
                restart();
            }
        }.start();
    }

    //     restart function
    public void restart (){
        if (gameEnded && restartTimer != null) {
            restartTimer.cancel();
            restartTimer = null;
        }
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        result.setText("");
        count=0;
        flag=0;
        gameEnded=false;

    }
}


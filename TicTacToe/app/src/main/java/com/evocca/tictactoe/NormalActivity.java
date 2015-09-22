package com.evocca.tictactoe;

/**
 * Created by mikaelaedmondstone on 20/09/2015.
 */
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Button;
import android.view.View.OnClickListener;

public class NormalActivity extends Activity implements OnClickListener {

    private GameBoard board = null;
    private int moveCount = 0, xloc = 0, yloc = 0;
    private String mark = "X", aiMark = "O";
    private boolean isOver = false;
    private AI ai  = null;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        backBtn = (Button) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(this);

        board = new GameBoard();
        ai = new AI(aiMark);
    }


    @Override
    public void onClick(View view) {

        Intent in = new Intent(NormalActivity.this, MenuActivity.class);
        startActivity(in);
    }


    //Action when reset is clicked which clears the screen and the virtual game board
    public void resetClick(View v) {
        clear();
        if (aiMark =="X") getAIMove(board);
    }

    //Action for when a cell is clicked. Determines which cell has been clicked
    public void cellClick(View v) {

        TextView cell = (TextView) findViewById(v.getId());
        String content = (String) cell.getText();
        if (content == "" && !isOver) {
            //Find the X Y location values of the particular cell that was clicked
            switch (cell.getId()) {
                case R.id.cell1:
                    xloc = 0; yloc = 0; break;
                case R.id.cell2:
                    xloc = 0; yloc = 1; break;
                case R.id.cell3:
                    xloc = 0; yloc = 2; break;
                case R.id.cell4:
                    xloc = 1; yloc = 0; break;
                case R.id.cell5:
                    xloc = 1; yloc = 1; break;
                case R.id.cell6:
                    xloc = 1; yloc = 2; break;
                case R.id.cell7:
                    xloc = 2; yloc = 0; break;
                case R.id.cell8:
                    xloc = 2; yloc = 1; break;
                case R.id.cell9:
                    xloc = 2; yloc = 2; break;
            }

            //Place the player's mark on the specific X Y location on both the virtual and displayed board
            board.placeMark(xloc, yloc, mark);
            cell.setText(mark);

            //Increment move Count because a move was just made
            moveCount++;

            //Check to see if the game is over
            isOver = checkEnd(mark);

            //if the game game is over get the AI's move
            if (!isOver)
                getAIMove(board);

        }
    }

    //Even for when the user changes between going first and going second
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_X:
                if (checked)
                    mark = "X"; aiMark = "O"; clear();
                break;
            case R.id.radio_O:
                if (checked)
                    mark = "O"; aiMark = "X"; clear(); getAIMove(board);
                break;
        }
    }

    //Checks to see if the game has ended provided with the last player to make a move
    private boolean checkEnd(String player) {
        //Checks the virtual board for a winner if there's a winner announce it with the provided player
        if (board.isWinner())
        {
            announce(true, player);
            return true;
        }
        //Check to see if we've reached our move total meaning it's a draw
        else if (moveCount >= 9)
        {
            announce(false, player);
            return true;
        }
        return false;
    }

    //Announce the winner, given a boolean for whether it was a win or a draw
    // and given the last player to make a mark
    private void announce(boolean endState, String player) {

        if (endState == true)
            player = player + " wins!";
        else
            player = "It's a draw!";

        //Get the application Context and setup the Toast notification with the end state info
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, player, Toast.LENGTH_LONG);
        toast.show();
    }

    //Clears the game Board
    private void clear() {
        //Get the id list of all the Textview cells
        int[] idList = { R.id.cell1, R.id.cell2, R.id.cell3, R.id.cell4,
                R.id.cell5, R.id.cell6, R.id.cell7, R.id.cell8, R.id.cell9 };
        TextView cell;
        for (int item : idList) {
            cell = (TextView) findViewById(item);
            cell.setText("");
        }
        //Reset the game state and clear the virtual board
        isOver = false;
        moveCount = 0;
        board.clear();
    }

    //Gets the AI's next move giving the current state of the board
    private void getAIMove(GameBoard board) {

        int[] move = ai.move(board,aiMark);
        TextView cell = null;
        switch (move[0]) {
            case 0:
                switch (move[1]) {
                    case 0:
                        cell = (TextView) findViewById(R.id.cell1);
                        break;
                    case 1:
                        cell = (TextView) findViewById(R.id.cell2);
                        break;
                    case 2:
                        cell = (TextView) findViewById(R.id.cell3);
                        break;
                }
                break;
            case 1:
                switch (move[1]) {
                    case 0:
                        cell = (TextView) findViewById(R.id.cell4);
                        break;
                    case 1:
                        cell = (TextView) findViewById(R.id.cell5);
                        break;
                    case 2:
                        cell = (TextView) findViewById(R.id.cell6);
                        break;
                }
                break;
            case 2:
                switch (move[1]) {
                    case 0:
                        cell = (TextView) findViewById(R.id.cell7);
                        break;
                    case 1:
                        cell = (TextView) findViewById(R.id.cell8);
                        break;
                    case 2:
                        cell = (TextView) findViewById(R.id.cell9);
                        break;
                }
                break;
        }


        if (cell != null && cell.getText() == "") {
            board.placeMark(move[0], move[1], aiMark);
            cell.setText(aiMark);
            moveCount++;
            isOver = checkEnd(aiMark);
        }
    }
}
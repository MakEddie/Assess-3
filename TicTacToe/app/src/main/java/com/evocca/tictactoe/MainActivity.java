package com.evocca.tictactoe;

/**
 * Created by mikaelaedmondstone on 20/09/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;



public class MainActivity extends Activity implements OnClickListener {

    Button playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = (Button) findViewById(R.id.playBtn);

        playBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent in = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(in);
    }
}
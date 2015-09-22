package com.evocca.tictactoe;

/**
 * Created by mikaelaedmondstone on 20/09/2015.
 */
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class About extends Activity implements View.OnClickListener {
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        backBtn = (Button) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent in = new Intent(About.this, MenuActivity.class);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}

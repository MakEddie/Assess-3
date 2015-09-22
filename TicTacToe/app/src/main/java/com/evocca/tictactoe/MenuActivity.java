package com.evocca.tictactoe;

/**
 * Created by mikaelaedmondstone on 20/09/2015.
 */
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void exit_click(View v)
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

        dlgAlert.setMessage("Do you really want to exit");
        dlgAlert.setTitle("Exit");

        dlgAlert.setCancelable(true);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                });
        dlgAlert.create().show();
    }
    public void about_click(View v)
    {
        Intent myIntent = new Intent(MenuActivity.this, About.class);
        MenuActivity.this.startActivity(myIntent);
    }
    public void normal_click(View v)
    {
        Intent myIntent = new Intent(MenuActivity.this,NormalActivity.class);
        MenuActivity.this.startActivity(myIntent);
    }
    public void hard_click(View v)
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

        dlgAlert.setMessage("Coming Soon");
        dlgAlert.setTitle("Access Denied");

        dlgAlert.setCancelable(true);

        dlgAlert.create().show();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


}



package com.hfad.soundmind;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickMyCalendarFAB(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }



    public void onClickDaily(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }


}

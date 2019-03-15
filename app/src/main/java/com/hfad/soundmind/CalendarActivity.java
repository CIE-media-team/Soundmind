package com.hfad.soundmind;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.List;

import io.objectbox.Box;


public class CalendarActivity extends AppCompatActivity {

    Box<Calendar> calendarBox;
    List<Calendar> calendarList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendarBox = ((App) getApplication()).getBoxStore().boxFor(Calendar.class);
        calendarList = calendarBox.getAll();
        addSpinnerMonths(calendarList);
    }
    public void onClickAddToDo(View view){ }

    public void addSpinnerMonths(List<Calendar> calendarList) {
        Spinner months = findViewById(R.id.month_select);
        ArrayAdapter<Calendar> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, calendarList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        months.setAdapter(adapter);
    }
}

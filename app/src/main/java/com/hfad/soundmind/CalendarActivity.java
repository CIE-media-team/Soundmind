package com.hfad.soundmind;


import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
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
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        calendarBox = ((App) getApplication()).getBoxStore().boxFor(Calendar.class);
        //calendarBox.removeAll();
        calendarList = calendarBox.getAll();

        int defaultYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        String defaultMonth = intToMonth(java.util.Calendar.getInstance().get(java.util.Calendar.MONTH));
        System.out.print(defaultMonth + defaultYear);

        addSpinnerMonths(calendarList, defaultMonth, defaultYear);
    }

    public void onClickAddToDo(View view) {
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new task")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        createTask(task);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    public void onClickEdit(View view){
        LinearLayout monthly_todo_container = findViewById(R.id.monthly_todo_list);

    }

    public void createTask(String task){
        LinearLayout monthly_todo_container = findViewById(R.id.monthly_todo_list);
        CheckBox newTask = new CheckBox(getApplicationContext());
        newTask.setText(task);
        monthly_todo_container.addView(newTask);


    }

    public void addSpinnerMonths(List<Calendar> calendarList, String month, int year) {
        Spinner months = findViewById(R.id.year_select);
        ArrayList<String> yearList = new ArrayList<String>();
        for (int each = 0; each < calendarList.size(); each++){
            String currentYear = Integer.toString(calendarList.get(each).getYear());
            if(!yearList.contains(currentYear)){
                yearList.add(currentYear);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, yearList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        months.setAdapter(adapter);
        String current = month + " " + year;
        months.setSelection(calendarList.indexOf(current));
    }

    public String intToMonth(int month) {
        String[] months = {"0", "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
        return months[month];
    }
}

package com.hfad.soundmind;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> taskArrayList = new ArrayList<>();
    private ListView lv;
    private CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home_todo:
                        break;
                    case R.id.navigation_calendar:
                        Intent a = new Intent(MainActivity.this, CalendarActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_guides:
                        Intent b = new Intent(MainActivity.this, ListActivity.class);
                        startActivity(b);
                        break;
                }
                return false;

            }
        });


        if (taskArrayList.size() == 0) {
            findViewById(R.id.empty).setVisibility(View.VISIBLE);
        } else {
            generateListView();
        }
    }




    public void onClickAddFAB(View view) {
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new task")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        taskArrayList.add(task);
                        generateListView();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();

    }

    public void generateListView(){
            lv = findViewById(R.id.listview);
            customAdapter = new CustomAdapter(this, taskArrayList);
            lv.setAdapter(customAdapter);

            final SwipeToDismissTouchListener<ListViewAdapter> touchListener =
                    new SwipeToDismissTouchListener<>(
                            new ListViewAdapter(lv),
                            new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
                                @Override
                                public boolean canDismiss(int position) {
                                    return true;
                                }

                                @Override
                                public void onDismiss(ListViewAdapter view, int position) {
                                    customAdapter.remove(position);
                                }
                            });

            lv.setOnTouchListener(touchListener);
            lv.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (touchListener.existPendingDismisses()) {
                        touchListener.undoPendingDismiss();
                    } else {
                        Toast.makeText(MainActivity.this, "Position " + position, LENGTH_SHORT).show();
                    }
                }
            });

        }

}


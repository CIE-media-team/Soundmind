package com.hfad.soundmind;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class App extends Application {

    private BoxStore boxStore;
    private ArrayList<String> months = new ArrayList<String>() {
        {
            add("JANUARY");
            add("FEBRUARY");
            add("MARCH");
            add("APRIL");
            add("MAY");
            add("JUNE");
            add("JULY");
            add("AUGUST");
            add("SEPTEMBER");
            add("OCTOBER");
            add("NOVEMBER");
            add("DECEMBER");
        }};

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the main data access object
        boxStore = MyObjectBox.builder().androidContext(App.this).build();

        // Get the wrapper (Box) for the Book table that lets us store Book objects
        Box<Calendar> calendarBox = boxStore.boxFor(Calendar.class);



        // Initialize with some data
        if (calendarBox.count() == 0) {
            int i = 2030;
            List<Calendar> initialCalendars = new ArrayList<>();
            while (i > 2018) {
                for (String month : months) {
                    initialCalendars.add(new Calendar(month, i, "", "", ""));
                }
                i--;
            }


            // ObjectBox is smart enough to handle CRUD on Collections of entities
            calendarBox.put(initialCalendars);

        }
    }
    public BoxStore getBoxStore() {
        return boxStore;
    }
}

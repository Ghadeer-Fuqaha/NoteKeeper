package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    public final static String NOTE_INFO = "com.example.notekeeper.NOTE_INFO";
    private NoteInfo mNote;
    private boolean mIsNewNote;

    //Activity: is a single,focused thing that the user can do.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //load up the layout resource called activity_note
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinnerCourses = findViewById(R.id.spinner_courses);

        //Populating the spinner
        //Spinner have two layouts:
        //1 --> one layout used to format the current selection
        //1 --> another layout used to format each of the available selections
        //Spinner Adapter: are responsible for doing the work of moving the data over
        // and each of these layouts

        //Create List contain Courses names
        List<CourseInfo> courses = DataManager.getInstance().getCourses();

        //then Create adapter to associate list with spinner
        ArrayAdapter<CourseInfo> adpaterCourses =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);

        adpaterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adpaterCourses);

        readDispalyStateValues();

        EditText textNoteTitle = findViewById(R.id.text_note_title);
        EditText textNoteText = findViewById(R.id.text_note_text);

        displayNote(spinnerCourses, textNoteTitle, textNoteText);
    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText) {
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        int courseIndex = courses.indexOf(mNote.getCourse());
        spinnerCourses.setSelection(courseIndex);
        textNoteTitle.setText(mNote.getTitle());
        textNoteText.setText(mNote.getText());
    }

    private void readDispalyStateValues() {
        Intent intent = getIntent();
        mNote = intent.getParcelableExtra(NOTE_INFO);

        mIsNewNote = mNote == null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

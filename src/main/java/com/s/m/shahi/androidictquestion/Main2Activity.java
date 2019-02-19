package com.s.m.shahi.androidictquestion;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.s.m.shahi.androidictquestion.Adapter.Adapter;
import com.s.m.shahi.androidictquestion.Model.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main2Activity extends AppCompatActivity {

    public static DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase, sqLiteDatabaseBin;
    private ListView listView;
    private Adapter adapter;
    private ArrayList<Model> noteArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.listView);

        noteArrayList = new ArrayList<>();

        adapter = new Adapter(Main2Activity.this, R.layout.row, noteArrayList);

        listView.setAdapter(adapter);

        Cursor cursor = databaseHelper.getDataInfo("SELECT * FROM note");
        noteArrayList.clear();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String Question = cursor.getString(1);
            String Correct = cursor.getString(2);
            String Option1 = cursor.getString(3);
            String Option2 = cursor.getString(4);
            String Option3 = cursor.getString(5);
            String Option4 = cursor.getString(6);
            String Undefine = cursor.getString(7);

            noteArrayList.add(new Model(Undefine, Question, null, Correct, Option1, Option2, Option3, Option4));
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.searchId);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter.getFilter().filter("");
                    listView.clearTextFilter();
                } else {
                    adapter.getFilter().filter(s);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            Intent intent = new Intent(Main2Activity.this, About.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

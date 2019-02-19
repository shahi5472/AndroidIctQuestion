package com.s.m.shahi.androidictquestion;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.s.m.shahi.androidictquestion.Adapter.RecyclerAdapter;
import com.s.m.shahi.androidictquestion.Model.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button offline;
    private RecyclerView recycle_user;
    private LinearLayoutManager layoutManager;

    private FirebaseDatabase database;
    private DatabaseReference contactList;

    private RecyclerAdapter recyclerAdapter;
    private List<Model> modelList = new ArrayList<>();

    private FirebaseRecyclerAdapter<Model, ViewHolder> adapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        contactList = database.getReference("Android_ict").child("Ict Question");

        offline = (Button) findViewById(R.id.offline);
        recycle_user = (RecyclerView) findViewById(R.id.recyclerView);
        recycle_user.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycle_user.setLayoutManager(layoutManager);

        contactList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    modelList.add(snapshot.getValue(Model.class));
                }

                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        recyclerAdapter = new RecyclerAdapter(modelList, this);
        recycle_user.setAdapter(recyclerAdapter);

        /*adapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(
                Model.class, R.layout.row, ViewHolder.class, contactList) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, final Model model, int position) {
                viewHolder.textViewQuestion.setText(model.getUndefined() + " Question : " + model.getQUESTION());
                viewHolder.textViewAnswer.setText("Answer : " + model.getCORRECT());

                viewHolder.textViewOPTION1.setText("Option A : " + model.getOPTION1());
                viewHolder.textViewOPTION2.setText("Option B : " + model.getOPTION2());
                viewHolder.textViewOPTION3.setText("Option C : " + model.getOPTION3());
                viewHolder.textViewOPTION4.setText("Option D : " + model.getOPTION4());

                boolean check = databaseHelper.check(model.getQUESTION());
                if (check) {

                } else {
                    databaseHelper.saveData(model.getQUESTION(), model.getCORRECT(), model.getOPTION1(), model.getOPTION2(), model.getOPTION3(), model.getOPTION4(), model.getUndefined());
                }

            }
        };
        recycle_user.setAdapter(adapter);*/

        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        if (Common.isConnectedToInternet(getBaseContext())) {

        } else {
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
        }

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
                    recyclerAdapter.getFilter().filter("");
                } else {
                    recyclerAdapter.getFilter().filter(s);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

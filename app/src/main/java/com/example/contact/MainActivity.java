package com.example.contact;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private ContactListAdapter mContactListAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<UserModel> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        mRecyclerView = (RecyclerView) findViewById(R.id.contact_screen_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL_LIST,12,12));

        View emptyView = findViewById(R.id.emptyView);

        contactList = new ArrayList<UserModel>();
        mContactListAdapter = new ContactListAdapter(MainActivity.this,emptyView,contactList);
        mRecyclerView.setAdapter(mContactListAdapter);

        new ContactAsyncTask(this,mContactListAdapter).execute();
        mContactListAdapter.updateList(contactList);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
           finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

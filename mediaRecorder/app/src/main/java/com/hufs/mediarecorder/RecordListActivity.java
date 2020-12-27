package com.hufs.mediarecorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecordListActivity  extends AppCompatActivity {

    ListView myListView;
    ArrayAdapter mAdapter;

    String path = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorderlist);

        path = getExternalCacheDir().getAbsolutePath();
        File directory = new File(path);
        File[] files = directory.listFiles();

        final List<String> filesNameList = new ArrayList<>();
        for(File f : files){
            filesNameList.add(f.getName());
        }

        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, filesNameList);
        myListView = findViewById(R.id.listView);
        myListView.setAdapter(mAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("filename", filesNameList.get(position));
                Intent intent = new Intent(getApplicationContext(), PlayRecordActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

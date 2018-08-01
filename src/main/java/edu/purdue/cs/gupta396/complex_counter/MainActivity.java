package edu.purdue.cs.gupta396.complex_counter;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button addNewButton;
    private Button autoButton;
    private ListView counterList;
    private ArrayList<String> counters = new ArrayList<>();
    private ArrayAdapter adapter;
    private final static int initialCount = 0;
    private final static int SELECTOR_CONST_ADDNEW = 1;
    private final static int SELECTOR_CONST_COUNTER = 2;
    private int currentItemIndex = 0;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        if(settings != null){
            Map map = settings.getAll();
            if(map != null){
                for(int i = 0; i < map.size(); i++){
                    counters.add(map.get(Integer.toString(i)).toString());
                }
            }
       }
        if(counters.size() == 0){
            counters.add("New Counter: " + initialCount);
        }
        counterList = findViewById(R.id.counterList);
        adapter = new ArrayAdapter<>(this, R.layout.counterlistview, counters);
        counterList = findViewById(R.id.counterList);
        counterList.setAdapter(adapter);
        counterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentItemIndex = (int)l;
                Intent myIntent = new Intent(MainActivity.this, Counter.class);
                String[] values = counters.get(currentItemIndex).split(":");
                myIntent.putExtra("CurrentCount", values[1].trim());
                startActivityForResult(myIntent, SELECTOR_CONST_COUNTER);
            }
        });
    }
    @Override
    protected void onStop(){
        SharedPreferences.Editor edit = settings.edit();
        edit.clear();
        for(int i = 0; i < counters.size(); i++){
            edit.putString(Integer.toString(i),counters.get(i));
        }
        edit.apply();
        super.onStop();
    }
    public void onAddNew(View view){
        Intent myIntent = new Intent(MainActivity.this, AddNew.class);
        startActivityForResult(myIntent, SELECTOR_CONST_ADDNEW);
    }

    public void onAuto(View view){
        for(int i = 0; i < counters.size(); i++){
            String[] values = counters.get(i).split(":");
            int incrementCount = Integer.parseInt(values[1].trim());
            incrementCount++;
            counters.set(i, values[0] + ": " + incrementCount);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case SELECTOR_CONST_ADDNEW:
                if(resultCode == RESULT_OK){
                    counters.add(data.getExtras().getString("NewCounterName") + ": " + initialCount);
                    adapter.notifyDataSetChanged();
                }
                break;
            case SELECTOR_CONST_COUNTER:
                if(resultCode == RESULT_OK){
                    String[] values = counters.get(currentItemIndex).split(":");
                    counters.set(currentItemIndex, values[0] + ": " + data.getExtras().getString("NewCount") );
                    adapter.notifyDataSetChanged();
                }
        }
    }
}

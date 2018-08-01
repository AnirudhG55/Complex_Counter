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

public class AddNew extends AppCompatActivity{
    private EditText counterName;
    private Button setButton;
    Intent newCounterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew);
        counterName = findViewById(R.id.counterName);
        setButton = findViewById(R.id.setButton);
        newCounterName = new Intent();
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String counterNameInput = counterName.getText().toString();
                newCounterName.putExtra("NewCounterName", counterNameInput);
                setResult(RESULT_OK, newCounterName);
                finish();
            }
        });
    }
}

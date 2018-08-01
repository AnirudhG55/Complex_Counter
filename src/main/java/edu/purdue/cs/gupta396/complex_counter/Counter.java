package edu.purdue.cs.gupta396.complex_counter;

/**
 * Created by anirudhgupta on 4/11/18.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class Counter extends AppCompatActivity{
    private TextView countView;
    private Button doneButton;
    private Button countButton;
    private Intent newCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter);
        countButton = findViewById(R.id.countButton);
        doneButton = findViewById(R.id.doneButton);
        countView = findViewById(R.id.countView);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String currentCounterValue = extras.getString("CurrentCount");
            countView.setText(currentCounterValue);
        }
        newCount = new Intent();
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String counterInput = countView.getText().toString();
                newCount.putExtra("NewCount", counterInput);
                setResult(RESULT_OK, newCount);
                finish();
            }
        });
    }
    public void onCount(View view){
        String counterInput = countView.getText().toString();
        int input = Integer.parseInt(counterInput);
        input++;
        String stringInput = Integer.toString(input);
        countView.setText(stringInput);
    }
}
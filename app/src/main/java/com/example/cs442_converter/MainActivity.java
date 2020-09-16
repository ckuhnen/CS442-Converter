package com.example.cs442_converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView from;
    private TextView to;
    private EditText input;
    private TextView output;
    private TextView history;
    double conversion = 1.60934;
    String inShort = " Mi";
    String outShort = " Km";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        history = findViewById(R.id.history);
        history.setMovementMethod(new ScrollingMovementMethod());

    }

    public void radioClicked(View v){
        switch(v.getId()) {
            case R.id.milesKilos:
                from.setText(R.string.m_input);
                to.setText(R.string.k_input);
                conversion = 1.60934;
                inShort = " Mi";
                outShort = " Km";
                break;
            case R.id.kilosMiles:
                from.setText(R.string.k_input);
                to.setText(R.string.m_input);
                conversion = 0.621371;
                inShort = " Km";
                outShort = " Mi";
                break;
        }
    }

    public void convertButton(View v) {
        if (input.getText().toString().equals("")) {
            input.setError("Please enter a distance");
        }
        else {
            double in = Double.valueOf(input.getText().toString());
            double out = in * conversion;
            String formatOut = String.format("%.1f", out);
            output.setText(formatOut);

            String historyText = history.getText().toString();
            String converted = input.getText().toString() + inShort + " ➔ " + formatOut + " " + outShort;
            history.setText(String.format("%s\n%s", converted, historyText));
            input.setText("");
        }
    }

    public void clear(View v) {
        history.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("HISTORY", history.getText().toString());
        outState.putString("CONVERTED", output.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        history.setText(savedInstanceState.getString("HISTORY"));
        output.setText(savedInstanceState.getString("CONVERTED"));
    }
}
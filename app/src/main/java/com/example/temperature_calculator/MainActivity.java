package com.example.temperature_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_value;
    RadioButton btn_celcius;
    RadioButton btn_fahrenhite;
    Button btn_calculate;
    TextView display_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_value = findViewById(R.id.et_value);
        btn_celcius = findViewById(R.id.rb_one);
        btn_fahrenhite = findViewById(R.id.rb_two);
        btn_calculate = findViewById(R.id.btn_cal);
        display_value = findViewById(R.id.tv_displayAnswer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAnswer();
            }
        });
    }

    private void calculateAnswer() {
        Calculations calculations = new Calculations();
        String temp_value = et_value.getText().toString();
        if(TextUtils.isEmpty(temp_value)){
            Toast.makeText(this, "Please add a value", Toast.LENGTH_LONG).show();
            temp_value = "0.0";
            display_value.setText(temp_value);
        }
        else {
            try {
                Float temp = Float.parseFloat(temp_value);
                if (btn_celcius.isChecked()) {
                    temp = calculations.convertCelciusToFahrenheit(temp);
                } else if (btn_fahrenhite.isChecked()) {
                    temp = calculations.convertFahrenheitToCelcius(temp);
                } else {
                    Toast.makeText(this, "Please Radio!", Toast.LENGTH_LONG).show();

                }
                display_value.setText(new Float(temp).toString());
            } catch (NullPointerException e) {
            }

        }
    }
}
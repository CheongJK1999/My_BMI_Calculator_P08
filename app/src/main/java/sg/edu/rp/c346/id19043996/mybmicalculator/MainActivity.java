package sg.edu.rp.c346.id19043996.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight;
    TextView tvOP1, tvOP2, tvOP3;
    Button btnCal, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My BMI Calculator");

        etWeight = findViewById(R.id.editTextW);
        etHeight = findViewById(R.id.editTextH);
        btnCal = findViewById(R.id.buttonCalculate);
        btnReset = findViewById(R.id.buttonReset);
        tvOP1 = findViewById(R.id.textViewOP1);
        tvOP2 = findViewById(R.id.textViewOP2);
        tvOP3 = findViewById(R.id.textViewOP3);

        btnCal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String strWeight = etWeight.getText().toString();
                String strHeight = etHeight.getText().toString();
                float weight = Float.parseFloat(etWeight.getText().toString());
                float height = Float.parseFloat(etHeight.getText().toString());
                float tbmi = weight / (height * height);

                String DateandTime = java.text.DateFormat.getDateTimeInstance().format(new Date());

                if ( tbmi == 0.0) {
                    tvOP3.setText("");
                } else if(tbmi < 18.5){
                    tvOP3.setText("You are underweight");
                } else if(tbmi > 18.5){
                    tvOP3.setText("You BMI is normal");
                } else if(tbmi > 25){
                    tvOP3.setText("You are overweight");
                } else if(tbmi >30){
                    tvOP3.setText("You are Obese");
                }

                tvOP1.setText(DateandTime);
                tvOP2.setText(String.format("%.3f, bmi"));

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tvOP1.setText("");
                tvOP2.setText("");
                tvOP3.setText("");

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        String DateandTime = tvOP1.getText().toString();
        float tbmi = Float.parseFloat(tvOP2.getText().toString());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editbmi = prefs.edit();

        editbmi.putString("DAT", DateandTime);
        editbmi.putFloat("bmi", tbmi);


        editbmi.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String DateandTime = prefs.getString("DAT", "");
        float tbmi = prefs.getFloat("tbmi", 0);


        tvOP1.setText(DateandTime);
        tvOP2.setText(tbmi + "");

    }

}

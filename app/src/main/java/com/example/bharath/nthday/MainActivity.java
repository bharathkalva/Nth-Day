package com.example.bharath.nthday;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected static TextView displayCurrentTime;
    ImageButton Calender1;
    ImageButton Calender2;
    TextView Date1;
    Button Calculate,Clear;
    int Days;
    EditText NoDays;
    int Date=1,Month=1,Year=2000;
    int[] Months = {31,28,31,30,31,30,31,31,30,31,30,31};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calender1 = (ImageButton)findViewById(R.id.IbCalender);
        Date1 = (TextView)findViewById(R.id.tvDate1);
        Calculate = (Button)findViewById(R.id.btCalculate);
        Clear = (Button)findViewById(R.id.btClear);
        NoDays = (EditText)findViewById(R.id.eTDate2);
        assert Calender1 != null;
        assert Calender2 != null;
        Calender1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment mDatePicker = new DatePickerFragment();
                mDatePicker.show(getSupportFragmentManager(), "Select date");
            }
        });
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Days = Integer.parseInt(NoDays.getText().toString());
                setTheDate();
                Date1.setText(Date+"-"+Month+"-"+Year);
            }
        });
    }
    private void setTheDate() {
        while(Days!=0){
            int MonthDays = Months[Month-1];
            if((Year%4==0)&&(Month==2)) MonthDays +=1;
            if(Days+Date<=MonthDays) {
                Date += Days;
                Days =0;
            } else {
                Days -= (MonthDays+1- Date);
                Date =1;
                Month +=1;
                if(Month>12) {
                    Year += 1;
                    Month = Month%12;
                }
                   }
        }
    }
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
           // displayCurrentTime.setText("Selected date: " + String.valueOf(year) + " - " + String.valueOf(month) + " - " + String.valueOf(day));
        }
    }
}

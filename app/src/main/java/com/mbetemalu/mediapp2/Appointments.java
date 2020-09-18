package com.mbetemalu.mediapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Appointments extends AppCompatActivity {

    private EditText doctor, app_time, app_date, app_description;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_appointments);

        doctor =findViewById(R.id.doc_type);
        app_date = findViewById(R.id.app_date);
        app_time = findViewById(R.id.app_time);
        app_description = findViewById(R.id.app_description);

        findViewById(R.id.book_appointment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateDoc() | !validateDate() | !validateTime() | !validateDescription()){
                    return;
                }

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Appointments");

                String doc_data = doctor.getText().toString().trim();
                String doc_date = app_date.getText().toString().trim();
                String doc_time = app_time.getText().toString().trim();
                String doc_desc = app_description.getText().toString().trim();

                AppointmentHelper appointmentHelper = new AppointmentHelper(doc_data, doc_date,doc_time,doc_desc);
                reference.child(doc_date).setValue(appointmentHelper);

                Toast.makeText(Appointments.this, "Appointment Added Successfully",Toast.LENGTH_SHORT).show();
            }
        });

        app_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                final int year = c.get(Calendar.YEAR);
                final int month = c.get(Calendar.MONTH);
                final int day = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(Appointments.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yearDay, int monthDay, int dayOfDay) {
                        app_date.setText(dayOfDay + "-" + (monthDay + 1)  + "-" +yearDay);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        app_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar time = Calendar.getInstance();
                int hour = time.get(Calendar.HOUR_OF_DAY);
                int min = time.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Appointments.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourDay, int minDay) {
                        app_time.setText(hourDay + ":" + minDay);
                    }
                },hour, min, true);
                timePickerDialog.show();
            }
        });
    }

    private Boolean validateDoc(){
        String val = doctor.getText().toString();

        if (val.isEmpty()) {
            doctor.setError("Field cannot be empty");
            return false;
        } else{
            doctor.setError(null);
            return true;
        }
    }

    private Boolean validateDate(){
        String val = app_date.getText().toString();

        if (val.isEmpty()) {
            app_date.setError("Field cannot be empty");
            return false;
        } else{
            app_date.setError(null);
            return true;
        }
    }

    private Boolean validateTime(){
        String val = app_time.getText().toString();

        if (val.isEmpty()) {
            app_time.setError("Field cannot be empty");
            return false;
        } else{
            app_time.setError(null);
            return true;
        }
    }

    private Boolean validateDescription(){
        String val = app_description.getText().toString();

        if (val.isEmpty()) {
            app_description.setError("Field cannot be empty");
            return false;
        } else{
            app_description.setError(null);
            return true;
        }
    }

}

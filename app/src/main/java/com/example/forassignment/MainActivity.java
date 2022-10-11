package com.example.forassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.forassignment.sqlite.DBHelper;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.close();

        radioGroup = findViewById(R.id.selectChoice);

        Button saveButton = findViewById(R.id.buttonCheck);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                EditText nameTrip = findViewById(R.id.inputName);
                EditText inputDescription = findViewById(R.id.inputDescription);
                EditText inputDestination = findViewById(R.id.inputDestination);
                TextView inputDate = findViewById(R.id.inputDate);
                RadioButton rChoice = findViewById(radioId);

                boolean check = validateinfo(nameTrip, inputDestination, inputDate,radioId);
                if(check= true){
                    Toast.makeText(getApplicationContext(), "Data is valid", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Sorry, check the information again", Toast.LENGTH_SHORT).show();
                }
                getInputs();
            }
        });
    }
    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected: "+ radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
    public void showDatePickerDialog(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void updateDate(LocalDate dob){
        TextView dobText = (TextView) findViewById(R.id.inputDate);
        dobText.setText(dob.toString());
    }


    private void getInputs(){
        int radioId = radioGroup.getCheckedRadioButtonId();
        EditText nameTrip = findViewById(R.id.inputName);
        EditText inputDescription = findViewById(R.id.inputDescription);
        EditText inputDestination = findViewById(R.id.inputDestination);
        TextView inputDate = findViewById(R.id.inputDate);
        RadioButton rChoice = findViewById(radioId);

        String strNameTrip = nameTrip.getText().toString();
        String strInputDate = inputDate.getText().toString();
        String strDescription = inputDescription.getText().toString();
        String strInputChoice = rChoice.getText().toString();
        String strDestination = inputDestination.getText().toString();
        displayNextAlert(strNameTrip,strInputDate,strInputChoice, strDescription, strDestination);

    }

    private Boolean validateinfo(EditText nameTrip, EditText inputDestination, TextView inputDate, int radioId) {
        if(nameTrip.length()==0){
            nameTrip.requestFocus();
            nameTrip.setError("Field cannot be empty");
            return false;
        }else if(inputDestination.length()==0){
            inputDestination.requestFocus();
            inputDestination.setError("Field cannot be empty");
            return false;
        }else if(inputDate.length()==0){
            inputDate.requestFocus();
            inputDate.setError("Field cannot be empty");
            return false;
        }else{
            return true;
        }
    }

    private void displayNextAlert(String strNameTrip,String strInputDate,String strInputChoice, String strDescription, String strDestination) {
        new AlertDialog.Builder(this).setTitle("Details Trip").setMessage("" +
                "\n Name trip:" + strNameTrip +
                "\n Description: " + strDestination +
                "\n Date: " + strInputDate +
                "\n Risk assesments: " + strInputChoice +
                "\n Destination: " + strDescription


        ).setNeutralButton("Back to Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }
}
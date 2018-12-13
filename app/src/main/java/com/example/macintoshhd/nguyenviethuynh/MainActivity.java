package com.example.macintoshhd.nguyenviethuynh;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mTxtId, mTxtPassword;
    private Button mBtnSave, mBtnReset, mBtnListStudents;
    private Spinner sMajor;
    private RadioButton rBtnGender, rMale, rFemale;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtId = findViewById(R.id.txtId);
        mTxtPassword = findViewById(R.id.txtPassword);
        mBtnSave = findViewById(R.id.btnSave);
        mBtnReset = findViewById(R.id.btnReset);
        mBtnListStudents = findViewById(R.id.btnList);
        sMajor = findViewById(R.id.spinnerMajor);

        rMale = findViewById(R.id.rbtnMale);
        rFemale = findViewById(R.id.rbtnFemale);

        mBtnSave.setOnClickListener(this);
        mBtnReset.setOnClickListener(this);
        mBtnListStudents.setOnClickListener(this);

        addItemsSpinner();


        rBtnGender = (RadioButton) findViewById(R.id.rGender);

        rBtnGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rMale){
                    Toast.makeText(getApplicationContext(), "Male",Toast.LENGTH_SHORT).show();

                } else if (checkedId == rFemale){
                    Toast.makeText(getApplicationContext(), "Female",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                save();
                break;
            case R.id.btnReset:
                if(v == mBtnReset){
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
                break;
            case R.id.btnList:
                listStudents();
                break;
        }
    }

    public void save() {

        String fileName = "nguyenviethuynh.txt";
        try (FileOutputStream outputStream = openFileOutput(fileName, MODE_PRIVATE)) {
            String studentId = mTxtId.getText().toString();
            String password = mTxtPassword.getText().toString();


            outputStream.write(studentId.getBytes());
            outputStream.write(password.getBytes());

            Toast.makeText(this, "New File has been saved", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(this, "Error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("TAG", "save" + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public void listStudents(){

    }


    public void addItemsSpinner(){
        sMajor = (Spinner) findViewById(R.id.spinnerMajor);
        List<String> list = new ArrayList<>();
        list.add("Android");
        list.add("IOS");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sMajor.setAdapter(dataAdapter);
    }
}

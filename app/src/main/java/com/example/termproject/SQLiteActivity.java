package com.example.termproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SQLiteActivity extends AppCompatActivity {

    Button btnAdd, btnDelete, btnUpdate, btnView, btnViewAll;
    DatabaseHelper dbHandler;
    EditText txtID, txtFName, txtFatherName, txtLName, txtMajor, txtDOB, txtGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        dbHandler = new DatabaseHelper(this);

        btnAdd=(Button) findViewById(R.id.btnAdd_FB);
        btnDelete=(Button) findViewById(R.id.btnDelete_FB);
        btnUpdate=(Button) findViewById(R.id.btnUpdate_FB);
        btnView= (Button) findViewById(R.id.btnView_FB);
        btnViewAll =(Button) findViewById(R.id.btnViewAll_FB);
        txtID = (EditText) findViewById(R.id.txtID_FB);
        txtFName = (EditText) findViewById(R.id.txtFName_FB);
        txtFatherName = (EditText) findViewById(R.id.txtFatherName_FB);
        txtLName = (EditText) findViewById(R.id.txtLName_FB);
        txtMajor = (EditText) findViewById(R.id.txtMajor_FB);
        txtDOB = (EditText) findViewById(R.id.txtDOB_FB);
        txtGender = (EditText) findViewById(R.id.txtGender_FB);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = txtID.getText().toString();
                String FName = txtFName.getText().toString();
                String FatherName = txtFatherName.getText().toString();
                String LName = txtLName.getText().toString();
                String Major = txtMajor.getText().toString();
                String DOB = txtDOB.getText().toString();
                String Gender = txtGender.getText().toString();

                dbHandler.addStudent(ID,FName,FatherName,LName,Major,DOB,Gender);
                Toast.makeText(SQLiteActivity.this, "Student added successfully.", Toast.LENGTH_LONG).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = txtID.getText().toString();

                dbHandler.deleteStudent(ID);
                Toast.makeText(SQLiteActivity.this,"Student with ID " + ID + " removed successfully",Toast.LENGTH_LONG).show();

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = txtID.getText().toString();
                Cursor cursor = dbHandler.viewStudent(ID);
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("ID: "+ cursor.getString(0) +"\n" );
                    buffer.append("FIRST NAME: "+ cursor.getString(1) +"\n" );
                    buffer.append("FATHER'S NAME: "+ cursor.getString(2) +"\n" );
                    buffer.append("LAST NAME: "+ cursor.getString(3) +"\n" );
                    buffer.append("MAJOR: "+ cursor.getString(4) +"\n" );
                    buffer.append("DOB: "+ cursor.getString(5) +"\n" );
                    buffer.append("GENDER: "+ cursor.getString(6) +"\n\n" );
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(SQLiteActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbHandler.viewAllStudents();
                StringBuffer buffer=new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("ID: "+ cursor.getString(0) +"\n" );
                    buffer.append("NAME: "+ cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) +"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(SQLiteActivity.this);
                builder.setCancelable(true);
                builder.setTitle("All students");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = txtID.getText().toString();
                String FName = txtFName.getText().toString();
                String FatherName = txtFatherName.getText().toString();
                String LName = txtLName.getText().toString();
                String Major = txtMajor.getText().toString();
                String DOB = txtDOB.getText().toString();
                String Gender = txtGender.getText().toString();

                dbHandler.updateStudent(ID, FName, FatherName, LName, Major, DOB, Gender);
                Toast.makeText(SQLiteActivity.this,"Student with ID " + ID + " record updated successfully",Toast.LENGTH_LONG).show();

            }
        });
    }
}
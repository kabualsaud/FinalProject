package com.example.termproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Alfaisal.db";
    public static final String TABLE_NAME="StudentRecord";
    public static final String STUDENT_NATIONAL_ID="ID";
    public static final String STUDENT_F_NAME="FIRSTNAME";
    public static final String STUDENT_FATHER_NAME="FATHERNAME";
    public static final String STUDENT_L_NAME="LASTNAME";
    public static final String STUDENT_MAJOR="MAJOR";
    public static final String STUDENT_DOB="DOB";
    public static final String STUDENT_GENDER="GENDER";


    public DatabaseHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL(
                "CREATE TABLE "+TABLE_NAME +"("
                        + STUDENT_NATIONAL_ID + " INTEGER PRIMARY KEY, "
                        + STUDENT_F_NAME + " TEXT NOT NULL,"
                        + STUDENT_FATHER_NAME + " TEXT NOT NULL,"
                        + STUDENT_L_NAME + " TEXT NOT NULL,"
                        + STUDENT_MAJOR + " TEXT NOT NULL,"
                        + STUDENT_DOB + " TEXT NOT NULL,"
                        + STUDENT_GENDER + " TEXT NOT NULL)");
    }

    public void addStudent(String ID,String FNAME,String FATHERNAME, String LNAME,String MAJOR,String DOB,String GENDER){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(STUDENT_NATIONAL_ID,ID);
        values.put(STUDENT_F_NAME,FNAME);
        values.put(STUDENT_FATHER_NAME,FATHERNAME);
        values.put(STUDENT_L_NAME,LNAME);
        values.put(STUDENT_MAJOR,MAJOR);
        values.put(STUDENT_DOB,DOB);
        values.put(STUDENT_GENDER,GENDER);

        myDB.insert(TABLE_NAME,null,values);
    }
    public Cursor viewAllStudents(){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor x=myDB.rawQuery("SELECT * FROM  "+TABLE_NAME,null);
        return x;
    }
    public Cursor viewStudent(String ID){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor x=myDB.rawQuery("SELECT * FROM  "+TABLE_NAME + " WHERE " +
                STUDENT_NATIONAL_ID + " = "+"'"+ID+"'",null);
        return x;
    }
    public Integer deleteStudent(String ID){
        SQLiteDatabase myDB=this.getWritableDatabase();
        return myDB.delete(TABLE_NAME,"ID=?", new String []{ID});

    }

    public boolean updateStudent(String ID,String FNAME,String FATHERNAME, String LNAME,String MAJOR,String DOB,String GENDER) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+ TABLE_NAME + " SET " + STUDENT_F_NAME + " = "+"'"+FNAME +"' "+ "WHERE " +
                STUDENT_NATIONAL_ID + " = "+"'"+ID+"'");
        db.execSQL("UPDATE "+ TABLE_NAME + " SET " + STUDENT_FATHER_NAME + " = "+"'"+FATHERNAME +"' "+ "WHERE " +
                STUDENT_NATIONAL_ID + " = "+"'"+ID+"'");
        db.execSQL("UPDATE "+ TABLE_NAME + " SET " + STUDENT_L_NAME + " = "+"'"+LNAME +"' "+ "WHERE " +
                STUDENT_NATIONAL_ID + " = "+"'"+ID+"'");
        db.execSQL("UPDATE "+ TABLE_NAME + " SET " + STUDENT_MAJOR + " = "+"'"+MAJOR +"' "+ "WHERE " +
                STUDENT_NATIONAL_ID + " = "+"'"+ID+"'");
        db.execSQL("UPDATE "+ TABLE_NAME + " SET " + STUDENT_DOB + " = "+"'"+DOB +"' "+ "WHERE " +
                STUDENT_NATIONAL_ID + " = "+"'"+ID+"'");
        db.execSQL("UPDATE "+ TABLE_NAME + " SET " + STUDENT_GENDER + " = "+"'"+GENDER +"' "+ "WHERE " +
                STUDENT_NATIONAL_ID + " = "+"'"+ID+"'");
        return true;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

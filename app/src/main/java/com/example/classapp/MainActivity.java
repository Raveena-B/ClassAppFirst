package com.example.classapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classapp.Database.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername.findViewById(R.id.Username);
        etPassword.findViewById(R.id.Password);

    }

    public void addData(View view){
        DBHelper dbHelper = new DBHelper(this) ;
        long value = dbHelper.addInfo(etUsername.getText().toString(),etPassword.getText().toString());

        if (value > 0){
            Toast.makeText(this, "Data Successfully added!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data id Not  Added!", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewAll(View view){ //view all in ds dbhealper

        DBHelper dbHelper = new DBHelper("user");

        List unames = dbHelper.readAllInfo("user");

        Toast.makeText(this, unames.toString(), Toast.LENGTH_SHORT).show();
    }

    public void deleteData(View view){
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.deleteInfo((etUsername.getText().toString()),
                etPassword.getText().toString());
        Toast.makeText(this, etUsername.getText().toString() + "Deleted successfully!", Toast.LENGTH_SHORT).show();
    }

    public void updateData(View view){
        DBHelper dbHelper = new DBHelper(this);
        int value = dbHelper.deleteInfo(etUsername.getText().toString(),etPassword.getText().toString());

        if(value > 0){
        Toast.makeText(this, "update successfully!", Toast.LENGTH_SHORT).show();
    }
        else{
            Toast.makeText(this, "not updated", Toast.LENGTH_SHORT).show();
        }
        }
    public void signIn(View view){
        DBHelper dbHelper = new DBHelper(this);  //ds object reference

        List usernames = dbHelper.readAllInfo("user");
        List passwords = dbHelper.readAllInfo("passwords");

        String user = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if(usernames.indexOf(user) >=0){
            if(passwords.get(usernames.indexOf(user)).equals(password)){
                Toast.makeText(this, "Login Successfully!!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Login Failed! Password is wrong", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Login Failed!!you are not registered", Toast.LENGTH_SHORT).show();
        }

    }
}
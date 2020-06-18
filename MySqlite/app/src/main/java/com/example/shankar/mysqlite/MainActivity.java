package com.example.shankar.mysqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    DatabaseHelperClass myDbClass;
    EditText editUserName, editEmailId, editGender, editPass;
    Button LoginBtn, ShowBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbClass = new DatabaseHelperClass(this);
        editUserName = (EditText) findViewById(R.id.userName);
        editEmailId = (EditText) findViewById(R.id.emailId);
        editGender = (EditText) findViewById(R.id.gender);
        editPass = (EditText) findViewById(R.id.password);
        LoginBtn = (Button) findViewById(R.id.btnLogin);
        ShowBtn = (Button) findViewById(R.id.btnShow);
        AddData();
        ViewData();


    }

    private void AddData() {
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = myDbClass.insertData(editUserName.getText().toString(),
                        editEmailId.getText().toString(),
                        editGender.getText().toString(),
                        editPass.getText().toString());
                if (isInserted == false)
                    Toast.makeText(MainActivity.this, "Data Successfully Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data could not be Inserted", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ViewData() {
        ShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray jArr = new JSONArray();
                jArr = myDbClass.getDataJArry();
                /*Cursor res = myDbClass.getAllData();
                if (res.getCount() == 0) {
                    //    showData("Error.....","No data found");
                    Toast.makeText(getApplicationContext(), "Error/....", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("1." + res.getString(0) + "\n");
                    buffer.append("2." + res.getString(1) + "\n\n");
                    buffer.append("3." + res.getString(2) + "\n\n");
                    buffer.append("4." + res.getString(3) + "\n\n");
                }*/
                //showData("Data List",buffer.toString());
//                String username = editUserName.getText().toString();
//                String emailid = editEmailId.getText().toString();
//                String gender = editGender.getText().toString();
//                String pass = editPass.getText().toString();
                intent = new Intent(MainActivity.this, ShowData.class);

                intent.putExtra("extra", jArr+"");
//                intent.putExtra("email", emailid);
//                intent.putExtra("gender", gender);
//                intent.putExtra("pass", pass);
                startActivity(intent);
            }
        });
    }

    //   private void showData(String title, String message) {
 /*       AlertDialog.Builder builder= new AlertDialog.Builder(this);
                  builder.create();
                  builder.setCancelable(true);
                  builder.setTitle(title);
                  builder.setMessage(message);
                  builder.show();



*/

}

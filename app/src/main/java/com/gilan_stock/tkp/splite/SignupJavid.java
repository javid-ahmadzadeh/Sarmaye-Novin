package com.gilan_stock.tkp.splite;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class SignupJavid extends AppCompatActivity {
    EditText UserNmae, UserFamily, UserAge,UserExper,UserEmail ;
    Button Register;
    RequestQueue requestQueue;
    String NameHolder,FamilyHolder,AgeHolder,ExperniseHolder, EmailHolder1 ;
    ProgressDialog progressDialog;

    String HttpUrl = "https://it-teach.ir/app10_JAVID/User-Registration.php";

    Boolean CheckEditText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_javid);
        UserNmae = (EditText) findViewById(R.id.EditTextName);
        UserFamily = (EditText) findViewById(R.id.EditTextFAMILY);
        UserAge = (EditText) findViewById(R.id.EditTextAGE);
        UserExper = (EditText) findViewById(R.id.EditTextEXPERICE);
        UserEmail = (EditText) findViewById(R.id.EditTextEmail);
        Register = (Button) findViewById(R.id.ButtonRegister);
        requestQueue = Volley.newRequestQueue(SignupJavid.this);
        progressDialog = new ProgressDialog(SignupJavid.this);

        // click listener :D
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    UserRegistration();
                }
                else {

                    Toast.makeText(SignupJavid.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    public void UserRegistration(){

        progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        progressDialog.dismiss();
                        Toast.makeText(SignupJavid.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(SignupJavid.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                // The firs argument should be same sa your MySQL database table columns.
                params.put("User_Name", NameHolder);
                params.put("User_Family", FamilyHolder);
                params.put("User_Age", AgeHolder);
                params.put("User_Expertise", ExperniseHolder);
                params.put("User_Email ", EmailHolder1);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(SignupJavid.this);
        requestQueue.add(stringRequest);

    } public void CheckEditTextIsEmptyOrNot(){

        NameHolder = UserNmae.getText().toString().trim();
        FamilyHolder = UserFamily.getText().toString().trim();
        AgeHolder = UserAge.getText().toString().trim();
        ExperniseHolder = UserExper.getText().toString().trim();
        EmailHolder1 = UserEmail.getText().toString().trim();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(FamilyHolder) || TextUtils.isEmpty(AgeHolder)
                || TextUtils.isEmpty(ExperniseHolder) || TextUtils.isEmpty(EmailHolder1))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }


    }
}

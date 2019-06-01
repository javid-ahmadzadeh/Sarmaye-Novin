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

public class SignUp extends AppCompatActivity {


    EditText FullName, Email, Password ;
    Button Register;
    RequestQueue requestQueue;
    String NameHolder, EmailHolder, PasswordHolder ;
    ProgressDialog progressDialog;

    String HttpUrl = "https://it-teach.ir/app10/User-Registration.php";

    Boolean CheckEditText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FullName = (EditText) findViewById(R.id.EditTextFullName);
        Email = (EditText) findViewById(R.id.EditTextEmail);
        Password = (EditText) findViewById(R.id.EditTextPassword);
        Register = (Button) findViewById(R.id.ButtonRegister);
        requestQueue = Volley.newRequestQueue(SignUp.this);
        progressDialog = new ProgressDialog(SignUp.this);

        // click listener :D
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    UserRegistration();
                }
                else {

                    Toast.makeText(SignUp.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(SignUp.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(SignUp.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                // The firs argument should be same sa your MySQL database table columns.
                params.put("User_Email", EmailHolder);
                params.put("User_Password", PasswordHolder);
                params.put("User_Full_Name", NameHolder);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);
        requestQueue.add(stringRequest);

    } public void CheckEditTextIsEmptyOrNot(){

        NameHolder = FullName.getText().toString().trim();
        EmailHolder = Email.getText().toString().trim();
        PasswordHolder = Password.getText().toString().trim();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }


}
}

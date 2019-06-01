package com.gilan_stock.tkp.splite;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class Login extends AppCompatActivity {
    EditText Email, Password;
    Button LoginButton;
    RequestQueue requestQueue;
    String EmailHolder, PasswordHolder;
    ProgressDialog progressDialog;
    String HttpUrl = "https://it-teach.ir/app10/user_login.php";
    Boolean CheckEditText;
    public static final String MyPref = "MyPrefers";
    public static final String Emails = "emailkey";
    public static final String Passwords = "passkey";
    SharedPreferences shPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        shPref = getSharedPreferences(MyPref, Context.MODE_PRIVATE);


        Email = (EditText) findViewById(R.id.editText_Email);
        Password = (EditText) findViewById(R.id.editText_Password);
        LoginButton = (Button) findViewById(R.id.button_login);
        requestQueue = Volley.newRequestQueue(Login.this);
        progressDialog = new ProgressDialog(Login.this);




        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    UserLogin();
                    String n = Email.getText().toString();
                    String f = Password.getText().toString();
                    SharedPreferences.Editor sEdit = shPref.edit();
                    sEdit.putString(Emails, n);
                    sEdit.putString(Passwords, f);
                    sEdit.apply();

                } else {

                    Toast.makeText(Login.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });
        if (shPref.contains(Emails)) {
            Email.setText(shPref.getString(Emails, null));
        }

        if (shPref.contains(Passwords)) {
            Password.setText(shPref.getString(Passwords, null));
        }

    }

    public void UserLogin() {
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        progressDialog.dismiss();
                        if(ServerResponse.toString().trim().equalsIgnoreCase("1")) {
                           Toast.makeText(Login.this, "Logged In Successfully", Toast.LENGTH_LONG).show();
                         //  finish();

                            Intent intent = new Intent(Login.this, ProfileActivity.class);
                            intent.putExtra("UserEmailTAG", EmailHolder);
                            startActivity(intent);
                        }
                        else {


                         Toast.makeText(Login.this, ServerResponse, Toast.LENGTH_LONG).show();
                            Toast.makeText(Login.this,"email: "+Email.getText().toString() , Toast.LENGTH_LONG).show();
                            Toast.makeText(Login.this,"password: "+Password.getText().toString() , Toast.LENGTH_LONG).show();


                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        progressDialog.dismiss();

                        Toast.makeText(Login.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("User_Email", EmailHolder);
                params.put("User_Password", PasswordHolder);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        requestQueue.add(stringRequest);

    }


    public void CheckEditTextIsEmptyOrNot() {

        EmailHolder = Email.getText().toString().trim();
        PasswordHolder = Password.getText().toString().trim();

        if (TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {

            CheckEditText = false;

        } else {

            CheckEditText = true;
        }




    }
}

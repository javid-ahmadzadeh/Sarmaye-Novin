package com.gilan_stock.tkp.splite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    TextView textView;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView = (TextView)findViewById(R.id.TextViewUserEmail);
        logout = (Button)findViewById(R.id.button_logout);
        String TempHolder = getIntent().getStringExtra("UserEmailTAG");
        textView.setText(textView.getText() + TempHolder);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ProfileActivity.this, "Logged Out Successfully", Toast.LENGTH_LONG).show();

                finish();
                Intent intent = new Intent(ProfileActivity.this, Login.class);

                startActivity(intent);

            }
        });
    }
}

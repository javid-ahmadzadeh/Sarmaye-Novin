package com.gilan_stock.tkp.splite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.gilan_stock.tkp.splite.Adapter.User_Adapter;
import com.gilan_stock.tkp.splite.ApiService.ApiServiceUser;
import com.gilan_stock.tkp.splite.CLASS.USER;

import java.util.List;

public class User_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);

        ApiServiceUser apiService = new ApiServiceUser(this);
        apiService.getPosts(new ApiServiceUser.OnPostsReceived() {
                                @Override
                                public void onReceived(List<USER> posts) {



                                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_USER);
                                    User_Adapter ciscoAdapter = new User_Adapter(User_Activity.this, posts);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(User_Activity.this, LinearLayoutManager.VERTICAL, false));
                                    recyclerView.setAdapter(ciscoAdapter);

                                }
                            });


                ImageView back = (ImageView) findViewById(R.id.back_USER);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(User_Activity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
    }


package com.gilan_stock.tkp.splite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.gilan_stock.tkp.splite.Adapter.User_Adaper_Javid;
import com.gilan_stock.tkp.splite.Adapter.User_Adapter;
import com.gilan_stock.tkp.splite.ApiService.ApiServiceUser;
import com.gilan_stock.tkp.splite.ApiService.ApiServiceUserJavid;
import com.gilan_stock.tkp.splite.CLASS.USEJAVID;
import com.gilan_stock.tkp.splite.CLASS.USER;

import java.util.List;

public class USER_ACTIVITY_JAVID extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user___javid);

        ApiServiceUserJavid apiService1 = new ApiServiceUserJavid(this);
        apiService1.getPosts(new ApiServiceUserJavid.OnPostsReceived() {
            @Override
            public void onReceived(List<USEJAVID> posts) {

               RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_USER_1);
                User_Adaper_Javid ciscoAdapter1 = new User_Adaper_Javid(USER_ACTIVITY_JAVID.this, posts);
                recyclerView.setLayoutManager(new LinearLayoutManager(USER_ACTIVITY_JAVID.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(ciscoAdapter1);
            }
        });

        ImageView back = (ImageView) findViewById(R.id.back_USER_1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(USER_ACTIVITY_JAVID.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}

package com.gilan_stock.tkp.splite.ApiService;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gilan_stock.tkp.splite.CLASS.USER;
import com.gilan_stock.tkp.splite.Data.DataUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TKP on 01/05/2019.
 */

public class ApiServiceUser {
    private static final String TAG = "ApiService";
    private Context context;
    public ApiServiceUser(Context context){

        this.context=context;
    }
    public void getPosts(final OnPostsReceived onPostsReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, "https://it-teach.ir/app10/GetPosts.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<USER> posts=new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    USER post=new USER();
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        post.setUserEmail(jsonObject.getString("User_Email"));
                        post.setId(jsonObject.getInt("id"));
                        post.setUserPass(jsonObject.getString("User_Password"));
                        post.setUserFullNmae(jsonObject.getString("User_Full_Name"));
                        posts.add(post);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                DataUser cl1 = null;
                cl1.posts = posts;
                onPostsReceived.onReceived(posts);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error );
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }
    public interface OnPostsReceived{
        void onReceived(List<USER> posts);
    }
}

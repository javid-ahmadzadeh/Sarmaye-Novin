package com.gilan_stock.tkp.splite.ApiService;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gilan_stock.tkp.splite.CLASS.USEJAVID;
import com.gilan_stock.tkp.splite.CLASS.USER;
import com.gilan_stock.tkp.splite.Data.DataUser;
import com.gilan_stock.tkp.splite.Data.DataUserJavid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TKP on 14/05/2019.
 */

public class ApiServiceUserJavid {
    private static final String TAG = "ApiService";
    private Context context;
    public ApiServiceUserJavid(Context context){

        this.context=context;
    }
    public void getPosts(final ApiServiceUserJavid.OnPostsReceived onPostsReceived){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, "https://it-teach.ir/app10_javid/GetPosts.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<USEJAVID> posts=new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    USEJAVID post=new USEJAVID();
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        post.setId(jsonObject.getInt("id"));
                        post.setUserName(jsonObject.getString("User_Name"));
                        post.setUserFamily(jsonObject.getString("User_Family"));
                        post.setUserAge(jsonObject.getString("User_Age"));
                        post.setUserExpertise(jsonObject.getString("User_Expertise"));
                        post.setUserEmail2(jsonObject.getString("User_Email"));
                        posts.add(post);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                DataUserJavid cl1 = null;
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
        void onReceived(List<USEJAVID> posts);
    }
}

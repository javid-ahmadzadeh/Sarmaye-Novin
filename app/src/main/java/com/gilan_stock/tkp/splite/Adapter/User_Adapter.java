package com.gilan_stock.tkp.splite.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gilan_stock.tkp.splite.CLASS.USER;
import com.gilan_stock.tkp.splite.Interface.SelectItemUser;
import com.gilan_stock.tkp.splite.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TKP on 01/05/2019.
 */

public class User_Adapter extends RecyclerView.Adapter<User_Adapter.User_AdapterViewHolder> {
    SelectItemUser selectItem ;
    private Context context;
    private List<USER> posts;
    public User_Adapter(Context context, List<USER> posts) {
        this.context = context;
        this.posts = posts;
    }
    @Override
    public User_AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_user,parent,false);
//        RecyclerView.ViewHolder viewHolder = new NewsViewHolder(view);
//        Typeface iranSansTypeface= Typeface.createFromAsset(context.getAssets(),"fonts/iranian_sans.ttf");
        return new User_AdapterViewHolder(view);
    }
    @Override
    public void onBindViewHolder(User_AdapterViewHolder holder, final int position) {
        USER post=posts.get(position);
        holder.userEmail.setText(post.getUserEmail());
        holder.userPass.setText(post.getUserPass());
        holder.userFullNmae.setText(post.getUserFullNmae());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem = (SelectItemUser) context;
                selectItem.itemselect(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return posts.size();
    }
    public class User_AdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView userEmail;
        private TextView userPass;
        private TextView userFullNmae;
        public User_AdapterViewHolder(View itemView ) {
            super(itemView);
            userEmail=(TextView)itemView.findViewById(R.id.item_email);
//            title.setTypeface(iranSansTypeface);
            userPass=(TextView)itemView.findViewById(R.id.item_password);
//            content.setTypeface(iranSansTypeface);
            userFullNmae=(TextView)itemView.findViewById(R.id.item_fullname);
//            date.setTypeface(iranSansTypeface);


        }
    }

}

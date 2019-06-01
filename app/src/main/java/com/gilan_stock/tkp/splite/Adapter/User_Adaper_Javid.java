package com.gilan_stock.tkp.splite.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gilan_stock.tkp.splite.CLASS.USEJAVID;
import com.gilan_stock.tkp.splite.CLASS.USER;
import com.gilan_stock.tkp.splite.Interface.SelectItemUser;
import com.gilan_stock.tkp.splite.Interface.SelectItemUserJavid;
import com.gilan_stock.tkp.splite.R;

import java.util.List;

/**
 * Created by TKP on 14/05/2019.
 */

public class User_Adaper_Javid extends RecyclerView.Adapter<User_Adaper_Javid.User_AdapterJAVIDViewHolder> {

    SelectItemUserJavid selectItem ;
    private Context context;
    private List<USEJAVID> posts;
    public User_Adaper_Javid(Context context, List<USEJAVID> posts) {
        this.context = context;
        this.posts = posts;
    }
    @Override
    public User_AdapterJAVIDViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_post_javid,parent,false);
        return new User_AdapterJAVIDViewHolder(view);
    }

    @Override
    public void onBindViewHolder(User_AdapterJAVIDViewHolder holder, final int position) {
        USEJAVID post=posts.get(position);
        holder.userName.setText(post.getUserName());

        holder.userFamily.setText(post.getUserFamily());

        holder.userAge.setText(post.getUserAge());


        holder.userExpertise.setText(post.getUserExpertise());
        holder.userEmail1.setText(post.getUserEmail2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem = (SelectItemUserJavid) context;
                selectItem.itemselect(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class User_AdapterJAVIDViewHolder extends RecyclerView.ViewHolder{
        private TextView userEmail1;
        private TextView userName;
        private TextView userFamily;
        private TextView userAge;
        private TextView userExpertise;

        public User_AdapterJAVIDViewHolder(View itemView ) {
            super(itemView);
          userEmail1=(TextView)itemView.findViewById(R.id.item_email1);
//            title.setTypeface(iranSansTypeface);
          userName=(TextView)itemView.findViewById(R.id.item_name1);
//            content.setTypeface(iranSansTypeface);
            userFamily=(TextView)itemView.findViewById(R.id.item_family1);
//            date.setTypeface(iranSansTypeface);

            userAge=(TextView)itemView.findViewById(R.id.item_age1);
            userExpertise=(TextView)itemView.findViewById(R.id.item_expertise1);

        }
    }
}

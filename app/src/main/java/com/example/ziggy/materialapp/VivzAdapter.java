package com.example.ziggy.materialapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ziggy on 2015-12-10.
 */
public class VivzAdapter  extends RecyclerView.Adapter<VivzAdapter.MyViewHolder>{

    private  LayoutInflater inflator;
    private Context context;
    private ClickListener clickListener;
    List<Information> data = Collections.emptyList();
    public VivzAdapter(Context context,List<Information> data){
        this.context = context;
        inflator = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view =  inflator.inflate(R.layout.custom_row, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconID);
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listMessage);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);

        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context,SubActivity.class));
            if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
            }
        }
    }

    public interface ClickListener{
         public void itemClicked(View view, int position);
    }
}

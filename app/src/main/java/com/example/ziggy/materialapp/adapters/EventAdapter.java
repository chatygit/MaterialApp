package com.example.ziggy.materialapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.ziggy.materialapp.R;
import com.example.ziggy.materialapp.network.VolleySingleton;
import com.example.ziggy.materialapp.pojo.Events;
import com.example.ziggy.materialapp.pojo.News;

import java.util.ArrayList;

/**
 * Created by Ziggy on 2015-12-30.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolderEvent> {

    private ArrayList<Events> listEvents = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private Context context;
    private ClickListener clickListener;
    public EventAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getsInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    public void setEventList(ArrayList<Events> listEvents){
        this.listEvents = listEvents;
        notifyItemRangeChanged(0, listEvents.size());
    }
    @Override
    public ViewHolderEvent onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_events,parent,false);
        ViewHolderEvent viewHolder = new ViewHolderEvent(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderEvent holder, int position) {
        Events currentEvent = listEvents.get(position);
        holder.eventDetail.setText(currentEvent.getDetail());
        holder.eventDate.setText(currentEvent.getDate());
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    class ViewHolderEvent extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView eventDetail;
        private TextView eventDate;
        public ViewHolderEvent(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            eventDetail = (TextView) itemView.findViewById(R.id.eventDetail);
            eventDate = (TextView) itemView.findViewById(R.id.eventDate);
        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context, SubActivity.class));
            if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
            }
        }
    }

    public interface ClickListener{
        public void itemClicked(View view,int position);

    }
}












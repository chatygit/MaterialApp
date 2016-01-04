package com.example.ziggy.materialapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ziggy.materialapp.network.VolleySingleton;

/**
 * Created by Ziggy on 2015-12-26.
 */
public class MyFragment extends Fragment {
    private TextView textView;
    public static MyFragment getInstance(int position){
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt("position",position);
        myFragment.setArguments(args);
        return myFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.activity_sub, container, false);
        Bundle bundle = getArguments();
        if(bundle != null){
            if(bundle.getInt("position") == 0){
                layout = inflater.inflate(R.layout.activity_sub,container,false);
                //textView = (TextView)layout.findViewById(R.id.position);
                //textView.setText("The Home Page");
            }else if(bundle.getInt("position") == 1){
                layout = inflater.inflate(R.layout.fragment_my,container,false);
                textView = (TextView)layout.findViewById(R.id.position);
                textView.setText("The Event Page");
            }else{
                layout = inflater.inflate(R.layout.fragment_my,container,false);
                textView = (TextView)layout.findViewById(R.id.position);
                textView.setText("The Contact Page");
            }
        }
        RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET, "http://php.net/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),"RESPONSE"+response,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"RESPONSE"+error,Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(request);
        return layout;
    }
}

















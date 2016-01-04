package com.example.ziggy.materialapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ziggy.materialapp.R;
import com.example.ziggy.materialapp.adapters.AdapterHome;
import com.example.ziggy.materialapp.adapters.EventAdapter;
import com.example.ziggy.materialapp.network.VolleySingleton;
import com.example.ziggy.materialapp.pojo.Events;
import com.example.ziggy.materialapp.pojo.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEvent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEvent extends Fragment implements EventAdapter.ClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String URL_EVENT = "http://myschool.com.ng/toolbox/mobile-app/events/index2.php";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private ArrayList<Events> listEvents = new ArrayList<>();
    private EventAdapter eventAdapter;
    private RecyclerView listNewsRyc;
    public static String selectedDate;
    public static String selectedDetail;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEvent.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEvent newInstance(String param1, String param2) {
        FragmentEvent fragment = new FragmentEvent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static String getRequestUrl(){
        return URL_EVENT;
    }

    public FragmentEvent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        volleySingleton = VolleySingleton.getsInstance();
        requestQueue = volleySingleton.getRequestQueue();
        sendJsonRequest();
    }

    private ArrayList<Events> parseJSONRequest(JSONArray response){
        ArrayList<Events> listEvents = new ArrayList<>();

        if(response!=null || response.length()>0) {


            try {
                JSONArray arrayEvents = response;
                for (int i = 0; i < arrayEvents.length(); i++) {
                    JSONObject currentEvent = arrayEvents.getJSONObject(i);
                    int id = currentEvent.getInt("id");
                    int comm_id = currentEvent.getInt("comm_id");
                    String detail = currentEvent.getString("detail");
                    String datetime = currentEvent.getString("event_date");
                    // data.append(id + " " + headline + "\n");
                    Events event = new Events(id, comm_id, detail, datetime);
                    listEvents.add(event);
                }
            } catch (JSONException e) {

            }


            //Toast.makeText(getActivity(),listNews.toString(),Toast.LENGTH_SHORT).show();
        }
        return listEvents;
    }

    private void sendJsonRequest() {
        JsonArrayRequest request = new JsonArrayRequest(getRequestUrl(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listEvents = parseJSONRequest(response);
                eventAdapter.setEventList(listEvents);
                //Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_event,container,false);
        listNewsRyc = (RecyclerView) view.findViewById(R.id.listEvent);
        listNewsRyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventAdapter = new EventAdapter(getActivity());
        eventAdapter.setClickListener(this);
        listNewsRyc.setAdapter(eventAdapter);
        sendJsonRequest();
        return view;
    }


    @Override
    public void itemClicked(View view, int position) {

    }
}

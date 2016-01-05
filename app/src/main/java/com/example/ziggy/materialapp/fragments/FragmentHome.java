package com.example.ziggy.materialapp.fragments;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ziggy.materialapp.PastQuestionsActivity;
import com.example.ziggy.materialapp.R;
import com.example.ziggy.materialapp.SubActivity;
import com.example.ziggy.materialapp.TakeExamActivity;
import com.example.ziggy.materialapp.activities.News_Detail;
import com.example.ziggy.materialapp.adapters.AdapterHome;
import com.example.ziggy.materialapp.network.VolleySingleton;
import com.example.ziggy.materialapp.pojo.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment implements AdapterHome.ClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String URL_NEWS = "http://myschool.com.ng/toolbox/mobile-app/news/index2.php";
   // public static final String URL_NEWS = "http://communicatorplusdemo.gouat.com/index.php?r=campaign/mytest";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private ArrayList<News> listNews = new ArrayList<>();
    private AdapterHome adapterHome;
    private RecyclerView listNewsRyc;
    public static String selectedHeadline;
    public static String selectedDetail;

    // Button Declarations
    private Button takeExam;
    private Button pastQuestions;
    private Context context;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static String getRequestUrl(){
        return URL_NEWS;
    }

    public FragmentHome() {
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


    private ArrayList<News> parseJSONRequest(JSONArray response){
        ArrayList<News> listNews = new ArrayList<>();

        StringBuilder data = new StringBuilder();
        if(response !=null && response.length()>0) {


            try {
                for (int i = 0; i < 4; i++) {
                //   for (int i = 0; i < arrayNews.length(); i++) {
                    JSONObject currentNews = response.getJSONObject(i);
                    int id = currentNews.getInt("id");
                    String headline = currentNews.getString("headline");
                    String detail = currentNews.getString("detail");
                    String datetime = currentNews.getString("date_time");
                   // data.append(id + " " + headline + "\n");
                    News news = new News(id, headline, detail, datetime);
                    listNews.add(news);
                }
            } catch (JSONException e) {

            }


            //Toast.makeText(getActivity(),listNews.toString(),Toast.LENGTH_SHORT).show();
        }
        return listNews;
    }

    private void sendJsonRequest() {
        JsonArrayRequest request = new JsonArrayRequest(getRequestUrl(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listNews = parseJSONRequest(response);
                adapterHome.setNewsList(listNews);
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home,container,false);


        // adding lister to button
        takeExam = (Button) view.findViewById(R.id.take_exam);
        pastQuestions = (Button) view.findViewById(R.id.past_questions);

        pastQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PastQuestionsActivity.class);
                startActivity(intent);
            }
        });

        takeExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TakeExamActivity.class);
                startActivity(intent);
            }
        });
        listNewsRyc = (RecyclerView) view.findViewById(R.id.listNews);
        listNewsRyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterHome = new AdapterHome(getActivity());
        adapterHome.setClickListener(this);
        listNewsRyc.setAdapter(adapterHome);
        sendJsonRequest();
        return view;
    }

    public static String getHeadline(){
        return selectedHeadline;
    }

    public static String getDetail(){
        return selectedDetail;
    }



    @Override
    public void itemClicked(View view, int position) {
        News currentNews = listNews.get(position);
        selectedHeadline = currentNews.getHeadline();
        selectedDetail = currentNews.getDetail();
        selectedDetail = selectedDetail.substring(0, selectedDetail.indexOf('<'));
        startActivity(new Intent(getActivity(), News_Detail.class));
    }
}
















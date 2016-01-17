package com.example.ziggy.materialapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ziggy.materialapp.pojo.BoxList;

import java.util.ArrayList;
import java.util.List;

public class TakeExamActivity extends AppCompatActivity {

    private ArrayList<BoxList> listExamSubjects;
    private Button nextButton;
    private String all = "";
    private CheckBoxListAdapter dataAdapter = null;

    private List<BoxList> coursesSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_exam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final ListView listview = (ListView) findViewById(R.id.listExamSubjects);
        fillSubjectList();

        dataAdapter = new CheckBoxListAdapter(this,R.layout.row,listExamSubjects);
        listview.setAdapter(dataAdapter);

        checkButtonClick();

    }

    private void fillSubjectList(){
        listExamSubjects = new ArrayList<>();
        listExamSubjects.add(new BoxList("ENG","English",true));
        listExamSubjects.add(new BoxList("BIO", "Biology", false));
        listExamSubjects.add(new BoxList("ZOO", "Zoology", false));

    }


    public class CheckBoxListAdapter extends ArrayAdapter<BoxList> {

        private ArrayList<BoxList> countryList;

        public CheckBoxListAdapter(Context context, int textViewResourceId,
                               ArrayList<BoxList> countryList){
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<>();
            this.countryList.addAll(countryList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder ;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.row, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        BoxList lang = (BoxList) cb.getTag();
                        lang.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            BoxList obj = countryList.get(position);
            holder.code.setText(" (" +  obj.getCode() + ")");
            holder.name.setText(obj.getName());
            holder.name.setChecked(obj.isSelected());
            holder.name.setTag(obj);

            return convertView;

        }

    }

    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.exam_next);
        myButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<BoxList> countryList =  dataAdapter.countryList;
                for(int i=0;i<countryList.size();i++){
                    BoxList country = countryList.get(i);
                    if(country.isSelected()){
                        responseText.append("\n" + country.getName());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

            }
        });

    }

}


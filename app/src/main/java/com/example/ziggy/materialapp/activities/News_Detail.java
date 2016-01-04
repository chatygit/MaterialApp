package com.example.ziggy.materialapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.ziggy.materialapp.R;
import com.example.ziggy.materialapp.fragments.FragmentHome;

public class News_Detail extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);

        TextView headlineText = (TextView) findViewById(R.id.textHeadline);
        TextView detailText = (TextView) findViewById(R.id.textDetail);

        String text = FragmentHome.getHeadline();
        String text2 = FragmentHome.getDetail();

        headlineText.setText(text);
        detailText.setText(text2);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

}

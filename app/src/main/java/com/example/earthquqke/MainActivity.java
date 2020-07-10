package com.example.earthquqke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=3&maxmag=9";
    EarthquakeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<Eathquakehelperclass> earthquakes = QueryUtils.extractEarthquakes();
            ListView mlistview=findViewById(R.id.list);
//            EarthquakeAdapter adapter=new EarthquakeAdapter(this,earthquakes);
                 adapter=new EarthquakeAdapter(this,new ArrayList<Eathquakehelperclass>());
        mlistview.setAdapter(adapter);
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);

        // OnItem Click Listner for List View
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Eathquakehelperclass eathquakehelperclass=adapter.getItem(position);

                Uri uri=Uri.parse(eathquakehelperclass.getUrl());
                Intent webintent=new Intent(Intent.ACTION_VIEW,uri);
                if (webintent.resolveActivity(getPackageManager()) != null) {
                    startActivity(webintent);
                }
            }
        });
    }
    private class EarthquakeAsyncTask extends AsyncTask<String,Void,ArrayList<Eathquakehelperclass>>{

        @Override
        protected ArrayList<Eathquakehelperclass> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Eathquakehelperclass> result = QueryUtils.FetchEarthquakeData(urls[0]);
            return result;
        }


        @Override
        protected void onPostExecute(ArrayList<Eathquakehelperclass> data) {
            adapter.clear();
            if(data!=null)
            adapter.addAll(data);
        }
    }

}
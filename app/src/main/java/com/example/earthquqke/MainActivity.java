package com.example.earthquqke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
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
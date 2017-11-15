package anandgaur.com.maptesting;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static anandgaur.com.maptesting.MapsActivity.current_lat;
import static anandgaur.com.maptesting.MapsActivity.current_lng;
import static anandgaur.com.maptesting.MapsActivity.db;

public class PumpListActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pump_list);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);


//        swipeRefreshLayout.setColorSchemeResources(
//                R.color.pink, R.color.indigo, R.color.lime);
        // refreshData();

        swipeRefreshLayout.setOnRefreshListener(new  SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });


        listView = (ListView) findViewById(R.id.listView);

        ArrayList<PumpDetail> loclist = (ArrayList<PumpDetail>) db.getAllPlace();



        final PumpPlaceAdapter adapter = new PumpPlaceAdapter(getApplicationContext(),loclist);

        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in=new Intent(getApplicationContext(),RouteActivity.class);
                PumpDetail item = (PumpDetail)parent.getItemAtPosition(position);
                in.putExtra("current_lat",current_lat);
                in.putExtra("current_lng",current_lng);
                in.putExtra("place_name",item.getPlacename());
                in.putExtra("lat",item.getLat());
                in.putExtra("lng",item.getLongi());
                startActivity(in);
            }
        });


    }

    private void refreshData() {
        Log.d("Reading: ", "Reading all Locations");
        ArrayList<PumpDetail> loclist = (ArrayList<PumpDetail>) db.getAllPlace();
        PumpPlaceAdapter adapter = new PumpPlaceAdapter(getApplicationContext(), loclist);
        listView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

}

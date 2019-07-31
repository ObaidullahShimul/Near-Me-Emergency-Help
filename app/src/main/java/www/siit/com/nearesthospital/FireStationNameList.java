package www.siit.com.nearesthospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class FireStationNameList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayAdapter<String> adapter;
    //private SearchView searchView;
    private SearchView searchView;
    //private android.widget.SearchView searchView;
    String[] fireSNames;
    public static String stationNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_station_name_list);


        setTitle("Fire Station Name");

        listView= (ListView) findViewById(R.id.listviewId);
        searchView= (SearchView) findViewById(R.id.searchviewId);


        //-----------for array string------------
        fireSNames=getResources().getStringArray(R.array.fireStationName);
        //array adapter
        adapter =new ArrayAdapter<String>(FireStationNameList.this,R.layout.sample_view,R.id.textviewId,fireSNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        stationNo=adapter.getItem(position);
        Toast.makeText(FireStationNameList.this,stationNo,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(FireStationNameList.this,FireStationDetails.class);
        startActivity(intent);

    }
}

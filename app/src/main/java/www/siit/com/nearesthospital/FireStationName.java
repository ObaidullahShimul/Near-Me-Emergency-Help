package www.siit.com.nearesthospital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FireStationName extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;
    private SearchView searchView;
    String[] countryNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_station_name);

        listView= (ListView) findViewById(R.id.listviewId);
        searchView= (SearchView) findViewById(R.id.searchviewId);

        //-----------for array string------------
        countryNames=getResources().getStringArray(R.array.fireStationName);
        //array adapter
        adapter =new ArrayAdapter<String>(FireStationName.this,R.layout.sample_view,R.id.textviewId,countryNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        //listView.setOnClickListener(this);
        //listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);


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

        String value=adapter.getItem(position);
        Toast.makeText(FireStationName.this,value,Toast.LENGTH_SHORT).show();

    }

}

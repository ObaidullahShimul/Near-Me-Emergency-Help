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

public class AmbulanceList extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;
    //private SearchView searchView;
    private SearchView searchView;
    //private android.widget.SearchView searchView;
    String[] ambulanceSNames;
    public static String ambulanceNos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_list);

        setTitle("Ambulance Name");

        listView= (ListView) findViewById(R.id.listviewId);
        searchView= (SearchView) findViewById(R.id.searchviewId);


        //-----------for array string------------
        ambulanceSNames=getResources().getStringArray(R.array.ambulanceNameList);
        //array adapter
        adapter =new ArrayAdapter<String>(AmbulanceList.this,R.layout.sample_view,R.id.textviewId,ambulanceSNames);
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


        ambulanceNos=adapter.getItem(position);
        Toast.makeText(AmbulanceList.this,ambulanceNos,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(AmbulanceList.this,AmbulanceDetails.class);
        startActivity(intent);

    }
}


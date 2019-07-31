package www.siit.com.nearesthospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import at.markushi.ui.CircleButton;

public class Main_ActitvityF extends AppCompatActivity {

    CircleButton fireStation, ambulanceBtn, hospitals,policStations,pharmacys,hotels,restaurants,mosques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__actitvity_f);

        fireStation=findViewById(R.id.fireStationBtnId);
        ambulanceBtn=findViewById(R.id.ambulanceBtnId);



        //--------------nearest Places Id---------------
        hospitals=findViewById(R.id.hospitalBtnId);
        policStations=findViewById(R.id.policStationBtnId);
        pharmacys=findViewById(R.id.pharmacyBtnId);
        hotels=findViewById(R.id.hotelBtnId);
        restaurants=findViewById(R.id.resturantBtnId);
        mosques=findViewById(R.id.mosqueBtnId);

        fireStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main_ActitvityF.this,FireStationNameList.class);
                startActivity(intent);

            }
        });

        ambulanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main_ActitvityF.this,AmbulanceList.class);
                startActivity(intent);

            }
        });


        //-----------------------hospitals-------------------------
        hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main_ActitvityF.this,GoogleMapsActivity.class);
                startActivity(intent);

            }
        });

        //----------------------Police Stations-------------------
        policStations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main_ActitvityF.this,GoogleMapsActivity.class);
                startActivity(intent);

            }
        });


        //--------------------Pharmacy-------------------------
        pharmacys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main_ActitvityF.this,GoogleMapsActivity.class);
                startActivity(intent);

            }
        });


        //-------------------------hotels----------------------
        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main_ActitvityF.this,GoogleMapsActivity.class);
                startActivity(intent);

            }
        });


        //------------------restaurants----------------------------
        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main_ActitvityF.this,GoogleMapsActivity.class);
                startActivity(intent);

            }
        });


        //--------------------mosques--------------------
        mosques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main_ActitvityF.this,GoogleMapsActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.signOutBtn){

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Main_ActitvityF.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }
}

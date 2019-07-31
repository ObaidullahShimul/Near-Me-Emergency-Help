package www.siit.com.nearesthospital;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static www.siit.com.nearesthospital.FireStationNameList.stationNo;

public class FireStationDetails extends AppCompatActivity implements View.OnClickListener {

    TextView stationName,phoneNo;
    Button callButton;

    String stationPhoneNo="01945147784";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_station_details);

        stationName=findViewById(R.id.fireStationNameTextId);
        phoneNo=findViewById(R.id.phoneNumberTextId);
        callButton=findViewById(R.id.callBtnId);

        //if (value.equals("India")){
        stationName.setText(stationNo);
        phoneNo.setText(stationPhoneNo);


        callButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.callBtnId){

            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("tel:+01945147784"));
            startActivity(intent);
        }

    }
}

package www.siit.com.nearesthospital;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlaces extends AsyncTask<Object,String,String>
{
    private String googleplaceData,url;
    private GoogleMap mMap;


    @Override
    protected String doInBackground(Object... objects)
    {
        mMap=(GoogleMap) objects[0];
        url=(String) objects[1];

        DownloadUrl downloadUrl=new DownloadUrl();
        try
        {
            googleplaceData=downloadUrl.ReadTheUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleplaceData;
    }

    @Override
    protected void onPostExecute(String s)
    {
        //super.onPostExecute(s);
        List<HashMap<String,String>> nearByPlacesList=null;
        DataParser dataParser=new DataParser();
        nearByPlacesList=dataParser.parse(s);
        DisplayNearbyPlaces(nearByPlacesList);
    }

    //--------
    private void DisplayNearbyPlaces(List<HashMap<String,String>> nearByPlacesList)
    {
        for (int i = 0; i < nearByPlacesList.size(); i++) {

            MarkerOptions markerOptions=new MarkerOptions();
            HashMap<String,String> googleNearbyPlaces=nearByPlacesList.get(i);

            String nameOfPlace=googleNearbyPlaces.get("place_name");
            String vicinity=googleNearbyPlaces.get("vicinity");
            double lat=Double.parseDouble(googleNearbyPlaces.get("lat"));
            double lng=Double.parseDouble(googleNearbyPlaces.get("lng"));



            //---------copy from onClickMethod--------------------
            LatLng latLng=new LatLng(lat,lng);
            markerOptions.position(latLng);
            markerOptions.title(nameOfPlace+" : "+vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
            
        }
    }
}

package www.siit.com.nearesthospital;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class GoogleMapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener

{

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code=99;

    private double latitude,longitude;
    private int ProximityRadius=10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        //-----------my-------
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            checkUserLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //---------------onClick Method----------------------------------
    public void onClickk(View v)
    {
        String hospital="hospital", police="police station",restaurant="restaurant", pharmacy="pharmacy",hotel="hotel",mosque="mosque";
        //String url=getUrl(latitude,longitude,hospital);
        Object transferData[]=new Object[2];

        GetNearbyPlaces getNearbyPlaces=new GetNearbyPlaces();

        switch (v.getId())
        {
            case R.id.search_address:
                EditText addresField=(EditText) findViewById(R.id.locationEditId);
                String address=addresField.getText().toString();

                List<Address> addressList=null;
                MarkerOptions userMarkerOptions=new MarkerOptions();

                if (!TextUtils.isEmpty(address))
                {
                    Geocoder geocoder=new Geocoder(this);
                    try
                    {
                        addressList=geocoder.getFromLocationName(address,6);
                        if (addressList!=null)
                        {
                            for (int i=0; i<addressList.size(); i++)
                            {
                                Address userAddress=addressList.get(i);
                                LatLng latLng=new LatLng(userAddress.getLatitude(),userAddress.getLongitude());

                                //---------copy from onLocationChanged--------------------
                                userMarkerOptions.position(latLng);
                                userMarkerOptions.title(address);
                                userMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                mMap.addMarker(userMarkerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                            }
                        }
                        
                        else 
                        {
                            Toast.makeText(this, "Location Not Found.......", Toast.LENGTH_SHORT).show();
                        }

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }

                else
                    {
                        Toast.makeText(this, "Please Write any Location Name......", Toast.LENGTH_SHORT).show();
                    }

                break;

                //---------------------- Hospital,School,Resturant Button work--------------------------
            case R.id.hospital_nearby:
                mMap.clear();
                String url=getUrl(latitude,longitude,hospital);
                transferData[0]=mMap;
                transferData[1]=url;
                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Nearby Hospitals......", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby Hospitals......", Toast.LENGTH_SHORT).show();
                break;


            case R.id.policestation_nearby:
                mMap.clear();
                url=getUrl(latitude,longitude,police);
                transferData[0]=mMap;
                transferData[1]=url;
                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Nearby Police Station......", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby Police Station......", Toast.LENGTH_SHORT).show();
                break;


            case R.id.pharmacy_nearby:
                mMap.clear();
                url=getUrl(latitude, longitude, pharmacy);
                transferData[0]=mMap;
                transferData[1]=url;
                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Nearby Pharmacy......", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby Pharmacy......", Toast.LENGTH_SHORT).show();
                break;


            case R.id.hotel_nearby:
                mMap.clear();
                url=getUrl(latitude, longitude, hotel);
                transferData[0]=mMap;
                transferData[1]=url;
                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Nearby Hotels......", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby Hotels......", Toast.LENGTH_SHORT).show();
                break;

            case R.id.resturant_nearby:
                mMap.clear();
                url=getUrl(latitude, longitude, restaurant);
                transferData[0]=mMap;
                transferData[1]=url;
                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Nearby Restaurants......", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby Restaurants......", Toast.LENGTH_SHORT).show();
                break;


            case R.id.mosque_nearby:
                mMap.clear();
                url=getUrl(latitude, longitude, mosque);
                transferData[0]=mMap;
                transferData[1]=url;
                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for Nearby Mosque......", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing Nearby Mosque......", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    //---------7th V---------
    private String getUrl(double latitude, double longitude,String nearbyPlace)
    {
        StringBuilder googleURL=new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleURL.append("location=" + latitude + "," + longitude);
        googleURL.append("&radius=" + ProximityRadius);
        googleURL.append("&type=" + nearbyPlace);
        googleURL.append("&sensor=true");
        googleURL.append("&key=" + "AIzaSyAjO3hxzs6cYhv4TviJWoTLDnevBWriDSE");

        Log.d("GoogleMapActivity","url = "+googleURL.toString());

        return googleURL.toString();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {

            buildgoogleApiclient();
            mMap.setMyLocationEnabled(true);
        }

    }


    public boolean checkUserLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
            }

            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
            }

            return false;
        }
        else {
            return true;
        }
    }

    //------


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case Request_User_Location_Code:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
                    {
                        if (googleApiClient==null)
                        {
                            buildgoogleApiclient();
                        }

                        mMap.setMyLocationEnabled(true);
                    }
                }

                else
                    {
                        Toast.makeText(this, "Permission Denied.....", Toast.LENGTH_SHORT).show();
                    }

                    return;
        }
    }

    protected synchronized void buildgoogleApiclient()
    {
            googleApiClient=new GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            googleApiClient.connect();
    }

    //------my implements method--------

    @Override
    public void onLocationChanged(Location location) {

        latitude=location.getLatitude();
        longitude=location.getLongitude();

        lastLocation=location;

        if (currentUserLocationMarker!=null){
            currentUserLocationMarker.remove();
        }

        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("User Current Location Marker");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentUserLocationMarker=mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(12));

        if (googleApiClient!=null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, (com.google.android.gms.location.LocationListener) this);
        }

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest=new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            //LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest, this);
        }



    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

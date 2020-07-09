package com.vidhu.appzoc.tripper;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by appzoc8 on 14/3/16.
 */
public class MainActivity extends Fragment {

    private static GoogleMap googleMap;
    ArrayList<LatLng> markerPoints;
    TextView tvDistanceDuration;

    AutoCompleteTextView source;
    AutoCompleteTextView destination;
    Button search;

    ParserTask parserTask;
    ParserTask placeDetailsParserTask;

    PlacesTask placesTask;

    final int PLACES = 0;
    final int PLACES_DETAILS = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.activity_main, container, false);
        setUpMapIfNeeded();
        markerPoints = new ArrayList<LatLng>();

        tvDistanceDuration = (TextView)mView. findViewById(R.id.tv_distance_time);
        // Getting a reference to the AutoCompleteTextView
        source = (AutoCompleteTextView) mView.findViewById(R.id.from);
        source.setThreshold(1);


        destination = (AutoCompleteTextView) mView.findViewById(R.id.to);
        destination.setThreshold(1);


        search=(Button) mView.findViewById(R.id.search);


        source.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                placesTask = new PlacesTask(0);
                placesTask.execute(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }


        });

        destination.addTextChangedListener(new TextWatcher() {


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                placesTask = new PlacesTask(1);
                placesTask.execute(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });


        // Setting an item click listener for the AutoCompleteTextView dropdown list
        source.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index,
                                    long id) {

//                GeocodingLocation locationAddress = new GeocodingLocation();
//                locationAddress.getAddressFromLocation(source,
//                        getActivity().getApplicationContext(), new GeocoderHandler());

            }
        });

        destination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index,
                                    long id) {

//                GeocodingLocation2 locationAddress2 = new GeocodingLocation2();
//                locationAddress2.getAddressFromLocation(destination,
//                        getActivity().getApplicationContext(), new GeocoderHandler2());

            }
        });



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!source.getText().equals("")) {

                    GeocodingLocation locationAddress = new GeocodingLocation();
                    locationAddress.getAddressFromLocation(source,
                            getActivity().getApplicationContext(), new GeocoderHandler());

                }else{
                    Toast.makeText(getActivity(),"Add source ",Toast.LENGTH_LONG).show();
                }

                if(!destination.getText().equals("")){

                    GeocodingLocation2 locationAddress2 = new GeocodingLocation2();
                    locationAddress2.getAddressFromLocation(destination,
                            getActivity().getApplicationContext(), new GeocoderHandler2());

                }

                else{
                    Toast.makeText(getActivity(),"Add destination",Toast.LENGTH_LONG).show();
                }

            }
        });
        return mView;
    }




    public void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            googleMap = ((SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map)).getMap();


            // Check if we were successful in obtaining the map.
            if (googleMap != null)
                setUpMap();
        }


    }

    private void setUpMap() {

        try {
            // Enable MyLocation Button in the Map
            googleMap.setMyLocationEnabled(true);

            googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition position) {
                    LatLngBounds bounds = googleMap.getProjection().getVisibleRegion().latLngBounds;
//                    mAdapter.setBounds(bounds);
                }
            });
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    private class PlacesTask extends AsyncTask<String, Void, String> {

        int position;
        PlacesTask(int position)
        {
            this.position=position;
        }
        @Override
        protected String doInBackground(String... place) {
            // For storing data from web service
            String data = "";

            // Obtain browser key from https://code.google.com/apis/console
            String key = "key=AIzaSyDRqh8QSu6UQFeo995dui9yPfD-j7BMSR4";

            String input = "";

            try {
                input = "input=" + URLEncoder.encode(place[0], "utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            // place type to be searched
            String types = "types=geocode";

            // Sensor enabled
            String sensor = "sensor=false";

            // Building the parameters to the web service
            String parameters = input + "&" + types + "&" + sensor + "&" + key;

            // Output format
            String output = "json";

            // Building the url to the web service
            String url = "https://maps.googleapis.com/maps/api/place/autocomplete/" + output + "?" + parameters;

            try {
                // Fetching the data from we service
                data = downloadUrl(url);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Creating ParserTask
            parserTask = new ParserTask(position);

            // Starting Parsing the JSON string returned by Web Service
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

        JSONObject jObject;
        int position;

        ParserTask(int position)
        {
            this.position=position;
        }

        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;

            PlaceJSONParser placeJsonParser = new PlaceJSONParser();

            try {
                jObject = new JSONObject(jsonData[0]);

                // Getting the parsed data as a List construct
                places = placeJsonParser.parse(jObject);

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return places;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {

            String[] from = new String[]{"description"};
            int[] to = new int[]{android.R.id.text1};

            // Creating a SimpleAdapter for the AutoCompleteTextView
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), result, android.R.layout.simple_list_item_1, from, to);

            // Setting the adapter
            if (position==0) {
                source.setAdapter(adapter);
            }else if(position==1){
                destination.setAdapter(adapter);
            }
        }
    }



    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");

                    break;
                default:
                    locationAddress = null;
            }

            if(!locationAddress.contains("Address: "))
            {
                double lat=Double.parseDouble(locationAddress.split(",")[0]);
                double lng=Double.parseDouble(locationAddress.split(",")[1]);
                LatLng latLng=new LatLng(lat,lng);

                markerPoints.add(latLng);
            }
//            source.setText(locationAddress);
            if(markerPoints.size()>0) {
//                googleMap.addMarker(new MarkerOptions().position(markerPoints.get(0)));
            }

        }
    }

    class GeocoderHandler2 extends GeocoderHandler {

        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }

            if(!locationAddress.contains("Address: "))
            {
                double lat=Double.parseDouble(locationAddress.split(",")[0]);
                double lng=Double.parseDouble(locationAddress.split(",")[1]);
                LatLng latLng=new LatLng(lat,lng);

                markerPoints.add(latLng);
            }
//            source.setText(locationAddress);
            if(markerPoints.size()==2) {
                googleMap.addMarker(new MarkerOptions().position(markerPoints.get(0))).setVisible(true);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((markerPoints.get(0)), 10));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);
                googleMap.addMarker(new MarkerOptions().position(markerPoints.get(0)));
                googleMap.addMarker(new MarkerOptions().position(markerPoints.get(1)));

                LatLng origin = markerPoints.get(0);
                LatLng dest = markerPoints.get(1);

                markerPoints.clear();

                // Getting URL to the Google Directions API
                String url = getDirectionsUrlDirection(origin, dest);

                DownloadTaskDirection downloadTask = new DownloadTaskDirection();

                // Start downloading json data from Google Directions API
                downloadTask.execute(url);
            }else{
                markerPoints.clear();
                Toast.makeText(getActivity(),"No location found",Toast.LENGTH_LONG).show();
            }
        }
    }

    ////////////////////////////direction////////////////////////////

    private String getDirectionsUrlDirection(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }
    /** A method to download json data from url */
    private String downloadUrldirection(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTaskDirection extends AsyncTask<String, Void, String>{

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrldirection(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTaskDirection parserTask = new ParserTaskDirection();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTaskDirection extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    if(j==0){    // Get distance from the list
                        distance = (String)point.get("distance");
                        continue;
                    }else if(j==1){ // Get duration from the list
                        duration = (String)point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }



                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);
                lineOptions.color(Color.RED);
            }

            tvDistanceDuration.setText("Distance:"+distance + ", Duration:"+duration);

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions!=null)
            {

                tvDistanceDuration.setText("Distance:"+distance + ", Duration:"+duration);
            googleMap.addPolyline(lineOptions);
                markerPoints.clear();

    }}}
}

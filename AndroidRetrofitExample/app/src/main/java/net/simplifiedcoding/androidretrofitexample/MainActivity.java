package net.simplifiedcoding.androidretrofitexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

//Class having OnItemClickListener to handle the clicks on list
public class MainActivity extends AppCompatActivity  {

    //Root URL of our web service
    public static final String ROOT_URL = "http://api.androidhive.info/";//http://nucleusproperties.in/api_new/projects/projectlist
    // http://api.androidhive.info/

    //Strings to bind with intent will be used to send data to other activity
    public static final String KEY_BOOK_ID = "id";
    public static final String KEY_BOOK_NAME = "name";
    public static final String KEY_BOOK_GENDER = "gender";
    public static final String KEY_BOOK_EMAIL = "email";

    //List view to show data
    private ListView listView;

    //List of type books this list will store type Book which is our data model
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewBooks);

        //Calling the method that will fetch data

       new GetAsynckTask(MainActivity.this).execute("contacts");


    }



    private class GetAsynckTask extends AsyncTask<String, String, List<Book>> {
        Context mContext;
        ProgressDialog loading;
        RestAdapter adapter;
        BooksAPI api;
        JSONObject mJsonObject;
        JSONArray mJsonArray;
        Book book;
        List<Book> list;

        public GetAsynckTask(MainActivity mainActivity) {
            mContext = mainActivity;
        }
        @Override
        protected List<Book> doInBackground(String... params) {
            try {

                //JsonArray jsonArray=null;

            JsonObject jsoon =  api.getBooks();
                mJsonObject = new JSONObject(jsoon.toString());
                mJsonArray=mJsonObject.getJSONArray("contacts");




                //mJsonArray = mJsonObject.getJSONArray("data");
            if (mJsonObject != null) {

           list = new ArrayList<>();
            try{

            for (int j=0;j<mJsonArray.length();j++){
                book = new Book();
                book.setName(mJsonArray.getJSONObject(j).getString("name"));
                book.setEmail(mJsonArray.getJSONObject(j).getString("email"));
                //book.setName(mJsonArray.getJSONObject(j).getString("email"));
                list.add(book);
            }
            }
            catch (JsonIOException e){
                e.printStackTrace();
            }

            Log.v("SIZE", list.size() + "");



        }  } catch (Exception e) {
                e.printStackTrace();

            }
            return list;
        }


        @Override
        protected void onPreExecute() {

            adapter = new RestAdapter.Builder()
                    .setEndpoint(ROOT_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
            api = adapter.create(BooksAPI.class);

            loading = ProgressDialog.show(mContext, "Fetching Data", "Please wait...", false,false);
            loading.show();

        }


        @Override
        protected void onPostExecute(List<Book> list) {

            CustomBaseAdapter arr = new CustomBaseAdapter(MainActivity.this, list);
            listView.setAdapter(arr);
            loading.dismiss();
        }
    }
}

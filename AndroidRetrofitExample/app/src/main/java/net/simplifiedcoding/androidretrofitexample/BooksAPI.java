package net.simplifiedcoding.androidretrofitexample;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Belal on 11/3/2015.
 */
public interface BooksAPI {

    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @GET("/contacts/")
    JsonObject getBooks();
}

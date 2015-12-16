package com.example.raingeval.booksmanager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener {

    private Button scanBtn, addBtn;
    private TextView authorText, titleText, descriptionText, dateText, ratingCountText;
    private String author, title, isbn, category, publisher, year, imagePath, description;
    private LinearLayout starLayout;
    private ImageView thumbView;
    private ImageView[] starViews;
    private Bitmap thumbImg;


    private Context thisContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisContext = this;
        setContentView(R.layout.activity_scan);
        scanBtn = (Button)findViewById(R.id.scan_button);
        scanBtn.setOnClickListener(this);
        addBtn = (Button)findViewById(R.id.add_button);
        addBtn.setVisibility(View.GONE);

        authorText = (TextView)findViewById(R.id.scan_author);
        titleText = (TextView)findViewById(R.id.scan_title);
        descriptionText = (TextView)findViewById(R.id.scan_description);
        dateText = (TextView)findViewById(R.id.scan_date);
        starLayout = (LinearLayout)findViewById(R.id.scan_star_layout);
        ratingCountText = (TextView)findViewById(R.id.scan_rating_count);
        thumbView = (ImageView)findViewById(R.id.thumb);

        starViews= new ImageView[5];
        for(int s=0; s<starViews.length; s++){
            starViews[s]=new ImageView(this);
        }

        if (savedInstanceState != null){
            authorText.setText(savedInstanceState.getString("authorText"));
            titleText.setText(savedInstanceState.getString("titleText"));
            author = savedInstanceState.getString("author");
            title = savedInstanceState.getString("title");
            isbn = savedInstanceState.getString("isbn");
            category = savedInstanceState.getString("category");
            publisher = savedInstanceState.getString("publisher");
            year = savedInstanceState.getString("year");
            imagePath = savedInstanceState.getString("imagePath");
            descriptionText.setText(savedInstanceState.getString("description"));
            dateText.setText(savedInstanceState.getString("date"));
            ratingCountText.setText(savedInstanceState.getString("ratings"));
            int numStars = savedInstanceState.getInt("stars");//zero if null
            for(int s=0; s<numStars; s++){
                starViews[s].setImageResource(R.drawable.mario_star);
                starLayout.addView(starViews[s]);
            }
            starLayout.setTag(numStars);
            thumbImg = (Bitmap)savedInstanceState.getParcelable("thumbPic");
            thumbView.setImageBitmap(thumbImg);
            if(savedInstanceState.getInt("isButtonVisible")==View.VISIBLE) addBtn.setVisibility(View.VISIBLE);
            else addBtn.setVisibility(View.GONE);

        }else{
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onClick(View v){
        //scan
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        //check if valid result
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            Log.v("SCAN", "content: " + scanContent + " - format: " + scanFormat);
            this.isbn = scanContent;
            if(scanContent!=null && scanFormat!=null && scanFormat.equalsIgnoreCase("EAN_13")){
                //book search
                String bookSearchString = "https://www.googleapis.com/books/v1/volumes?"+
                        "q=isbn:"+scanContent;//+"&key="+ "AIzaSyBwM60GVNdmmUAlbIdEZ8Pj7ZkKForyzAo";
                //new GetBookInfo().execute(bookSearchString);
                getBookInfo(bookSearchString);
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Not a valid scan!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else{
            //invalid scan data
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    protected void onSaveInstanceState(Bundle savedBundle) {
        savedBundle.putString("titleText", ""+titleText.getText());
        savedBundle.putString("authorText", ""+authorText.getText());
        savedBundle.putString("title", ""+title);
        savedBundle.putString("author", ""+author);
        savedBundle.putString("isbn", ""+isbn);
        savedBundle.putString("category", ""+category);
        savedBundle.putString("publisher", ""+publisher);
        savedBundle.putString("year", "" + year);
        savedBundle.putString("imagePath", "" + imagePath);
        savedBundle.putString("description", ""+descriptionText.getText());
        savedBundle.putString("date", "" + dateText.getText());
        savedBundle.putString("ratings", "" + ratingCountText.getText());
        savedBundle.putParcelable("thumbPic", thumbImg);
        savedBundle.putInt("isButtonVisible", addBtn.getVisibility());
        if(starLayout.getTag()!=null)
            savedBundle.putInt("stars", Integer.parseInt(starLayout.getTag().toString()));
    }

    public void addBook(View view) {


        BookLibrary bookLibrary = new BookLibrary(this);
        System.out.println(author);
        System.out.println(title);
        System.out.println(isbn);
        System.out.println(category);
        Book b = bookLibrary.createBook(author, title, isbn, category, publisher, year, imagePath, description);
        bookLibrary.addBook(b);
        Toast toast = Toast.makeText(this, R.string.book_added, Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void getBookInfo(String bookURL){
        RequestQueue queue = Volley.newRequestQueue(thisContext);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, bookURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseResult(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(stringRequest);
    }

    protected void parseResult(String result){
        //parse search results
        System.out.println(result);
        try{
            //parse results
            addBtn.setVisibility(View.VISIBLE);
            JSONObject resultObject = new JSONObject(result);
            JSONArray bookArray = resultObject.getJSONArray("items");
            JSONObject bookObject = bookArray.getJSONObject(0);
            JSONObject volumeObject = bookObject.getJSONObject("volumeInfo");
            try{
                titleText.setText("TITLE: "+volumeObject.getString("title"));
                title = volumeObject.getString("title"); }
            catch(JSONException jse){
                titleText.setText("");
                title = "";
                jse.printStackTrace();
            }
            StringBuilder authorBuild = new StringBuilder("");
            try{
                JSONArray authorArray = volumeObject.getJSONArray("authors");
                for(int a=0; a<authorArray.length(); a++){
                    if(a>0) authorBuild.append(", ");
                    authorBuild.append(authorArray.getString(a));
                }
                authorText.setText("AUTHOR(S): "+authorBuild.toString());
                author = authorArray.getString(0);
            }
            catch(JSONException jse){
                authorText.setText("");
                author = "";
                jse.printStackTrace();
            }
            try {
                dateText.setText("PUBLISHED: "+volumeObject.getString("publishedDate"));
                year = volumeObject.getString("publishedDate");}
            catch(JSONException jse){
                dateText.setText("");
                year = "";
                jse.printStackTrace();
            }
            try{
                descriptionText.setText("DESCRIPTION: "+volumeObject.getString("description"));
                description = volumeObject.getString("description");}
            catch(JSONException jse){
                descriptionText.setText("");
                description = "";
                jse.printStackTrace();
            }
            try{
                //set stars
                double decNumStars = Double.parseDouble(volumeObject.getString("averageRating"));
                int numStars = (int)decNumStars;
                starLayout.setTag(numStars);
                starLayout.removeAllViews();
                for(int s=0; s<numStars; s++){
                    starViews[s].setImageResource(R.drawable.mario_star);
                    starLayout.addView(starViews[s]);
                }
            }
            catch(JSONException jse){
                starLayout.removeAllViews();
                jse.printStackTrace();
            }
            try {
                ratingCountText.setText(" - " + volumeObject.getString("ratingsCount") + " ratings"); }
            catch(JSONException jse){
                ratingCountText.setText("");
                jse.printStackTrace();
            }
            try{
                JSONObject imageInfo = volumeObject.getJSONObject("imageLinks");
                new GetBookThumb().execute(imageInfo.getString("thumbnail"));
            }
            catch(JSONException jse){
                thumbView.setImageBitmap(null);
                imagePath ="";
                jse.printStackTrace();
            }
            try{
                JSONArray categories = volumeObject.getJSONArray("categories");
                category = categories.getString(0);
                System.out.println(category);
            }
            catch(JSONException jse){
                category = "";
                jse.printStackTrace();
            }
            try{
                JSONArray idArray = volumeObject.getJSONArray("industryIdentifiers");
                JSONObject id = idArray.getJSONObject(0);
                isbn = id.getString("identifier");
                System.out.println("isbn");
            }
            catch(JSONException jse){
                jse.printStackTrace();
            }
            try{
                publisher = volumeObject.getString("publisher");
            }
            catch(JSONException jse){
                publisher = "";
                jse.printStackTrace();
            }
        }
        catch (Exception e) {
            //no result
            e.printStackTrace();
            titleText.setText("NOT FOUND");
            authorText.setText("");
            descriptionText.setText("");
            dateText.setText("");
            starLayout.removeAllViews();
            ratingCountText.setText("");
            thumbView.setImageBitmap(null);
            addBtn.setVisibility(View.GONE);
        }

    }


    private class GetBookThumb extends AsyncTask<String, Void, String> {



        //get thumbnail
        @Override
        protected String doInBackground(String... thumbURLs) {
            //attempt to download image
            try{
                //try to download
                URL thumbURL = new URL(thumbURLs[0]);
                URLConnection thumbConn = thumbURL.openConnection();
                thumbConn.connect();
                InputStream thumbIn = thumbConn.getInputStream();
                BufferedInputStream thumbBuff = new BufferedInputStream(thumbIn);
                thumbImg = BitmapFactory.decodeStream(thumbBuff);
                thumbBuff.close();
                thumbIn.close();
                thumbConn = thumbURL.openConnection();
                thumbConn.connect();
                thumbIn = thumbConn.getInputStream();


                File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File outputFile = new File(storageDir, title+".bmp");
                FileOutputStream f = new FileOutputStream(outputFile);
                byte[] buffer = new byte[2048];
                int i;
                while ((i = thumbIn.read(buffer)) > 0) {
                    f.write(buffer, 0, i);
                }
                f.close();
                imagePath = outputFile.getAbsolutePath();

                //thumbBuff.close();
                thumbIn.close();
            }
            catch(Exception e) {
                e.printStackTrace();

            }
            return "";
        }

        protected void onPostExecute(String result) {
            thumbView.setImageBitmap(thumbImg);
        }
    }

}

package com.example.wundermanproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductList extends AppCompatActivity {

    String ProductName;
    String ProductPrice;
    String ProductID;
    String jsonString = "https://run.mocky.io/v3/cdc28c44-ea46-4e03-b6cc-c7782223e96d";

    ArrayList<HashMap<String,String>> productList;

    int imageid;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        productList = new ArrayList<>();
        listView = findViewById(R.id.listview);


        GetData getData = new GetData();
        getData.execute();

    }

   public class GetData extends AsyncTask<String,String,String>{

       @Override
       protected String doInBackground(String... strings) {
           String current = "";

           try {
               URL url;
               HttpURLConnection urlConnection = null;
               try {
                   url = new URL(jsonString);
                   urlConnection = (HttpURLConnection) url.openConnection();

                   InputStream inputStream = urlConnection.getInputStream();
                   InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                   int data = inputStreamReader.read();
                   while (data != -1) {
                       current += (char) data;
                       data = inputStreamReader.read();
                   }
                   return current;

               } catch (MalformedURLException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               } finally {
                   if (urlConnection != null) {
                       urlConnection.disconnect();
                   }
               }

           } catch (Exception e) {
               e.printStackTrace();
           }
               return current;
           }

       @Override
       protected void onPostExecute(String s) {
           try {
               JSONObject jsonObject = new JSONObject(s);
               JSONArray jsonArray = jsonObject.getJSONArray("Products");
               System.out.println(jsonArray.length());
               //i<jsonArray.length() for entire array
               for (int i = 0; i<jsonArray.length(); i++){
                   JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                   ProductName = jsonObject1.getString("name");
                   ProductPrice = jsonObject1.getString("cost");


                   //Hashmap
                   HashMap<String,String> products = new HashMap<>();
                   products.put("name", ProductName);
                   products.put("cost", ProductPrice);

                   productList.add(products);

               }
           } catch (JSONException e) {
               e.printStackTrace();
           }

           ListAdapter adapter = new SimpleAdapter(ProductList.this, productList, R.layout.row_layout, new String[] {"name", "cost"}, new int[]{R.id.textView, R.id.textView2});
           listView.setAdapter(adapter);

           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   Toast.makeText(getApplicationContext(),"You Selected "+ProductName, Toast.LENGTH_SHORT).show();
               }
           });

       }
   }
   }

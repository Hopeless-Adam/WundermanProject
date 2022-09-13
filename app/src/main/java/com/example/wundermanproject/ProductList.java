package com.example.wundermanproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
    HashMap<String,String> productswithID = new HashMap<>();
    ArrayList<String> productIDs = new ArrayList<String>();

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

               for (int i=10; i<894; i++) {
                    jsonArray.remove(i);
               }

               //i<jsonArray.length() for entire array
               for (int i = 0; i<2; i++){
                   JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                   ProductName = jsonObject1.getString("name");
                   ProductPrice = jsonObject1.getString("cost");
                   ProductID = jsonObject1.getString("prodid");


                   productIDs.add(ProductID);

                   //Hashmap
                   HashMap<String,String> productswithcost = new HashMap<>();
                   productswithcost.put("name", ProductName);
                   productswithcost.put("cost", ProductPrice);


                   productswithID.put(ProductID, ProductName + " " + ProductPrice);

                   /*String image_url = "https://images.riverisland.com/is/image/RiverIsland/"+ProductID+"_main";
                   Picasso.get().load(image_url).into(imageView);*/


                   productList.add(productswithcost);

               }
           } catch (JSONException e) {
               e.printStackTrace();
           }

           ListAdapter adapter = new SimpleAdapter(ProductList.this, productList, R.layout.row_layout, new String[] {"name", "cost"}, new int[]{R.id.textView, R.id.textView2});
           listView.setAdapter(adapter);

           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   Toast.makeText(getApplicationContext(),"You Selected "+ productIDs.get(i) , Toast.LENGTH_SHORT).show();
                   String ProductAtPos = productIDs.get(i);
                   Intent intent = new Intent(ProductList.this, IndividualProduct.class);
                   intent.putExtra("key", ProductAtPos);
                   intent.putExtra("key2", i);
                   startActivity(intent);
               }
           });

       }
   }
   }

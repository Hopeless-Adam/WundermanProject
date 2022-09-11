package com.example.wundermanproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    String Name, Price;
    String jsonString = "https://static-ri.ristack-3.nn4maws.net/v1/plp/en_gb/2506/products.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        ArrayList<String> cars = new ArrayList<>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");

        /*try {
            //String str = "{ \"number\": [3, 4, 5, 6] }";
            JSONObject jsonObject = new JSONObject(jsonString);
            System.out.println(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        //Display Arraylist in Listview
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cars);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}
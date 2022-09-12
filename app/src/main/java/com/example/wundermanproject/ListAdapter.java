/*
package com.example.wundermanproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter {
    private String [] ProductNames;
    private String [] ProductCosts;
    private int imageid;
    private Activity context;

    public ListAdapter(Activity context, String[] ProductNames, String[] ProductCosts, int imageid) {
        super(context, R.layout.row_layout, ProductNames);
        this.context = context;
        this.ProductNames = ProductNames;
        this.ProductCosts = ProductCosts;
        this.imageid = imageid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_layout, null, true);
        TextView textViewName = row.findViewById(R.id.textView);
        TextView textViewCost = row.findViewById(R.id.textView2);
        ImageView ProductImage = row.findViewById(R.id.imageView);

        textViewName.setText(ProductNames[position]);
        textViewCost.setText(ProductCosts[position]);
        ProductImage.setImageResource(imageid[position]);
        return  row;
    }
}
*/

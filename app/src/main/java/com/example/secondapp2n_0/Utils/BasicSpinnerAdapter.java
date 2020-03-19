package com.example.secondapp2n_0.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.R;

import java.util.ArrayList;
import java.util.Map;

public class BasicSpinnerAdapter extends BaseAdapter {

    private ArrayList<String> mSpinnerItems;
    private ArrayList<String> mData;
    private Context mContext;
    ArrayList<String> spinnerItems;
    ArrayList<Parcel> parcels;

    public BasicSpinnerAdapter(ArrayList<String> data, Context context, ArrayList<Parcel> parcels1) {
        mData = data;
        mContext = context;
        mSpinnerItems = spinnerItems;
        parcels=parcels1;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_item_spinner, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.row_item_textview);
        final Spinner spinner = (Spinner) view.findViewById(R.id.row_item_spinner1);


        textView.setText(mData.get(position));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, mSpinnerItems);
        spinner.setAdapter(adapter);
     //   spinner.setSelection(position, false);
        spinner.setSelection(0,false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {

                if(position>0)
                Toast.makeText(mContext,String.valueOf(position)+" Selected" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                Toast.makeText(mContext, "Sppinner Not Selected", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}/*

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BasicSpinnerAdapter extends BaseAdapter {

    private ArrayList<String> mSpinnerItems;
    private ArrayList<String> mData;
    private Context mContext;

    public BasicSpinnerAdapter(ArrayList<String> data, ArrayList<String> spinnerItems, Context context) {
        mData = data;
        mContext = context;
        mSpinnerItems = spinnerItems;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_item_spinner, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.row_item_textview);
        final Spinner spinner = (Spinner) view.findViewById(R.id.row_item_spinner1);


        textView.setText(mData.get(position));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, mSpinnerItems);
        spinner.setAdapter(adapter);
     //   spinner.setSelection(position, false);
        spinner.setSelection(0,false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {

                if(position>0)
                Toast.makeText(mContext,String.valueOf(position)+" Selected" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                Toast.makeText(mContext, "Sppinner Not Selected", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
*/
package com.example.secondapp2n_0.ui.Waiting;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.secondapp2n_0.Entities.Enumes;
import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.R;
import com.example.secondapp2n_0.Utils.Converters;
import com.example.secondapp2n_0.Utils.GPService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DeleverForParcel extends AppCompatActivity {

    ArrayList<String> selectedItems=new ArrayList<String>();;
    ArrayList<String> items = new ArrayList<>();
    DeleverForParcelViewModel deleverForParcelViewModel;
    static Parcel parcel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delever_for_parcel);
        deleverForParcelViewModel = ViewModelProviders.of(this).get(DeleverForParcelViewModel.class);
        parcel=iniParcel();
        setDetails(parcel);

        final ListView listView = (ListView) findViewById(R.id.checkable_list);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        final HashMap<String, Boolean> deliveryList = parcel.getAvailableDeliveries();

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try { itemSelect(view); } catch (Exception e) { }
            }
        });
        deleverForParcelViewModel.getAllParcelsForOwner().observe(this, new Observer<List<Parcel>>() {
                @Override
                public void onChanged(final List<Parcel> parcelList) {
                    items.clear();
                    selectedItems.clear();
                    final HashMap<String, Boolean> deliveryList = parcel.getAvailableDeliveries();
                    for (Map.Entry<String, Boolean> entry : deliveryList.entrySet()) {
                        items.add(entry.getKey());
                        if (entry.getValue())
                            selectedItems.add(entry.getKey());
                    }
                    Collections.sort(items);
                    DeleverAdapter deleverAdapter = new DeleverAdapter(getApplicationContext(), R.layout.cheack_box  , items,selectedItems,parcel);
                    listView.setAdapter(deleverAdapter);
                }
            });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void setDetails(Parcel parcel) {
        Calendar cal = Calendar.getInstance();
        String str = "ID: " + parcel.getID() +
                "\nWarehouseID: " + parcel.getWarehouseID() +
                "\nWarehouseLocation: " + parcel.getWarehouseLocation() +
                "\nParcelType: " + parcel.getParcelType().toString() + "" +
                "\nBreakable: " + parcel.getBreakable().toString() +
                "\nWeight: " + parcel.getWeight().toString() +
                "\nParcelStatus: " + parcel.getParcelStatus() +
                "\nReciviedDate: " + parcel.getReciviedDate().format(cal.getTime()) +
                "\nToLocation: " + GPService.getCompleteAddress(parcel.getToLocation(),getApplicationContext()) +
                "\nTomail: " + parcel.getToMail() +
                "\nToname: " + parcel.getToName() +
                "\nTophoneNumber: " + parcel.getToPhoneNumber() +
                "\nDeliverName: " + parcel.getDeliverName();
        ((TextView)findViewById(R.id.Details)).setText(str);
    }

    public class DeleverAdapter extends ArrayAdapter<String> {
        ArrayList<String> arrayList;
        ArrayList<String> delevers;
        Parcel parcel;
        Context context;

        public DeleverAdapter(@NonNull Context context1, int resource, @NonNull List<String> objects, ArrayList<String> hashMap,Parcel parcel1) {
            super(context1, resource, objects);
            arrayList=(ArrayList<String>) objects;
            parcel=parcel1;
            delevers=hashMap;
            context=context1;
        }
        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {

            final ViewHolder viewHolder;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.cheack_box, null);
                viewHolder.name = (CheckBox) convertView.findViewById(R.id.CheckBox);
                convertView.setTag(viewHolder);
            }
            else {

                viewHolder = (ViewHolder) convertView.getTag();
            }


            viewHolder.name.setText(arrayList.get(position));
            viewHolder.name.setOnClickListener( new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void onClick(View view)
                {
                    try{ itemSelect(view); }catch (Exception e) { }
                }
            });
            if (delevers.contains(arrayList.get(position)))
                viewHolder.name.setChecked(true);
            else viewHolder.name.setChecked(false);
            return convertView;
        }
        private class ViewHolder {
            CheckBox name;
        }

    }
    private void itemSelect(View view) {

        HashMap<String, Boolean> deliveryList = parcel.getAvailableDeliveries();
        String s= (String) ((CheckBox)view).getText();
        Boolean f=deliveryList.get(s);
        Boolean b=false;
        String name = "";
        Boolean name_b = false;

        if(deliveryList.get(s))
        {
            deliveryList.remove(s);
            deliveryList.put(s,!f);
            parcel.setAvailableDeliveries(deliveryList);
            try { deleverForParcelViewModel.setParcelFromOwner(parcel); } catch (Exception e) { }
        }
        else {
            for (Map.Entry<String, Boolean> entry : deliveryList.entrySet()) {
                if (entry.getValue() && (((String)entry.getKey()) != s))
                {
                    b=true;
                    name=entry.getKey();
                    name_b=entry.getValue();
                    break;
                }
            }
            if (b)
            {
                deliveryList.remove(name);
                deliveryList.put(name,!name_b);
            }
            deliveryList.remove(s);
            deliveryList.put(s,!f);
            parcel.setAvailableDeliveries(deliveryList);
            try { deleverForParcelViewModel.setParcelFromOwner(parcel); } catch (Exception e) { }
        }
    }

    public void ParcelArrive(View view)
    {
        parcel.setParcelStatus(Enumes.ParcelStatus.RECIVED);
        try { deleverForParcelViewModel.setParcelFromOwner(parcel); } catch (Exception e) {}
        finish();
    }
    public Parcel iniParcel()
    {
        final Parcel parcel = new Parcel();
        Converters converters=new Converters();
        parcel.setAvailableDeliveries((HashMap<String, Boolean>)getIntent().getSerializableExtra("Deliveries"));
        parcel.setBreakable(getIntent().getBooleanExtra("getBreakable",false));
        parcel.setDeliverName(getIntent().getStringExtra("getDeliverName"));
        parcel.setID(getIntent().getIntExtra("getID",0));
        parcel.setParcelStatus((Enumes.ParcelStatus) getIntent().getSerializableExtra("getParcelStatus"));
        parcel.setParcelType((Enumes.ParcelType) getIntent().getSerializableExtra("getParcelType"));
        parcel.setToLocation(converters.locationFromDatabase(getIntent().getStringExtra("getToLocation")));
        parcel.setToMail(getIntent().getStringExtra("getToMail"));
        parcel.setToName(getIntent().getStringExtra("getToName"));
        parcel.setToPhoneNumber(getIntent().getStringExtra("getToPhoneNumber"));
        parcel.setWarehouseID(getIntent().getStringExtra("getWarehouseID"));
        parcel.setWarehouseLocation(getIntent().getStringExtra("getWarehouseLocation"));
        parcel.setWeight((Enumes.Weight) getIntent().getSerializableExtra("getWeight"));
        parcel.setReciviedDate(converters.dateFromDatabase(getIntent().getStringExtra("getReciviedDate")));
        return parcel;
    }
}




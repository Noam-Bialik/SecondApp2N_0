
package com.example.secondapp2n_0.ui.Waiting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.R;
import com.example.secondapp2n_0.Utils.Converters;
import com.example.secondapp2n_0.Utils.ItemArrayAdapter;
import com.example.secondapp2n_0.ui.MainActivity;

import java.util.ArrayList;

public class WaitingFragment extends Fragment {

    WaitingViewModel waitingViewModel;
    Converters converters;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //registeredParcelsViewModel=ViewModelProviders.of(this).get(WaitingViewModel.class);
        MainActivity activity=(MainActivity)getActivity();
        waitingViewModel=new WaitingViewModel(getContext(),activity.getUserName());
        final View root = inflater.inflate(R.layout.waiting, null);

        final ArrayList<String> allParcelsThat = new ArrayList<String>();
        converters = new Converters();
        waitingViewModel.getAllParcelsForOwner().observe(getViewLifecycleOwner(), new Observer<ArrayList<Parcel>>() {
            @Override
            public void onChanged(final ArrayList<Parcel> parcels) {
                allParcelsThat.clear();
                for (Parcel item : parcels) {
                    allParcelsThat.add("" + (item.getID()));
                }
                ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(getContext(), R.layout.list_item, allParcelsThat);
                ListView parcelsList = root.findViewById(R.id.ParcelsList);
                parcelsList.setAdapter(itemArrayAdapter);
                parcelsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        onParcelClick(parcels.get(position), root,parcels);
                    }
                });
            }
        });
        return root;
    }

    public void onParcelClick(final Parcel parcel, View root, final ArrayList<Parcel> parcels) {
        Intent intent =new Intent(getActivity(),DeleverForParcel.class);
        Converters converters=new Converters();
        intent.putExtra("getParcelType",parcel.getParcelType());
        intent.putExtra("getBreakable",parcel.getBreakable());
        intent.putExtra("getWeight",parcel.getWeight());
        intent.putExtra("getWarehouseLocation",parcel.getWarehouseLocation());
        intent.putExtra("getToName",parcel.getToName());
        intent.putExtra("getToLocation",converters.locationToDatabase(parcel.getToLocation()));
        intent.putExtra("getToPhoneNumber",parcel.getToPhoneNumber());
        intent.putExtra("getToMail",parcel.getToMail());
        intent.putExtra("getReciviedDate",converters.dateToDatabase(parcel.getReciviedDate()));
        intent.putExtra("getParcelStatus",parcel.getParcelStatus());
        intent.putExtra("getDeliverName",parcel.getDeliverName());
        intent.putExtra("getID",parcel.getID());
        intent.putExtra("getWarehouseID",parcel.getWarehouseID());
        intent.putExtra("Deliveries", parcel.getAvailableDeliveries());
        startActivity(intent);
    }
}
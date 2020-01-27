package com.example.secondapp2n_0.ui.RegisteredParcels;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.secondapp2n_0.Data.IParcelsRepository;
import com.example.secondapp2n_0.Data.ParcelsRepository;
import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.MainActivity;
import com.example.secondapp2n_0.R;
import com.example.secondapp2n_0.Utils.Converters;
import com.example.secondapp2n_0.Utils.ItemArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegisteredParcelsFragment extends Fragment {

    private RegisteredParcelsViewModel homeViewModel;
    private IParcelsRepository parcelsRepository;
    private Converters converters=new Converters();
    private ArrayList<String> allParcelsThat = new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parcelsRepository=ParcelsRepository.getInstance();
        final View root = inflater.inflate(R.layout.history_parcels_activity, container, false);
        final TextView textView = root.findViewById(R.id.text_home);


        homeViewModel = ViewModelProviders.of(this).get(RegisteredParcelsViewModel.class);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        parcelsRepository.getAllParcelsForOwner().observe(this, new Observer<List<Parcel>>() {
            @Override
            public void onChanged(final List<Parcel> parcelList) {
                allParcelsThat.clear();
                for (Parcel item : parcelList) {
                    allParcelsThat.add("" + (item.getID()));
                }
                ListView parcelsList=root.findViewById(R.id.ParcelsList);
                ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(getContext(), R.layout.list_item, allParcelsThat);
                parcelsList.setAdapter(itemArrayAdapter);
                parcelsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        onParcelClick(parcelList.get(position));
                    }
                });
            }
        });
        return root;
    }
    public void onParcelClick(final Parcel parcel)
    {
        Calendar cal = Calendar.getInstance();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("you want to take the parcel?");
        String str="ID: "+parcel.getID() +
                "\nWarehouseID: "+parcel.getWarehouseID()+
                "\nWarehouseLocation: "+parcel.getWarehouseLocation()+
                "\nParcelType: "+parcel.getParcelType().toString()+"" +
                "\nBreakable: "+parcel.getBreakable().toString()+
                "\nWeight: "+parcel.getWeight().toString()+
                "\nParcelStatus: "+parcel.getParcelStatus()+
                "\nReciviedDate: "+parcel.getReciviedDate().format(cal.getTime())+
                "\nSendDate: "+parcel.getSendDate().format(cal.getTime())+
                "\nToLocation: "+converters.locationToDatabase(parcel.getToLocation())+
                "\nTomail: "+parcel.getToMail()+
                "\nToname: "+parcel.getToName()+
                "\nTophoneNumber: "+parcel.getToPhoneNumber()+
                "\nDeliverName: "+parcel.getDeliverName();
        alertDialog.setMessage(str);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Parcel parcel1=parcel;

            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }
}
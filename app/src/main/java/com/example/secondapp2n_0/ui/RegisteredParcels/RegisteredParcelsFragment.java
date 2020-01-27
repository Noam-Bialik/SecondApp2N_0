package com.example.secondapp2n_0.ui.RegisteredParcels;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.example.secondapp2n_0.Entities.Enumes;
import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.MainActivity;
import com.example.secondapp2n_0.R;
import com.example.secondapp2n_0.Utils.Converters;
import com.example.secondapp2n_0.Utils.ItemArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisteredParcelsFragment extends Fragment {

    private RegisteredParcelsViewModel homeViewModel;
    private Converters converters;
    private IParcelsRepository parcelsRepository;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        converters=new Converters();
        homeViewModel = ViewModelProviders.of(this).get(RegisteredParcelsViewModel.class);

        final View root = inflater.inflate(R.layout.history_parcels_activity, null);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        parcelsRepository= ParcelsRepository.getInstance();
        final ArrayList<String> allParcelsThat=new ArrayList<String>();

        parcelsRepository.getAllParcelsForOwner().observe(this, new Observer<ArrayList<Parcel>>() {
            @Override
            public void onChanged(final ArrayList<Parcel> parcelList) {
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("ditels of parcel")
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("the parcel arrived", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Parcel parcel1=parcel;
                        parcel1.setParcelStatus(Enumes.ParcelStatus.RECIVED);
                        try {
                            parcelsRepository.setParcelFromOwner(parcel1);
                            startActivity(new Intent(getContext(),MainActivity.class));
                            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
        builder.setMessage(str);


        View checkBoxView = View.inflate(getContext(), R.layout.cheack_box, null);
        CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.checkbox);

        HashMap<String , Boolean> deliveryList=parcel.getAvailableDeliveries();

        for (Map.Entry<String, Boolean> entry : deliveryList.entrySet()) {
            if(entry.getValue())
                checkBox.setChecked(true);
            else  checkBox.setChecked(false);
            checkBox.setText(entry.getKey());
            builder.setView(checkBox);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Save to shared preferences
            }
        });
        builder.show();
    }
    public void onDestroyView() {
        if (getView() != null) {
            ViewGroup parent = (ViewGroup) getView().getParent();
            parent.removeAllViews();
        }
        super.onDestroyView();
    }
}
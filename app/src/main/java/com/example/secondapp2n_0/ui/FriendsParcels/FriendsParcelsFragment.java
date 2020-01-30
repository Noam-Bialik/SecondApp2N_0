package com.example.secondapp2n_0.ui.FriendsParcels;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.secondapp2n_0.Data.IParcelsRepository;
import com.example.secondapp2n_0.Entities.Enumes;
import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.MainActivity;
import com.example.secondapp2n_0.R;
import com.example.secondapp2n_0.Utils.Converters;
import com.example.secondapp2n_0.Utils.GPService;
import com.example.secondapp2n_0.Utils.PlanetAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class FriendsParcelsFragment extends Fragment {

    private FriendsParcelsViewModel friendsParcelsViewModel;
    private ArrayList<String> allParcelThat = new ArrayList<String>();
    private PlanetAdapter aAdpt;
    private Converters converters;
    private String userName;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        friendsParcelsViewModel = ViewModelProviders.of(this).get(FriendsParcelsViewModel.class);
        final View root = inflater.inflate(R.layout.parcels_list_delivery, container, false);
        return root;
    }
}
   /*     userName = getArguments().getString("userName");
        final TextView textView = root.findViewById(R.id.Tital);
        friendsParcelsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        friendsParcelsViewModel = new FriendsParcelsViewModel();
        try {
            friendsParcelsViewModel.getAllParcelsForDelivery().observe(this, new Observer<ArrayList<Parcel>>() {
                @Override
                public void onChanged(final ArrayList<Parcel> parcelsList) {
                    allParcelThat.clear();
                    for (Parcel item : parcelsList) {
                        allParcelThat.add("Name: " + item.getToName() + "Address :" + GPService.getCompleteAddress(item.getToLocation(), getContext()));
                    }
                    ListView ListOfParcel = root.findViewById(R.id.ParcelsList);
                    PlanetAdapter aAdpt = new PlanetAdapter(allParcelThat, getContext());
                    ListOfParcel.setAdapter(aAdpt);
                    ListOfParcel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            onParcelClick(parcelsList.get(position));
                        }
                    });
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
            ListView lv = root.findViewById(R.id.listView);
            aAdpt = new PlanetAdapter(allParcelThat, getActivity());

            lv.setAdapter(aAdpt);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //onParcelClick();
                }
            });
            registerForContextMenu(lv);
            lv.setTextFilterEnabled(true);

            EditText editText = root.findViewById(R.id.editTxt);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count < before) {
                        // We're deleting char so we need to reset the adapter data
                        aAdpt.resetData();
                    }
                    aAdpt.getFilter().filter(s.toString());
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
        return root;
    }

    public void onParcelClick ( final Parcel parcel)
    {
        Calendar cal = Calendar.getInstance();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("ditels of parcel")
                .setPositiveButton("To be a messenger", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Parcel parcel1=parcel;
                        parcel1.setAvailableDeliveries(new HashMap<String, Boolean>(){{put(userName,false);}});
                    }
                })
                .setNegativeButton("pass", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        View checkBoxView = View.inflate(getContext(), R.layout.cheack_box, null);
        CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.checkbox);

        HashMap<String, Boolean> deliveryList = parcel.getAvailableDeliveries();

        for (Map.Entry<String, Boolean> entry : deliveryList.entrySet()) {
            if (entry.getValue())
                checkBox.setChecked(true);
            else checkBox.setChecked(false);
            checkBox.setText(entry.getKey());
            builder.setView(checkBox);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Save to shared preferences
            }
        });
        String str = "ID: " + parcel.getID() +
                "\nWarehouseID: " + parcel.getWarehouseID() +
                "\nWarehouseLocation: " + parcel.getWarehouseLocation() +
                "\nParcelType: " + parcel.getParcelType().toString() + "" +
                "\nBreakable: " + parcel.getBreakable().toString() +
                "\nWeight: " + parcel.getWeight().toString() +
                "\nParcelStatus: " + parcel.getParcelStatus() +
                "\nReciviedDate: " + parcel.getReciviedDate().format(cal.getTime()) +
                "\nSendDate: " + parcel.getSendDate().format(cal.getTime()) +
                "\nToLocation: " + converters.locationToDatabase(parcel.getToLocation()) +
                "\nTomail: " + parcel.getToMail() +
                "\nToname: " + parcel.getToName() +
                "\nTophoneNumber: " + parcel.getToPhoneNumber() +
                "\nDeliverName: " + parcel.getDeliverName();

        builder.setMessage(str);

        builder.show();
    }

}*/
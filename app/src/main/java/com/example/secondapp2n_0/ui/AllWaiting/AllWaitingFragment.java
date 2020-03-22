/*
public class AllWaitingFragment extends Fragment {

    private RecivedViewModel friendsParcelsViewModel;
    ArrayList<String> allParcelThat = new ArrayList<String>();
    PlanetAdapter aAdpt;
    Converters converters;
    String userName;
    ArrayList<Parcel> planetsList1 = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        friendsParcelsViewModel = ViewModelProviders.of(this).get(RecivedViewModel.class);
        final View root = inflater.inflate(R.layout.all_waiting, container, false);
        try {
            friendsParcelsViewModel.getAllParcelsForDelivery().observe(getViewLifecycleOwner(), new Observer<ArrayList<Parcel>>() {
                @Override
                public void onChanged(final ArrayList<Parcel> parcels) {
                    planetsList1.clear();
                    planetsList1.addAll(parcels);

                    AllWaitingFragment.this.aAdpt.notifyDataSetChanged();
                    ((ListView) root.findViewById(R.id.listView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            onParcelClick(parcels.get(position));
                        }
                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        aAdpt = new PlanetAdapter(planetsList1, getContext());
        ListView lv = root.findViewById(R.id.listView);
        lv.setAdapter(aAdpt);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
                onParcelClick(planetsList1.get(position));
            }
        });
        registerForContextMenu(lv);
        lv.setTextFilterEnabled(true);
        EditText editTxt = root.findViewById(R.id.editTxt);
        editTxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //System.out.println("Text ["+s+"] - Start ["+start+"] - Before ["+before+"] - Count ["+count+"]");
                if (count < before) {
                    // We're deleting char so we need to reset the adapter data
                    aAdpt.resetData();
                }
                aAdpt.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }});
        return root;
    }
    public void onParcelClick(Parcel parcel) {
        Calendar cal = Calendar.getInstance();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("you want take this parcel?");
        alertDialog.setMessage("ditels of parcel");
        String str="ID: "+parcel.getID() +
                "\nWarehouseID: "+parcel.getWarehouseID()+
                "\nWarehouseLocation: "+parcel.getWarehouseLocation()+
                "\nParcelType: "+parcel.getParcelType().toString()+"" +
                "\nBreakable: "+parcel.getBreakable().toString()+
                "\nWeight: "+parcel.getWeight().toString()+
                "\nParcelStatus: "+parcel.getParcelStatus()+
                "\nReciviedDate: "+parcel.getReciviedDate().format(cal.getTime())+
                "\nSendDate: "+parcel.getSendDate().format(cal.getTime())+
                "\nToLocation: "+ GPService.getCompleteAddress(parcel.getToLocation(),getContext())+
                "\nTomail: "+parcel.getToMail()+
                "\nToname: "+parcel.getToName()+
                "\nTophoneNumber: "+parcel.getToPhoneNumber()+
                "\nDeliverName: "+parcel.getDeliverName();
        alertDialog.setMessage(str);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }
}
*/

package com.example.secondapp2n_0.ui.AllWaiting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.R;
import com.example.secondapp2n_0.Utils.Converters;
import com.example.secondapp2n_0.Utils.GPService;
import com.example.secondapp2n_0.Utils.PlanetAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AllWaitingFragment extends Fragment {

    ArrayList<String> allParcelsThat = new ArrayList<>();
    ArrayList<Parcel> planetsList1 = new ArrayList<>();
    PlanetAdapter aAdpt;
    String user= AllWaitingViewModel.getUser();
    AllWaitingViewModel allWaitingViewModel;
    Converters converters = new Converters();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //allWaitingViewModel = ViewModelProviders.of(this).get(AllWaitingViewModel.class);
        allWaitingViewModel =new AllWaitingViewModel(getContext());
        final View root = inflater.inflate(R.layout.all_waiting, null);
        final ListView parcelsList = (ListView) root.findViewById(R.id.listView);

        aAdpt = new PlanetAdapter(planetsList1, getContext());
        parcelsList.setAdapter(aAdpt);
        parcelsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
                onParcelClick(planetsList1.get(position),parcelsList);
            }
        });
        registerForContextMenu(parcelsList);
        parcelsList.setTextFilterEnabled(true);
        EditText editTxt = (EditText) root.findViewById(R.id.editTxt);
        editTxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //System.out.println("Text ["+s+"] - Start ["+start+"] - Before ["+before+"] - Count ["+count+"]");
                if (count < before) {
                    // We're deleting char so we need to reset the adapter data
                    aAdpt.resetData();
                }
                aAdpt.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }});
        iniAdpter(parcelsList);
     return root;
    }

    public void onParcelClick(final Parcel parcel, final ListView parcelsList) {
        Calendar cal = Calendar.getInstance();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("you want take this parcel?");
        alertDialog.setMessage("ditels of parcel");
        String str = "ID: " + parcel.getID() +
                "\nWarehouseID: " + parcel.getWarehouseID() +
                "\nWarehouseLocation: " + parcel.getWarehouseLocation() +
                "\nParcelType: " + parcel.getParcelType().toString() + "" +
                "\nBreakable: " + parcel.getBreakable().toString() +
                "\nWeight: " + parcel.getWeight().toString() +
                "\nParcelStatus: " + parcel.getParcelStatus() +
                "\nSendDate: " + parcel.getSendDate().format(cal.getTime()) +
                "\nToLocation: " + GPService.getCompleteAddress(parcel.getToLocation(),getContext()) +
                "\nTomail: " + parcel.getToMail() +
                "\nToname: " + parcel.getToName() +
                "\nTophoneNumber: " + parcel.getToPhoneNumber() +
                "\nDeliverName: " + parcel.getDeliverName();
        alertDialog.setMessage(str);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                HashMap<String , Boolean> hashMap = parcel.getAvailableDeliveries();
                if (!hashMap.containsKey(user)) {
                    hashMap.put("nave", false);
                    parcel.setAvailableDeliveries(hashMap);

                    try {
                        allWaitingViewModel.setParcelFromDelivery(parcel);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    iniAdpter(parcelsList);
                }
                else
                {
                    AlertDialog Dialog = new AlertDialog.Builder(getContext()).create();
                    Dialog.setTitle("Alert");
                    Dialog.setMessage("You are already a messenger");
                    Dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    Dialog.show();
                }
                dialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "no", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {
                HashMap<String , Boolean> hashMap = parcel.getAvailableDeliveries();
                if (hashMap.containsKey(user))
                    if (hashMap.get(user))
                    {
                        AlertDialog Dialog = new AlertDialog.Builder(getContext()).create();
                        Dialog.setTitle("Alert");
                        Dialog.setMessage("You cannot cancel the shipment because the recipient has already approved you");
                        Dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        Dialog.show();
                    }
                    else {
                        hashMap.remove("nave");
                        parcel.setAvailableDeliveries(hashMap);
                        try {
                            allWaitingViewModel.setParcelFromDelivery(parcel);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        iniAdpter(parcelsList);
                        dialog.dismiss();
                    }
                else dialog.dismiss();
            }
        });

        alertDialog.show();
    }
    public void iniAdpter(final ListView parcelsList)
    {
        //ListView listView=((ListView)((View)inflater.inflate(R.layout.all_waiting, null)).findViewById(R.id.listView));

        try {
            //friendsParcelsViewModel.getAllParcelsForDelivery().removeObservers(this);
            allWaitingViewModel.getAllParcelsForDelivery().observe(getViewLifecycleOwner(), new Observer<List<Parcel>>() {
                @Override
                public void onChanged(final List<Parcel> parcelList) {
                    planetsList1.clear();
                    planetsList1.addAll(parcelList);
                    AllWaitingFragment.this.aAdpt.notifyDataSetChanged();
                    parcelsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            onParcelClick(parcelList.get(position),parcelsList);
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.secondapp2n_0.ui.FriendsParcels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.secondapp2n_0.Data.IParcelsDateSource;
import com.example.secondapp2n_0.Data.IParcelsRepository;
import com.example.secondapp2n_0.Data.ParcelDataSource;
import com.example.secondapp2n_0.Data.ParcelsRepository;
import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.R;

import java.util.ArrayList;


public class FriendsParcelsFragment extends Fragment {

    private FriendsParcelsViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(FriendsParcelsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button btn = (Button)root.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getContext(),"hg", Toast.LENGTH_SHORT).show();
                IParcelsRepository a = ParcelsRepository.getInstance();

                try {
                    a.getAllParcelsForDelivery().observe(getViewLifecycleOwner(), new Observer<ArrayList<Parcel>>() {
                        @Override
                        public void onChanged(ArrayList<Parcel> parcels) {
                            ArrayList<Parcel> l = new ArrayList<>(parcels);
                        }
                    });
                    a.getAllParcelsForOwner().observe(getViewLifecycleOwner(), new Observer<ArrayList<Parcel>>() {
                        @Override
                        public void onChanged(ArrayList<Parcel> parcels) {
                            ArrayList<Parcel> l = new ArrayList<>(parcels);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        return root;
    }
}
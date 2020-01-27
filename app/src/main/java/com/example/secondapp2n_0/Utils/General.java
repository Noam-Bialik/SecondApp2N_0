package com.example.secondapp2n_0.Utils;


import com.example.secondapp2n_0.Entities.Parcel;

import java.util.List;

public class General {

  static public int findIndexInParcels(List<Parcel> parcels, int id){
    for(int i = 0;i < parcels.size();i++)
      if(id == parcels.get(i).getID())
        return i;

    return -1;
  }

}
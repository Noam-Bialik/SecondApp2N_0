/*
 * Copyright (C) 2012 jfrankie (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.secondapp2n_0.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.secondapp2n_0.Data.ParcelsRepository;
import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.R;
import com.example.secondapp2n_0.ui.AllWaiting.AllWaitingViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlanetAdapter extends ArrayAdapter<Parcel> implements Filterable {

	private List<Parcel> planetList;
	private Context context;
	private Filter planetFilter;
	private List<Parcel> origPlanetList;
	String filter;
	String user= AllWaitingViewModel.getUser();
	ParcelsRepository parcelsRepository;

	public PlanetAdapter(ArrayList<Parcel> planetList, Context ctx) {
		super(ctx, R.layout.img_row_layout, planetList);
		this.planetList = planetList;
		this.context = ctx;
		this.origPlanetList = planetList;
		parcelsRepository=ParcelsRepository.getInstance(context);
	}
	
	public int getCount() {
		return planetList.size();
	}

	public Parcel getItem(int position) {
		return planetList.get(position);
	}

	public long getItemId(int position) {
		return planetList.get(position).hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		PlanetHolder holder = new PlanetHolder();
		
		// First let's verify the convertView is not null
		if (convertView == null) {
			// This a new view we inflate the new layout
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.img_row_layout, null);
			// Now we can fill the layout with the right values
			TextView tv = (TextView) v.findViewById(R.id.name);
			TextView status = (TextView) v.findViewById(R.id.Status);
			TextView distance = (TextView) v.findViewById(R.id.Distance);


			holder.planetNameView = tv;
			holder.status = status;
			holder.distance=distance;
			v.setTag(holder);
		}
		else
			holder = (PlanetHolder) v.getTag();

		Parcel p = planetList.get(position);
		String str="you want delever my parcel ?";
		HashMap<String , Boolean> hashMap = p.getAvailableDeliveries();

		if (hashMap.containsKey(user))
			if (hashMap.get(user))
				str = "messenger of:"+" "+p.getToName();
			else str="requested to be a messenger of:"+" "+p.getToName();

		holder.status.setText(str);
		holder.planetNameView.setText(p.getToName());
		try {
			int a= (int) GPService.distance(p.getToLocation(),GPService.getLocationFromAddress(p.getWarehouseLocation(),getContext()));
			holder.distance.setText(a+" KM");
		} catch (Exception e) {
			e.printStackTrace();
		}


		return v;
	}

	public void resetData() {
		planetList = origPlanetList;
	}
	
	
	/* *********************************
	 * We use the holder pattern        
	 * It makes the view faster and avoid finding the component
	 * **********************************/
	
	private static class PlanetHolder {
		public TextView planetNameView;
		public TextView status;
		public TextView distance;
	}
	

	
	/*
	 * We create our filter	
	 */
	
	@Override
	public Filter getFilter() {
		if (planetFilter == null)
			planetFilter = new PlanetFilter();
		
		return planetFilter;
	}



	private class PlanetFilter extends Filter {

		
		
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			// We implement here the filter logic
			if (constraint == null || constraint.length() == 0) {
				// No filter implemented we return all the list
				results.values = origPlanetList;
				results.count = origPlanetList.size();
			}
			else {
				// We perform filtering operation
				List<Parcel> nPlanetList = new ArrayList<Parcel>();
				
				for (Parcel p : planetList) {
					if (p.getToName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
						nPlanetList.add(p);
				}
				
				results.values = nPlanetList;
				results.count = nPlanetList.size();

			}
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			
			// Now we have to inform the adapter about the new list filtered
			if (results.count == 0)
				notifyDataSetInvalidated();
			else {
				planetList = (List<Parcel>) results.values;
				notifyDataSetChanged();
			}
			
		}
		
	}
}

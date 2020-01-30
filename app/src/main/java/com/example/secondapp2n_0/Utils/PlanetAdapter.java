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

import com.example.secondapp2n_0.Entities.Parcel;
import com.example.secondapp2n_0.R;

import java.util.ArrayList;
import java.util.List;

public class PlanetAdapter extends ArrayAdapter<Parcel> implements Filterable {

	private ArrayList<String> parcels;
	private Context context;
	private Filter planetFilter;
	private ArrayList<String> origPlanetList;
	
	public PlanetAdapter(ArrayList<String> parcelList, Context ctx) {
		super(ctx,R.layout.list_item);
		//super(ctx, R.layout.list_item ,parcelList);
		this.parcels = parcelList;
		this.context = ctx;
		this.origPlanetList = parcelList;
	}
	
	public int getCount() {
		return parcels.size();
	}

	public String getItem1(int position) {
		return parcels.get(position);
	}

	public long getItemId(int position) {
		return parcels.get(position).hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_item, null);
			return v;
	}

	public void resetData() {
		parcels = origPlanetList;
	}
	
	
	/* *********************************
	 * We use the holder pattern        
	 * It makes the view faster and avoid finding the component
	 * **********************************/
	
	private static class PlanetHolder {
		public TextView planetNameView;
		public TextView distView;
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
				ArrayList<String> nPlanetList = new ArrayList<String>();
				
				for (String p : parcels) {
					if (p.toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
				parcels = (ArrayList<String>) results.values;
				notifyDataSetChanged();
			}
			
		}
		
	}
}
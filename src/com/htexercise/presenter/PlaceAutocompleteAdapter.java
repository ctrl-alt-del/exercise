package com.htexercise.presenter;

import java.util.List;

import com.htexercise.R;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceAutocompleteAdapter extends BaseAdapter implements SearchPresenter {

	Activity activity;
	List<String> places;
	private SearchView searchView;

	public PlaceAutocompleteAdapter(Activity activity, List<String> places) {
		this.activity = activity;
		this.places = places;
	}
	
	@Override
	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

	@Override
	public int getCount() {
		return this.places.size();
	}

	@Override
	public String getItem(int position) {
		return this.places.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final String place = this.getItem(position);

		PlaceAutocompleteRowViewHolder holder;
		if (convertView == null) {

			holder = new PlaceAutocompleteRowViewHolder();

			convertView = View.inflate(this.activity, R.layout.place_autocomplete_row, null);
			holder.description = (TextView) convertView.findViewById(R.id.place_autocomplete_row_description);

			convertView.setTag(holder);
		} else {
			holder = (PlaceAutocompleteRowViewHolder) convertView.getTag();
		}

		holder.description.setText(place);
		
		holder.description.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(activity, place + " is clicked...", Toast.LENGTH_SHORT).show();
				if (searchView != null) {
					searchView.setQuery(place, false);
				} 
			}
		});

		return convertView;
	}



	/**
	 * Class to hold the views of place_autocomplete_row.xml.
	 * 
	 * @since 2014-08-23
	 * @version 1.0
	 * */
	private class PlaceAutocompleteRowViewHolder {

		protected TextView description;
	}
}

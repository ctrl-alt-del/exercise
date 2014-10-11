package com.htexercise.presenter;

import java.util.List;

import com.htexercise.PlaceDetailsActivity;
import com.htexercise.R;
import com.htexercise.model.BundleExtraConstant;
import com.htexercise.model.Prediction;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceAutocompleteAdapter extends BaseAdapter implements SearchPresenter {

	Activity activity;
	List<Prediction> predictions;
	private SearchView searchView;

	public PlaceAutocompleteAdapter(Activity activity, List<Prediction> predictions) {
		this.activity = activity;
		this.predictions = predictions;
	}
	
	@Override
	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

	@Override
	public int getCount() {
		return this.predictions.size();
	}

	@Override
	public Prediction getItem(int position) {
		return this.predictions.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final Prediction prediction = this.getItem(position);

		PlaceAutocompleteRowViewHolder holder;
		if (convertView == null) {

			holder = new PlaceAutocompleteRowViewHolder();

			convertView = View.inflate(this.activity, R.layout.place_autocomplete_row, null);
			holder.description = (TextView) convertView.findViewById(R.id.place_autocomplete_row_description);

			convertView.setTag(holder);
		} else {
			holder = (PlaceAutocompleteRowViewHolder) convertView.getTag();
		}

		holder.description.setText(prediction.getDescription());
		
		holder.description.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(activity, prediction.getDescription() + " is clicked...", Toast.LENGTH_SHORT).show();
				if (searchView != null) {
					searchView.setQuery(prediction.getDescription(), false);
				} 
				// make API request with prediction.getPlaceId()
				
				
				Toast.makeText(activity, BundleExtraConstant.PLACE_DETAILS.getDesc() + " <===", Toast.LENGTH_SHORT).show();
				Intent placeDetialsIntent = new Intent(activity, PlaceDetailsActivity.class);
				placeDetialsIntent.putExtra(BundleExtraConstant.PLACE_DETAILS.getDesc(), prediction.getDescription());
				activity.startActivity(placeDetialsIntent);
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

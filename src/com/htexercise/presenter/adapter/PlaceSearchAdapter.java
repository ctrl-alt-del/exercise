package com.htexercise.presenter.adapter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.htexercise.R;
import com.htexercise.model.BundleExtraConstant;
import com.htexercise.model.Location;
import com.htexercise.model.PlaceDetail;
import com.htexercise.model.Prediction;
import com.htexercise.model.Result;
import com.htexercise.network.ApiClient;
import com.htexercise.view.PlaceSearchViewInterface;
import com.htexercise.view.impl.PlaceDetailViewImpl;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceSearchAdapter extends BaseAdapter {

	private PlaceSearchViewInterface placeSearchViewInterface;
	private Activity activity;
	private List<Prediction> predictions;

	public PlaceSearchAdapter(PlaceSearchViewInterface placeSearchViewInterface, List<Prediction> predictions) {
		this.placeSearchViewInterface = placeSearchViewInterface;
		this.activity = this.placeSearchViewInterface.getActivity();
		this.predictions = predictions;
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

				SearchView searchView = placeSearchViewInterface.getSearchView();
				if (searchView != null) {
					searchView.setQuery(prediction.getDescription(), false);
				} 

				// make API request with prediction.getPlaceId()
				String apiKey = activity.getResources().getString(R.string.API_KEY);
				ApiClient.getApiClient().getPlaceDetails(apiKey, prediction.getPlaceId(), new Callback<PlaceDetail>() {

					@Override
					public void failure(RetrofitError retrofitError) {
						Toast.makeText(activity, "Connection Issue, please try again", Toast.LENGTH_SHORT).show();
						// Toast.makeText(activity, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
					}

					@Override
					public void success(PlaceDetail placeDetail, Response response) {

						Result result = placeDetail.getResult();

						Intent placeDetialsIntent = new Intent(activity, PlaceDetailViewImpl.class);
						placeDetialsIntent.putExtra(
								BundleExtraConstant.PLACE_DETAILS_FORMATTED_ADDRESS.getDesc(), 
								result.getFormattedAddress());

						placeDetialsIntent.putExtra(
								BundleExtraConstant.PLACE_DETAILS_PLACE_ID.getDesc(), 
								result.getPlaceId());

						Location location = result.getGeometry().getLocation();
						placeDetialsIntent.putExtra(
								BundleExtraConstant.PLACE_DETAILS_LOCATION.getDesc(), 
								"Lat: " + location.getLat() + ", Lng: " + location.getLng());

						activity.startActivity(placeDetialsIntent);
					}

				});
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

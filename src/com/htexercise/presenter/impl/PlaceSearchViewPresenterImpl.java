package com.htexercise.presenter.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.htexercise.R;
import com.htexercise.model.BundleExtraConstant;
import com.htexercise.model.Location;
import com.htexercise.model.PlaceAutocomplete;
import com.htexercise.model.PlaceDetail;
import com.htexercise.model.Prediction;
import com.htexercise.model.Result;
import com.htexercise.network.ApiClient;
import com.htexercise.presenter.adapter.PlaceSearchAdapter;
import com.htexercise.view.PlaceSearchViewInterface;
import com.htexercise.view.impl.PlaceDetailsViewImpl;

/**
 * PlaceSearchViewPresenter class
 * 
 * @since 2014-10-11
 * @version 1.0
 * */
public class PlaceSearchViewPresenterImpl implements OnQueryTextListener {

	private PlaceSearchViewInterface placeSearchViewInterface;
	private PlaceSearchAdapter placeAutocompleteAdapter;
	private Activity activity;
	
	private SearchView searchView;

	private List<Prediction> placeAutocompletes;
	private ListView placeAutocompletesListView;

	private int queryIncrement = 0;


	public PlaceSearchViewPresenterImpl(PlaceSearchViewInterface placeSearchViewInterface) {

		this.placeSearchViewInterface = placeSearchViewInterface;
		this.activity = this.placeSearchViewInterface.getActivity();
		this.placeAutocompletes = new LinkedList<Prediction>();
		
		this.placeAutocompleteAdapter = new PlaceSearchAdapter(
				this.placeSearchViewInterface, placeAutocompletes);

	}

	public PlaceSearchAdapter getPlaceSearchAdapter() {
		return this.placeAutocompleteAdapter;
	}
	
	@Override
	public boolean onQueryTextChange(String query) {

		this.searchView = placeSearchViewInterface.getSearchView();

		/*
		 * When the search widget is first clicked or filled with space(s), 
		 * update the hint on the widget to tell users what to do. It makes 
		 * sure that request with "" or " " query won't be sent.
		 * */
		if (StringUtils.isBlank(query)) {
			/*
			 * hide the list view because nothing is there to display
			 * */
			this.hideListView();
			
			this.searchView.setQueryHint("Enter your place here");
			return true;
		}

		/*
		 * As long as there is something on the search widget, the list
		 * view can be showed.
		 * */
		this.showListView();
		
		/*
		 * Selecting an item that already on the list .
		 * */
		if (placeAutocompletes.contains(query)) {
			return true;
		}

		/*
		 * textCount determines how frequent an API call should be fired
		 * and the StringUtils.isBlank(query) make sure that it won't 
		 * request for an API call with empty, "", " " query
		 * */
		if (queryIncrement < 2) {
			// Not trigger API call
			queryIncrement++;
			//			Toast.makeText(getBaseContext(), "onQueryTextChange -> " + query, Toast.LENGTH_SHORT).show();
		} else {
			// Trigger API call
			
			String apiKey = activity.getResources().getString(R.string.API_KEY);
			ApiClient.getApiClient().getPlaceAutocompletes(apiKey, query, new Callback<PlaceAutocomplete>() {

				@Override
				public void failure(RetrofitError retrofitError) {
					//					Toast.makeText(getBaseContext(), "Connection Issue, please try again", Toast.LENGTH_SHORT).show();
					Toast.makeText(activity, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
				}

				@Override
				public void success(PlaceAutocomplete placeAutocomplete,
						Response response) {

					placeAutocompletes.clear();
					for (Prediction prediction : placeAutocomplete.getPredictions()) {
						placeAutocompletes.add(prediction);
					}
					
					/*
					 * notify the {@link PlaceSearchView} to update its ListView
					 * */
					placeAutocompleteAdapter.notifyDataSetChanged();
				}

			});
			
			/*
			 * reset the counter 
			 * */
			queryIncrement = 0;
		}
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		/**
		 * Ignore user action on submitting empty query.
		 * */
		if (StringUtils.isBlank(query)) {
			Toast.makeText(activity, "type something maybe?", Toast.LENGTH_SHORT).show();
			return true;
		}


		if (placeAutocompletes != null && placeAutocompletes.size() > 0) {

			// get the place_id of the first item on the autocomplete list and make API request
			Prediction prediction = placeAutocompletes.get(0);
			String apiKey = activity.getResources().getString(R.string.API_KEY);

			ApiClient.getApiClient().getPlaceDetails(apiKey, prediction.getPlaceId(), new Callback<PlaceDetail>() {

				@Override
				public void failure(RetrofitError retrofitError) {
					//					Toast.makeText(getBaseContext(), "Connection Issue, please try again", Toast.LENGTH_SHORT).show();
					Toast.makeText(activity, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
				}

				@Override
				public void success(PlaceDetail placeDetail,
						Response response) {

					Result result = placeDetail.getResult();

					Intent placeDetialsIntent = new Intent(activity, PlaceDetailsViewImpl.class);
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
		return true;
	}

	private void showListView() {
		this.placeAutocompletesListView = placeSearchViewInterface.getListView();
		this.placeAutocompletesListView.setVisibility(View.VISIBLE);
	}

	private void hideListView() {
		this.placeAutocompletesListView = placeSearchViewInterface.getListView();
		this.placeAutocompletesListView.setVisibility(View.INVISIBLE);
	}
}

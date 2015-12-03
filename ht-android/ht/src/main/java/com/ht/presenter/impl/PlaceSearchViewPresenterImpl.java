package com.ht.presenter.impl;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.ht.R;
import com.ht.model.BundleExtraConstant;
import com.ht.model.Location;
import com.ht.model.PlaceAutocomplete;
import com.ht.model.PlaceDetail;
import com.ht.model.Prediction;
import com.ht.model.Result;
import com.ht.network.ApiClient;
import com.ht.network.PlaceAPIs;
import com.ht.presenter.PlaceSearchViewPresenterInterface;
import com.ht.presenter.adapter.PlaceSearchAdapter;
import com.ht.view.PlaceSearchViewInterface;
import com.ht.view.impl.PlaceDetailViewImpl;

import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * PlaceSearchViewPresenter class
 *
 * @version 1.0
 * @since 2014-10-11
 */
public class PlaceSearchViewPresenterImpl
        implements PlaceSearchViewPresenterInterface, OnQueryTextListener {

    private PlaceSearchViewInterface placeSearchViewInterface;
    private PlaceSearchAdapter placeSearchAdapter;
    private Activity activity;

    private SearchView searchView;

    private List<Prediction> places;
    private ListView placeSearchListView;

    /*
     * make sure there is only one keystrokesCount
     * */
    private static int keystrokesCount = 0;


    public PlaceSearchViewPresenterImpl(PlaceSearchViewInterface placeSearchViewInterface) {

        this.placeSearchViewInterface = placeSearchViewInterface;
        this.activity = this.placeSearchViewInterface.getActivity();
        this.places = new LinkedList<Prediction>();

        this.placeSearchAdapter = new PlaceSearchAdapter(
                this.placeSearchViewInterface, places);

    }

    @Override
    public PlaceSearchAdapter getPlaceSearchAdapter() {
        return this.placeSearchAdapter;
    }

    @Override
    public boolean onQueryTextChange(String query) {

        this.searchView = placeSearchViewInterface.getSearchView();

		/*
         * When the search widget is first clicked or filled with space(s),
		 * update the hint on the widget to tell users what to do. It makes 
		 * sure that request with "" or " " query won't be sent.
		 * */
        if (TextUtils.isEmpty(query)) {
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
		 * keystrokesCount determines how frequent an API call should be fired.
		 * In this case, API request will be made on every three keystrokes
		 * except the case that query.trim().length() == 1, a.k.a the first letter.
		 * 
		 * trim() is used to prevent user inputs, such as "  s", "s " and etc.
		 * 
		 * query.length() == 0 has been eliminated by condition prior to this one. 
		 * */
        if (keystrokesCount < 2 && query.trim().length() > 1) {
            // Not trigger API call
            keystrokesCount++;
        } else {
            // Trigger API call

            String apiKey = activity.getResources().getString(R.string.API_KEY);
            PlaceAPIs client = ApiClient.getApiClient(activity);

            if (client == null) {
                return true;
            }

            client.getPlaceAutocompletes(apiKey, query, new Callback<PlaceAutocomplete>() {

                @Override
                public void failure(RetrofitError retrofitError) {
                    Toast.makeText(activity, "Connection Issue, please try again", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(activity, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void success(PlaceAutocomplete placeAutocomplete,
                                    Response response) {

                    places.clear();
                    places.addAll(placeAutocomplete.getPredictions());

					/*
					 * notify the {@link PlaceSearchView} to update its ListView
					 * */
                    placeSearchAdapter.notifyDataSetChanged();
                }

            });

			/*
			 * reset keystrokesCount 
			 * */
            keystrokesCount = 0;
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
		/*
		 * User action on submitting empty query.
		 * */
        if (TextUtils.isEmpty(query)) {
            Toast.makeText(activity, "type something maybe?", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (places.size() == 0) {
            Toast.makeText(activity, "try typing more", Toast.LENGTH_SHORT).show();
            return true;
        }
		
		/* 
		 * If user composes a random query and press submit, it should get the 
		 * place_id of the first item on the auto complete list unless 
		 * the query matches one of the description of the items on the list. 
		 * */
        Prediction prediction = places.get(0);
        for (Prediction place : places) {
            if (place.getDescription().equalsIgnoreCase(query)) {
                prediction = place;
                break;
            }
        }
		
		/*
		 * In case that no description on the auto complete list matches the 
		 * query of user's submission, it will set the query to be the description 
		 * of the first item on the auto complete list.
		 * */
        if (prediction == places.get(0)) {
            searchView.setQuery(prediction.getDescription(), false);
        }

        String apiKey = activity.getResources().getString(R.string.API_KEY);

        PlaceAPIs client = ApiClient.getApiClient(activity);

        if (client == null) {
            return true;
        }

        client.getPlaceDetails(apiKey, prediction.getPlaceId(), new Callback<PlaceDetail>() {

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(activity, "Connection Issue, please try again", Toast.LENGTH_SHORT).show();
                // Toast.makeText(activity, retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void success(PlaceDetail placeDetail,
                                Response response) {

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
        return true;
    }

    private void showListView() {
        this.placeSearchListView = placeSearchViewInterface.getListView();
        this.placeSearchListView.setVisibility(View.VISIBLE);
    }

    private void hideListView() {
        this.placeSearchListView = placeSearchViewInterface.getListView();
        this.placeSearchListView.setVisibility(View.INVISIBLE);
    }
}

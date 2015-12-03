package com.ht.network;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import retrofit.RestAdapter;

public class ApiClient {

	private static final String API_ENDPOINT = "https://maps.googleapis.com/maps/api";
	private static PlaceAPIs sApiClient;

	public static PlaceAPIs getApiClient(Activity activty) {

		/*
		 * Check if the device has Internet connection
		 * */
		ConnectivityManager cm = (ConnectivityManager) activty
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		
		boolean isConnected = activeNetwork != null &&
				activeNetwork.isConnectedOrConnecting();
		
		if (!isConnected) {
			Toast.makeText(activty, "Please connect to internet...", Toast.LENGTH_SHORT).show();
			return null;
		}

		if (sApiClient == null) {
			RestAdapter restAdapter = new RestAdapter.Builder()
			.setEndpoint(API_ENDPOINT)
			.build();

			sApiClient = restAdapter.create(PlaceAPIs.class);
		}

		return sApiClient;
	}
}

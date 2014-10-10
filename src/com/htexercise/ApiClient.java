package com.htexercise;

import retrofit.RestAdapter;

public class ApiClient {

	private static final String API_ENDPOINT = "https://maps.googleapis.com/maps/api";
	private static PlaceAPIs sTwitchTvService;

    public static PlaceAPIs getApiClient() {
    	
        if (sTwitchTvService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_ENDPOINT)
                    .build();

            sTwitchTvService = restAdapter.create(PlaceAPIs.class);
        }

        return sTwitchTvService;
    }
}

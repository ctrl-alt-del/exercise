package com.ht.network;

import com.ht.model.PlaceAutocomplete;
import com.ht.model.PlaceDetail;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface PlaceAPIs {

	/**
	 * Method to get autocompletes of a given substring of a place through Google
	 * Place API
	 * 
	 * @param key Google API key
	 * @param input the substring of a place
	 * @param callback the async call back
	 * 
	 * @version 1.0
	 * */
	@GET("/place/autocomplete/json")
	void getPlaceAutocompletes(
			@Query("key") String apiKey, 
			@Query("input") String text, 
			Callback<PlaceAutocomplete> callback);

	/**
	 * Method to get details of a place through Google Place API
	 * 
	 * @param key Google API key
	 * @param placeid the id of a place
	 * @param callback the async call back
	 * 
	 * @version 1.0
	 * */
	@GET("/place/details/json")
	void getPlaceDetails(
			@Query("key") String apiKey, 
			@Query("placeid") String placeId, 
			Callback<PlaceDetail> callback);


}

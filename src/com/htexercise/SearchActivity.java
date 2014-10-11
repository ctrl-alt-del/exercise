package com.htexercise;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htexercise.model.BundleExtraConstant;
import com.htexercise.model.PlaceAutocomplete;
import com.htexercise.model.Prediction;
import com.htexercise.presenter.PlaceAutocompleteAdapter;
import com.htexercise.view.SearchActivityView;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class SearchActivity extends Activity implements SearchActivityView {

	private ListView placeAutocompletesListView;
	private PlaceAutocompleteAdapter placeAutocompletesAdapter;
	private List<String> placeAutocompletes = new LinkedList<String>();
	private SearchView searchView;
	private Activity activity;

//	private static final Gson gson = new GsonBuilder().create();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		this.activity = this;
		this.placeAutocompletesAdapter = new PlaceAutocompleteAdapter(this, placeAutocompletes);
		this.placeAutocompletesListView = (ListView) findViewById(R.id.place_autocomplete_listview);
		this.placeAutocompletesListView.setAdapter(this.placeAutocompletesAdapter);
		
		
//		String JSON_DB = "{\"predictions\" : [{\"description\" : \"San Francisco, CA, United States\",\"id\" : \"1b9ea3c094d3ac23c9a3afa8cd4d8a41f05de50a\",\"matched_substrings\" : [{\"length\" : 3,\"offset\" : 0}],\"place_id\" : \"ChIJIQBpAG2ahYAR_6128GcTUEo\",\"reference\" : \"CkQ4AAAAzdplvAZhSGk_MZ04zHVizU3O7-0XfZ4guzTl43mtRBbqXnLTCnrvkjZDP_tE3sjKsqnCscom1809lTvIkwFokhIQMalx2NlO-JFsnM6wyrnlbBoU3kmjYOQgeNYhyrZOVKSkhnEvpNo\",\"terms\" : [{\"offset\" : 0,\"value\" : \"San Francisco\"},{\"offset\" : 15,\"value\" : \"CA\"},{\"offset\" : 19,\"value\" : \"United States\"}],\"types\" : [ \"locality\", \"political\", \"geocode\" ]},{\"description\" : \"The Art Institute of California - San Francisco, Market Street, San Francisco, CA, United States\",\"id\" : \"b21ec4b16049724662f1ff92e0a64bec83789ba8\",\"matched_substrings\" : [{\"length\" : 3,\"offset\" : 34}],\"place_id\" : \"ChIJW8n8M5uAhYARA0t6rYrjXBc\",\"reference\" : \"CnRuAAAAbLfG3ebd6PSJEoLZac4c0aq2vSUVDpuIn_SEI2C8kU_qn73PUPqSHq4D3BPJBts2w5E586OuaCZFpm2b8bDPb0rLMRhHAbjnk3kpZfp0P5ZzBPfwONSAsihei8kLQnRoodC51ves3oa-a9cbS4z9OxIQ3_VOgNPB40ZlN7KTurNsORoUey5SEEKchzaS14tdzjK43Ym2w_s\",\"terms\" : [{\"offset\" : 0,\"value\" : \"The Art Institute of California - San Francisco\"},{\"offset\" : 49,\"value\" : \"Market Street\"},{\"offset\" : 64,\"value\" : \"San Francisco\"},{\"offset\" : 79,\"value\" : \"CA\"},{\"offset\" : 83,\"value\" : \"United States\"}],\"types\" : [ \"establishment\" ]},{\"description\" : \"Grand Hyatt San Francisco, Stockton Street, San Francisco, CA, United States\",\"id\" : \"84180298b1fa686ec18c8cb49133fc834557cacc\",\"matched_substrings\" : [{\"length\" : 3,\"offset\" : 12}],\"place_id\" : \"ChIJv01Os46AhYARz43hFvD0kCQ\",\"reference\" : \"CmRaAAAAo5mF8AwK40u05R5CeqoYsL53838Fli4LlqiCyB3C6LjG3UP04bAlggn9SXvYkQK2Yp5Fr6Sik4mywJkS52iehkj2IcbS59KDaDtGhfAk5MphOrpo4COZ_RvvsY1T2L0gEhAIWNhBDuSkrPN2qLPApkd-GhQzQcnVMg6c_eGFc_IKn5PPnQj-Og\",\"terms\" : [{\"offset\" : 0,\"value\" : \"Grand Hyatt San Francisco\"},{\"offset\" : 27,\"value\" : \"Stockton Street\"},{\"offset\" : 44,\"value\" : \"San Francisco\"},{\"offset\" : 59,\"value\" : \"CA\"},{\"offset\" : 63,\"value\" : \"United States\"}],\"types\" : [ \"establishment\" ]},{\"description\" : \"JW Marriott San Francisco Union Square, Mason Street, San Francisco, CA, United States\",\"id\" : \"bb43b2daaf9ef2767048184a38da1f8b3e5e2b01\",\"matched_substrings\" : [{\"length\" : 3,\"offset\" : 12}],\"place_id\" : \"ChIJrbjkZo6AhYARZpLDu1Jr2A8\",\"reference\" : \"CnRkAAAAWZQdP9jATkHY7uh-F3OxELO7yWGgC20VpAToIdWo8fG8YHbrI_LAVgSv-TxElS_1mqjFea0eOIEgbs1yes7fOBzTH2UpPVuIlE16GAuWW6CLAJNop7f0uGtWX8eAFLJaUSIZWMXvbH-BohBduGElZxIQHq_uUaYzdKj4qn_Ti4WuzxoU8JjnRHZk1jI_GzR-zC1jHic1mGk\",\"terms\" : [{\"offset\" : 0,\"value\" : \"JW Marriott San Francisco Union Square\"},{\"offset\" : 40,\"value\" : \"Mason Street\"},{\"offset\" : 54,\"value\" : \"San Francisco\"},{\"offset\" : 69,\"value\" : \"CA\"},{\"offset\" : 73,\"value\" : \"United States\"}],\"types\" : [ \"establishment\" ]},{\"description\" : \"San Francisco Symphony, Van Ness Avenue, San Francisco, CA, United States\",\"id\" : \"535d27082c633f0d4034fb4ce954cacdb26b001c\",\"matched_substrings\" : [{\"length\" : 3,\"offset\" : 0}],\"place_id\" : \"ChIJTTrbupiAhYARMJChTbbaB_Y\",\"reference\" : \"CmRYAAAAyxmj8sn9l-somFpR06wpfbUH8Osq4W2Kpy2dlffgXfE0H-PE21fiAP27lu2s8uGZEFxRGQ0NZX9DRJPoImpwEpnN29zbUfgWr_9VnipNkLW3pBJm9Fgc7aw7ZOuO6NMyEhD-eAfAq6hCOgbuW4OM2MtFGhRKP9_4a81DYVoT3vRgmivRhmCrYw\",\"terms\" : [{\"offset\" : 0,\"value\" : \"San Francisco Symphony\"},{\"offset\" : 24,\"value\" : \"Van Ness Avenue\"},{\"offset\" : 41,\"value\" : \"San Francisco\"},{\"offset\" : 56,\"value\" : \"CA\"},{\"offset\" : 60,\"value\" : \"United States\"}],\"types\" : [ \"establishment\" ]}],\"status\" : \"OK\"}";
//		
//		
//		PlaceAutocomplete placeAutocomplete = gson.fromJson(JSON_DB, PlaceAutocomplete.class);
//
//		for (Prediction each : placeAutocomplete.getPredictions()) {
//			placeAutocompletes.add(each.getDescription());
//		}		
		
		hideListView();

	}

	private int textCount = 0;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		
		/**
		 * Only works on SDK 11 or higher 
		 * */
	    this.searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
	    
	    this.placeAutocompletesAdapter.setSearchView(searchView);
	    
	    searchView.setOnQueryTextListener(new OnQueryTextListener() { 

			@Override 
			public boolean onQueryTextChange(String query) {
				
				/**
				 * When the search widget is first clicked, update the hint on 
				 * the widget to tell users what to do
				 * */
				if (query.length() == 0) {
					searchView.setQueryHint("Enter your place here");
					hideListView();
					return true;
				}
				
				showListView();
				/**
				 * Selecting an item that already on the list 
				 * */
				if (placeAutocompletes.contains(query)) {
					return true;
				}
				
				if (query.length() > 1 && textCount < 2) {
					// Not trigger API call
					textCount++;
					Toast.makeText(getBaseContext(), "onQueryTextChange -> " + query, Toast.LENGTH_SHORT).show();
				} else {
					// Trigger API call
					placeAutocompletes.add(query);
					placeAutocompletesAdapter.notifyDataSetChanged();
					textCount = 0;
				}
				
				return true; 
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				
				/**
				 * Ignore user action on submitting empty query.
				 * */
				if (StringUtils.isBlank(query)) {
					Toast.makeText(getBaseContext(), "type something maybe?", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				
				searchView.setQuery("--> " + query, false);
				
				if (placeAutocompletes != null && placeAutocompletes.size() > 0) {
					placeAutocompletes.get(0);
					
//					Toast.makeText(getBaseContext(), "onQueryTextSubmit -> " + query, Toast.LENGTH_SHORT).show();
					
					// get the place_id of the first item on the autocomplete list and make API request
					
					Toast.makeText(activity, BundleExtraConstant.PLACE_DETAILS.getDesc() + " <===", Toast.LENGTH_SHORT).show();
					Intent placeDetialsIntent = new Intent(activity, PlaceDetailsActivity.class);
					placeDetialsIntent.putExtra(BundleExtraConstant.PLACE_DETAILS.getDesc(), query);
					activity.startActivity(placeDetialsIntent);
				}
				
				
				return true;
			} 

		});
	    
	    searchView.setQueryRefinementEnabled(true);
	    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

	    return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(this, "Setting is click..", Toast.LENGTH_SHORT).show();
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void showListView() {
		this.placeAutocompletesListView.setVisibility(View.VISIBLE);
	}


	@Override
	public void hideListView() {
		this.placeAutocompletesListView.setVisibility(View.INVISIBLE);
	}

	
}

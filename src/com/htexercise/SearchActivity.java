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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		this.activity = this;
		this.placeAutocompletesAdapter = new PlaceAutocompleteAdapter(this, placeAutocompletes);
		this.placeAutocompletesListView = (ListView) findViewById(R.id.place_autocomplete_listview);
		this.placeAutocompletesListView.setAdapter(this.placeAutocompletesAdapter);
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

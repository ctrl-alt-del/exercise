package com.htexercise;

import java.util.LinkedList;
import java.util.List;

import com.htexercise.presenter.PlaceAutocompleteAdapter;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class SearchActivity extends Activity {

	private ListView placeAutocompletesListView;
	private PlaceAutocompleteAdapter placeAutocompletesAdapter;
	private List<String> placeAutocompletes = new LinkedList<String>();
	private SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		this.placeAutocompletesAdapter = new PlaceAutocompleteAdapter(this, placeAutocompletes);
		this.placeAutocompletesListView = (ListView) findViewById(R.id.place_autocomplete_listview);
		this.placeAutocompletesListView.setAdapter(this.placeAutocompletesAdapter);

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
				
				if (query.length() == 0) {
					searchView.setQueryHint("Enter your place here");
				} else if (query.length() > 1 && textCount < 2) {
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
				
				Toast.makeText(getBaseContext(), "onQueryTextSubmit -> " + query, Toast.LENGTH_SHORT).show();
				searchView.setQuery("--> " + query, false);
				return true;
			} 

		});
	    
	    
	    // Assumes current activity is the searchable activity
	    searchView.setQueryRefinementEnabled(true);
	    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//	    searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default


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

	
}

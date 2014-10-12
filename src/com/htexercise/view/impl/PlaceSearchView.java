package com.htexercise.view.impl;


import com.htexercise.R;
import com.htexercise.presenter.PlaceSearchViewPresenter;
import com.htexercise.view.PlaceSearchViewInterface;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class PlaceSearchView extends Activity implements PlaceSearchViewInterface, OnQueryTextListener {

	private ListView placeSearchListView;

	private SearchView searchView;
	private PlaceSearchViewPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		this.presenter = new PlaceSearchViewPresenter(this);

		this.placeSearchListView = (ListView) findViewById(R.id.place_autocomplete_listview);
		this.placeSearchListView.setVisibility(View.INVISIBLE);
		this.placeSearchListView.setAdapter(this.presenter.getPlaceSearchAdapter());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		/**
		 * Only works on SDK 11 or higher 
		 * */
		this.searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		this.searchView.setOnQueryTextListener(this);
		this.searchView.setQueryRefinementEnabled(true);
		this.searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

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
	public Activity getActivity() {
		return this;
	}

	@Override
	public SearchView getSearchView() {
		return this.searchView;
	}

	@Override 
	public boolean onQueryTextChange(String query) {
		return this.presenter.onQueryTextChange(query);
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		return this.presenter.onQueryTextSubmit(query);
	}

	@Override
	public ListView getListView() {
		return this.placeSearchListView;
	}

}

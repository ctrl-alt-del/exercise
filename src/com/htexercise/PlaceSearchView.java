package com.htexercise;

import java.util.LinkedList;
import java.util.List;

import com.htexercise.model.Prediction;
import com.htexercise.presenter.PlaceSearchAdapter;
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

	private ListView placeAutocompletesListView;
	private List<Prediction> placeAutocompletes;

	private SearchView searchView;
	private PlaceSearchViewPresenter presenter;
	private PlaceSearchAdapter placeAutocompleteAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		this.presenter = new PlaceSearchViewPresenter(this);

		this.placeAutocompletes = new LinkedList<Prediction>();

		this.placeAutocompleteAdapter = new PlaceSearchAdapter(
				this, placeAutocompletes);

		this.placeAutocompletesListView = (ListView) findViewById(R.id.place_autocomplete_listview);
		this.placeAutocompletesListView.setVisibility(View.INVISIBLE);
		this.placeAutocompletesListView.setAdapter(this.placeAutocompleteAdapter);

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
		this.presenter.setQueryTextChangeAction(query);
		return true; 
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		this.presenter.setQueryTextSubmitAction(query);
		return true;
	}

	@Override
	public ListView getListView() {
		return this.placeAutocompletesListView;
	}

	@Override
	public List<Prediction> getPlaceAutocompletes() {
		return this.placeAutocompletes;
	}

	@Override
	public void updateListView() {
		this.placeAutocompleteAdapter.notifyDataSetChanged();
	}
}

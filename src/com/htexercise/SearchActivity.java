package com.htexercise;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		LinkedList<String> places = new LinkedList<String>();
		places.add("San Francisco");
		places.add("New York");

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setListNavigationCallbacks(
				new PlaceAdapter(this, places), 
				new ActionBar.OnNavigationListener() {
					@Override
					public boolean onNavigationItemSelected(int itemPosition, long itemId) {
						Toast.makeText(getBaseContext(), "An item is selected...", Toast.LENGTH_SHORT).show();
						return true;
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(this, "Setting is click..", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_search:
			Toast.makeText(this, "Search is click..", Toast.LENGTH_SHORT).show();
//			break;
			return true;
		default:
			break;
		}
return true;
//		return super.onOptionsItemSelected(item);
	public class PlaceAdapter extends BaseAdapter {

		Activity activity;
		List<String> places;

		public PlaceAdapter(Activity activity, List<String> places) {
			this.activity = activity;
			this.places = places;
		}

		@Override
		public int getCount() {
			return this.places.size();
		}

		@Override
		public String getItem(int position) {
			return this.places.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			final String place = this.getItem(position);

			PlaceAutocompleteRowViewHolder holder;
			if (convertView == null) {

				holder = new PlaceAutocompleteRowViewHolder();

				convertView = View.inflate(this.activity, R.layout.place_autocomplete_row, null);
				holder.description = (TextView) convertView.findViewById(R.id.place_autocomplete_row_description);

				convertView.setTag(holder);
			} else {
				holder = (PlaceAutocompleteRowViewHolder) convertView.getTag();
			}

			holder.description.setText(place);
			holder.description.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(getBaseContext(), place + " is clicked...", Toast.LENGTH_SHORT).show();

				}
			});

			return convertView;
		}
	}

	/**
	 * Class to hold the views of place_autocomplete_row.xml.
	 * 
	 * @since 2014-08-23
	 * @version 1.0
	 * */
	private class PlaceAutocompleteRowViewHolder {

		protected TextView description;
	}

}

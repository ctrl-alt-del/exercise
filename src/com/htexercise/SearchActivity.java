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
	}

}

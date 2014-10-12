package com.htexercise.view.impl;

import com.htexercise.R;
import com.htexercise.presenter.PlaceDetailViewPresenter;
import com.htexercise.view.PlaceDetailViewInterface;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * PlaceDetailsViewImpl class
 * 
 * @since 2014-10-11
 * @version 1.0
 * */
public class PlaceDetailsViewImpl extends Activity 
implements OnClickListener, PlaceDetailViewInterface {

	private PlaceDetailViewPresenter placeDetailViewPresenter;
	
	private TextView addressTextView;
	private TextView locationTextView;
	private TextView placeIdTextView;
	private Button cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_details);

		getWindow().setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		this.addressTextView = (TextView) this.findViewById(R.id.place_details_address);
		this.locationTextView = (TextView) this.findViewById(R.id.place_details_location);
		this.placeIdTextView = (TextView) this.findViewById(R.id.place_details_place_id);
		this.cancel = (Button) this.findViewById(R.id.place_details_cancel);
		
		this.placeDetailViewPresenter = new PlaceDetailViewPresenter(this);
		this.placeDetailViewPresenter.setAddressOnView();
		this.placeDetailViewPresenter.setLocationOnView();
		this.placeDetailViewPresenter.setPlaceIdOnView();
		
		this.cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.place_details_cancel:
			this.onBackPressed();
		default:
			break;
		}
	}

	@Override
	public Bundle getBundleExtras() {
		return this.getIntent().getExtras();
	}

	@Override
	public void setAddress(String address) {
		this.addressTextView.setText(address);
	}

	@Override
	public void setLocation(String location) {
		this.locationTextView.setText(location);		
	}

	@Override
	public void setPlaceId(String placeId) {
		this.placeIdTextView.setText(placeId);		
	}
}

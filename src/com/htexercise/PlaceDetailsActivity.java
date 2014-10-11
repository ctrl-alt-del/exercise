package com.htexercise;

import org.apache.commons.lang3.StringUtils;

import com.htexercise.model.BundleExtraConstant;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PlaceDetailsActivity extends Activity implements OnClickListener {

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
		
		this.cancel.setOnClickListener(this);
		
		Bundle extras = getIntent().getExtras();
		  if (extras != null) {
		   String address= extras.getString(BundleExtraConstant.PLACE_DETAILS_FORMATTED_ADDRESS.getDesc());
		   if (!StringUtils.isBlank(address)) {
		       this.addressTextView.setText(address);
		   }        
		   
		   String location= extras.getString(BundleExtraConstant.PLACE_DETAILS_LOCATION.getDesc());
		   if (!StringUtils.isBlank(location)) {
		       this.locationTextView.setText(location);
		   }      
		   
		   String placeId= extras.getString(BundleExtraConstant.PLACE_DETAILS_PLACE_ID.getDesc());
		   if (!StringUtils.isBlank(placeId)) {
		       this.placeIdTextView.setText(placeId);
		   }      
		}
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
}

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

	private TextView address;
	private TextView location;
	private TextView placeId;
	private Button cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_details);

		getWindow().setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		this.address = (TextView) this.findViewById(R.id.place_details_address);
		this.location = (TextView) this.findViewById(R.id.place_details_location);
		this.placeId = (TextView) this.findViewById(R.id.place_details_place_id);
		this.cancel = (Button) this.findViewById(R.id.place_details_cancel);
		
		this.cancel.setOnClickListener(this);
		
		Bundle extras = getIntent().getExtras();
		  if (extras != null) {
		   String place= extras.getString(BundleExtraConstant.PLACE_DETAILS.getDesc());
		   if (!StringUtils.isBlank(place)) {
		       this.address.setText(place);
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

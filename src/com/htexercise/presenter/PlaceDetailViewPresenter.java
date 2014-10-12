package com.htexercise.presenter;

import org.apache.commons.lang3.StringUtils;

import android.os.Bundle;

import com.htexercise.model.BundleExtraConstant;
import com.htexercise.view.PlaceDetailViewInterface;

public class PlaceDetailViewPresenter {

	private PlaceDetailViewInterface placeDetailViewInterface;
	private Bundle bundleExtras;

	public PlaceDetailViewPresenter(PlaceDetailViewInterface placeDetailViewInterface) {
		this.placeDetailViewInterface = placeDetailViewInterface;
		this.bundleExtras = this.placeDetailViewInterface.getBundleExtras();
	}

	public void setAddressOnView() {
		String address= this.bundleExtras.getString(BundleExtraConstant.PLACE_DETAILS_FORMATTED_ADDRESS.getDesc());
		if (!StringUtils.isBlank(address)) {
			this.placeDetailViewInterface.setAddress(address);
		}   
	}
	
	public void setLocationOnView() {
		String location= this.bundleExtras.getString(BundleExtraConstant.PLACE_DETAILS_LOCATION.getDesc());
		if (!StringUtils.isBlank(location)) {
			this.placeDetailViewInterface.setLocation(location);
		}   
	}
	
	public void setPlaceIdOnView() {
		String placeId= this.bundleExtras.getString(BundleExtraConstant.PLACE_DETAILS_PLACE_ID.getDesc());
		if (!StringUtils.isBlank(placeId)) {
			this.placeDetailViewInterface.setPlaceId(placeId);
		}   
	}
}

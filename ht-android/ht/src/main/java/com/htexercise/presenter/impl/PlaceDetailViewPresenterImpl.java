package com.htexercise.presenter.impl;

import org.apache.commons.lang3.StringUtils;

import android.os.Bundle;

import com.htexercise.model.BundleExtraConstant;
import com.htexercise.presenter.PlaceDetailViewPresenterInterface;
import com.htexercise.view.PlaceDetailViewInterface;

/**
 * PlaceDetailViewPresenter class
 * 
 * @since 2014-10-11
 * @version 1.0
 * */
public class PlaceDetailViewPresenterImpl implements PlaceDetailViewPresenterInterface {

	private PlaceDetailViewInterface placeDetailViewInterface;
	private Bundle bundleExtras;

	public PlaceDetailViewPresenterImpl(PlaceDetailViewInterface placeDetailViewInterface) {
		this.placeDetailViewInterface = placeDetailViewInterface;
		this.bundleExtras = this.placeDetailViewInterface.getBundleExtras();
	}
	
	@Override
	public void setAddressOnView() {
		String address= this.bundleExtras.getString(BundleExtraConstant.PLACE_DETAILS_FORMATTED_ADDRESS.getDesc());
		if (!StringUtils.isBlank(address)) {
			this.placeDetailViewInterface.setAddress(address);
		}   
	}
	
	@Override
	public void setLocationOnView() {
		String location= this.bundleExtras.getString(BundleExtraConstant.PLACE_DETAILS_LOCATION.getDesc());
		if (!StringUtils.isBlank(location)) {
			this.placeDetailViewInterface.setLocation(location);
		}   
	}
	
	@Override
	public void setPlaceIdOnView() {
		String placeId= this.bundleExtras.getString(BundleExtraConstant.PLACE_DETAILS_PLACE_ID.getDesc());
		if (!StringUtils.isBlank(placeId)) {
			this.placeDetailViewInterface.setPlaceId(placeId);
		}   
	}
}

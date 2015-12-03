package com.ht.view;

import com.ht.presenter.impl.PlaceDetailViewPresenterImpl;
import com.ht.view.impl.PlaceDetailViewImpl;

import android.os.Bundle;

/**
 * Interface for {@link PlaceDetailViewImpl} to interact with 
 * {@link PlaceDetailViewPresenterImpl}
 * 
 * @since 2014-10-11
 * @version 1.0
 * */
public interface PlaceDetailViewInterface {
	
    public Bundle getBundleExtras();
    public void setAddress(String address);
    public void setLocation(String location);
    public void setPlaceId(String placeId);
    
}
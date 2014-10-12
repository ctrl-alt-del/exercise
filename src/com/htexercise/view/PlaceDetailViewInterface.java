package com.htexercise.view;

import com.htexercise.presenter.PlaceDetailViewPresenter;
import com.htexercise.view.impl.PlaceDetailsView;

import android.os.Bundle;

/**
 * Interface for {@link PlaceDetailsView} to interact with 
 * {@link PlaceDetailViewPresenter}
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

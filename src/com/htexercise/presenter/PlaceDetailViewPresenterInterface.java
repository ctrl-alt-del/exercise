package com.htexercise.presenter;

import com.htexercise.presenter.impl.PlaceDetailViewPresenterImpl;
import com.htexercise.view.impl.PlaceDetailViewImpl;

/**
 * Interface for {@link PlaceDetailViewImpl} to interact with 
 * {@link PlaceDetailViewPresenterImpl}
 * 
 * @since 2014-10-11
 * @version 1.0
 * */
public interface PlaceDetailViewPresenterInterface {
	
	public void setAddressOnView();
	public void setLocationOnView();
	public void setPlaceIdOnView();
    
}

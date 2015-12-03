package com.ht.presenter;

import com.ht.presenter.impl.PlaceDetailViewPresenterImpl;
import com.ht.view.impl.PlaceDetailViewImpl;

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

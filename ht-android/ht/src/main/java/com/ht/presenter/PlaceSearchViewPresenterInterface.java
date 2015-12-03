package com.ht.presenter;

import com.ht.presenter.adapter.PlaceSearchAdapter;
import com.ht.presenter.impl.PlaceSearchViewPresenterImpl;
import com.ht.view.impl.PlaceSearchViewImpl;

/**
 * Interface for {@link PlaceSearchViewImpl} to interact with 
 * {@link PlaceSearchViewPresenterImpl}
 * 
 * @since 2014-10-11
 * @version 1.0
 * */
public interface PlaceSearchViewPresenterInterface {
	
	public PlaceSearchAdapter getPlaceSearchAdapter();
    
}

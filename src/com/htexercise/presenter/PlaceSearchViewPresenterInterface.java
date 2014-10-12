package com.htexercise.presenter;

import com.htexercise.presenter.adapter.PlaceSearchAdapter;
import com.htexercise.presenter.impl.PlaceSearchViewPresenterImpl;
import com.htexercise.view.impl.PlaceSearchViewImpl;

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

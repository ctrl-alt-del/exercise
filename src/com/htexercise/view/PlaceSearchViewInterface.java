package com.htexercise.view;

import com.htexercise.presenter.impl.PlaceSearchViewPresenterImpl;
import com.htexercise.view.impl.PlaceSearchViewImpl;

import android.app.Activity;
import android.widget.ListView;
import android.widget.SearchView;

/**
 * Interface for {@link PlaceSearchViewImpl} to interact with 
 * {@link PlaceSearchViewPresenterImpl}
 * 
 * @since 2014-10-11
 * @version 1.0
 * */
public interface PlaceSearchViewInterface {
	
	public ListView getListView();
    public Activity getActivity();
    public SearchView getSearchView();
    
}

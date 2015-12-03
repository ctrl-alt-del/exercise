package com.ht.view;

import android.app.Activity;
import android.widget.ListView;
import android.widget.SearchView;

import com.ht.presenter.impl.PlaceSearchViewPresenterImpl;
import com.ht.view.impl.PlaceSearchViewImpl;

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

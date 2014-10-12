package com.htexercise.view;

import java.util.List;

import com.htexercise.PlaceSearchView;
import com.htexercise.model.Prediction;
import com.htexercise.presenter.PlaceSearchViewPresenter;

import android.app.Activity;
import android.widget.ListView;
import android.widget.SearchView;

/**
 * Interface for {@link PlaceSearchView} to interact with 
 * {@link PlaceSearchViewPresenter}
 * 
 * @since 2014-10-11
 * @version 1.0
 * */
public interface PlaceSearchViewInterface {
	
	public ListView getListView();
	public void updateListView();
    public Activity getActivity();
    public SearchView getSearchView();
    public List<Prediction> getPlaceAutocompletes();
    
}

package com.htexercise.view;

import java.util.List;

import com.htexercise.model.Prediction;

import android.app.Activity;
import android.widget.ListView;
import android.widget.SearchView;

public interface PlaceSearchViewInterface {
	
	public ListView getListView();
	public void updateListView();
	
    public Activity getActivity();
    
    public SearchView getSearchView();
    
    public List<Prediction> getPlaceAutocompletes();
    
    
    
}

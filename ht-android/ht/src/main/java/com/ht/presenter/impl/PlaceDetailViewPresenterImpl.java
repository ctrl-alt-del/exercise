package com.ht.presenter.impl;

import android.os.Bundle;
import android.text.TextUtils;

import com.ht.model.BundleExtraConstant;
import com.ht.presenter.PlaceDetailViewPresenterInterface;
import com.ht.view.PlaceDetailViewInterface;

/**
 * PlaceDetailViewPresenter class
 *
 * @version 1.0
 * @since 2014-10-11
 */
public class PlaceDetailViewPresenterImpl implements PlaceDetailViewPresenterInterface {

    private PlaceDetailViewInterface placeDetailViewInterface;
    private Bundle bundleExtras;

    public PlaceDetailViewPresenterImpl(PlaceDetailViewInterface placeDetailViewInterface) {
        this.placeDetailViewInterface = placeDetailViewInterface;
        this.bundleExtras = this.placeDetailViewInterface.getBundleExtras();
    }

    @Override
    public void setAddressOnView() {
        String address = this.bundleExtras.getString(BundleExtraConstant.PLACE_DETAILS_FORMATTED_ADDRESS.getDesc());
        if (!TextUtils.isEmpty(address)) {
            this.placeDetailViewInterface.setAddress(address);
        }
    }

    @Override
    public void setLocationOnView() {
        String location = this.bundleExtras.getString(BundleExtraConstant.PLACE_DETAILS_LOCATION.getDesc());
        if (!TextUtils.isEmpty(location)) {
            this.placeDetailViewInterface.setLocation(location);
        }
    }

    @Override
    public void setPlaceIdOnView() {
        String placeId = this.bundleExtras.getString(BundleExtraConstant.PLACE_DETAILS_PLACE_ID.getDesc());
        if (!TextUtils.isEmpty(placeId)) {
            this.placeDetailViewInterface.setPlaceId(placeId);
        }
    }
}

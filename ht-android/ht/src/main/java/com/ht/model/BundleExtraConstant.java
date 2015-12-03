package com.ht.model;

/**
 * Enum class to keep constants used on passing bundle extra.
 * <br>
 * This class can return either the enum object of its string representation
 * 
 * @since 2014-10-11
 * @version 1.0
 * */
public enum BundleExtraConstant {
	
	PLACE_DETAILS_FORMATTED_ADDRESS("PLACE_DETAILS_FORMATTED_ADDRESS"),
	PLACE_DETAILS_PLACE_ID("PLACE_DETAILS_PLACE_ID"),
	PLACE_DETAILS_LOCATION("PLACE_DETAILS_LOCATION");
	
	private String desc;
	
	BundleExtraConstant(String desc) {
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }
}

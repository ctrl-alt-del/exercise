package com.htexercise.model;

/**
 * Enum class to keep constants used on passing bundle extra.
 * <br>
 * This class can return either the enum object of its string representation
 * 
 * @since 2014-10-11
 * @version 1.0
 * */
public enum BundleExtraConstant {
	
	PLACE_DETAILS("PLACE_DETAILS");
	
	private String desc;
	
	BundleExtraConstant(String desc) {
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }
}

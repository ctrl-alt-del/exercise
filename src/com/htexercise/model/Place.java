package com.htexercise.model;

import java.util.ArrayList;
import java.util.List;

public class Place {

    private List<Prediction> predictions = new ArrayList<Prediction>();
    private String status;

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

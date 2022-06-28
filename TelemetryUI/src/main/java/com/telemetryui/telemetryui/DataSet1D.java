package com.telemetryui.telemetryui;

/**
 * The DataSet1D class stores a Dataset of 1D or 1 Double Values for a specific Time and will later get Mathods for typical 1D operations.
 */
public class DataSet1D extends DataSet{

    final Double x;
    final Long time;
    /**
     * This Initializes a 3D Dataset.
     * @param x A Double value.
     */
    DataSet1D(Long time, Double x){
        this.time = time;
        this.x = x;
    }
}

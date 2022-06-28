package com.telemetryui.telemetryui;

/**
 * The DataSet2D class stores a Dataset of 2D or 2 Double Values for a specific Time and will later get Mathods for typical 2D operations.
 */
public class DataSet2D extends DataSet{

    final Double x,y;
    final Long time;
    /**
     * This Initializes a 2D Dataset.
     * @param x An Double value.
     * @param y An Double value.
     */
    DataSet2D(Long time, Double x, Double y){
        this.time = time;
        this.x = x;
        this.y = y;
    }
}

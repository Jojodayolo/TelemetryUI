package com.telemetryui.telemetryui;

/**
 * The DataSet3D class stores a Dataset of 3D or 3 Double Values for a specific Time and will later get Mathods for typical 3D operations.
 */
public class DataSet3D extends DataSet{

    final Double x,y,z;
    final Long time;
    /**
     * This Initializes a 3D Dataset.
     * @param x An Double value.
     * @param y An Double value.
     * @param z An Double value.
     */
    DataSet3D(Long time, Double x, Double y, Double z){
        this.time = time;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

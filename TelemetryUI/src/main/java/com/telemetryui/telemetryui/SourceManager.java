package com.telemetryui.telemetryui;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class manages different Source objects so Sinks can access them.
 */
public class SourceManager {

    LinkedList<Source> sourceList;

    public SourceManager() {
        this.sourceList = new LinkedList<>();
    }

    /**
     * Creates a 1D Dataset to transfer data.
     * @param name Name of the Source.
     * @return returns the Source that got created.
     */
    public Source addSingleValueSource(String name){
        Source tmpSrc = new Source(name,new ConcurrentLinkedQueue<DataSet1D>());
        sourceList.add(tmpSrc);
        return tmpSrc;
    }

    /**
     * Creates a 2D Dataset to transfer data.
     * @param name Name of the Source.
     * @return returns the Source that got created.
     */
    public Source add2DValueSource(String name){
        Source tmpSrc = new Source(name,new ConcurrentLinkedQueue<DataSet2D>());
        sourceList.add(tmpSrc);
        return tmpSrc;
    }

    /**
     * Creates a 3D Dataset to transfer data.
     * @param name Name of the Source.
     * @return returns the Source that got created.
     */
    public Source add3DValueSource(String name){
        Source tmpSrc = new Source(name,new ConcurrentLinkedQueue<DataSet3D>());
        sourceList.add(tmpSrc);
        return tmpSrc;
    }

    /**
     * @return returns a LinkedList with all Source Objects.
     */
    public LinkedList<Source> getSourceList(){
        return sourceList;
    }

    /**
     * @return returns a String Array with the names of all sources.
     */
    public String[] getStringArr(){
        String[] strArr = new String[sourceList.size()];

        for (int i = 0; i < sourceList.size(); i++) {
            strArr[i] = sourceList.get(i).name;
        }
        return strArr;
    }


}

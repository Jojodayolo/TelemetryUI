package com.telemetryui.telemetryui;

import javafx.scene.Node;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class creates test data for a DataSet3D and writes them into an source object which ist created by the SourceManager.
 */
public class DataGenerator extends Node implements Runnable{

    ConcurrentLinkedQueue<DataSet3D> outQueue;
    public boolean running = true;

    /**
     * @param srcManager This takes a reference to the SourceManager so a Source object can be added for data transmission.
     */
    DataGenerator(SourceManager srcManager){
        this.outQueue = (ConcurrentLinkedQueue<DataSet3D>) srcManager.add3DValueSource("TestDataGen").queue;
    }

    /**
     *  This Method runs until the <code>running</code> variable gets set to false. It generates a DataSet3D with some Test data to display.
     */
    @Override
    public void run() {
        while(running){
            if(outQueue.size() < 1000) {
                Long ct = System.currentTimeMillis();
                Double number = (ct % (Math.PI * 2)) *0.1d;
                outQueue.add(new DataSet3D(ct,
                        Math.sin(number),
                        Math.cos(number),
                        1 + Math.sin(number)
                ));
            }
        }
    }
}

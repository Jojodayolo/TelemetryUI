package com.telemetryui.telemetryui;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class holds a LinkedQueue of Datasets and gets used to transfere DataSets between sources(@see SerialReader or @see DataGenerator) and Sinks(@see DataChannel).
 */
public class Source {
    ConcurrentLinkedQueue<? extends DataSet> queue;
    String name;
    Source(String name, ConcurrentLinkedQueue<? extends DataSet> queue) {
        this.name = name;
        this.queue = queue;
    }
}

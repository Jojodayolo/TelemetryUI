package com.telemetryui.telemetryui;

import com.fazecast.jSerialComm.*;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * This class is responsible to read specific formated data from the Serial bus and write them as DataSet3D's to Source Objects for further Data transfer.
 */
public class SerialReader implements Runnable{
    SerialPort port = null;
    boolean running = true;
    SourceManager manager;
    ConcurrentLinkedQueue<DataSet3D> accQueue, magQueue, gyrQueue;

    /**
     * @param scrManager the manager that gets used to route Data from the @see SerialReader or @see DataGenerator.
     */
    SerialReader(SourceManager scrManager){
        this.manager = scrManager;
        accQueue = (ConcurrentLinkedQueue<DataSet3D>) manager.add3DValueSource("accQueue").queue;
        magQueue = (ConcurrentLinkedQueue<DataSet3D>) manager.add3DValueSource("magQueue").queue;
        gyrQueue = (ConcurrentLinkedQueue<DataSet3D>) manager.add3DValueSource("gyrQueue").queue;

        if(SerialPort.getCommPorts().length > 2){
            port = SerialPort.getCommPorts()[2];
            port.setBaudRate(115200);
            port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
            port.openPort();
            System.out.println(port.getDescriptivePortName());
        }
    }

    /**
     * This method runs on a separate thread and reads a Serial port to receive data in a specific format from a microcontroller.
     */
    @Override
    public void run() {
        while(running){
            //read Serial Port until firs new line occurs to get a synced starting point
            boolean check = true;
            assert port != null;
            InputStream in = port.getInputStream();
            BufferedReader buffStream = new BufferedReader(new InputStreamReader(in));
            while(check){
                try {
                    buffStream.readLine();
                    check = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (port != null){
                String s = null;
                try {
                    s = buffStream.readLine();
                    String[] dataArr = s.split("\\t");
                    putData(dataArr);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param arr an Array of number as String objects that get converted to a Number type and written to a Source object for Data transfer.
     */
    private void putData(String[] arr){
        try {
            if (accQueue.size() < 1000) {
                accQueue.add(new DataSet3D(Long.valueOf(arr[0]),
                        Double.valueOf(arr[1]),
                        Double.valueOf(arr[2]),
                        Double.valueOf(arr[3]))
                );
                gyrQueue.add(new DataSet3D(Long.valueOf(arr[0]),
                        Double.valueOf(arr[4]),
                        Double.valueOf(arr[5]),
                        Double.valueOf(arr[6]))
                );
                magQueue.add(new DataSet3D(Long.valueOf(arr[0]),
                        Double.valueOf(arr[7]),
                        Double.valueOf(arr[8]),
                        Double.valueOf(arr[9]))
                );
            } else {
                accQueue.poll();
                gyrQueue.poll();
                magQueue.poll();

                accQueue.add(new DataSet3D(Long.valueOf(arr[0]),
                        Double.valueOf(arr[1]),
                        Double.valueOf(arr[2]),
                        Double.valueOf(arr[3]))
                );
                gyrQueue.add(new DataSet3D(Long.valueOf(arr[0]),
                        Double.valueOf(arr[4]),
                        Double.valueOf(arr[5]),
                        Double.valueOf(arr[6]))
                );
                magQueue.add(new DataSet3D(Long.valueOf(arr[0]),
                        Double.valueOf(arr[7]),
                        Double.valueOf(arr[8]),
                        Double.valueOf(arr[9]))
                );
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}


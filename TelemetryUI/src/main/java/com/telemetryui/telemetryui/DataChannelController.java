package com.telemetryui.telemetryui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import java.util.Timer;
import java.util.TimerTask;


/**
 * This is the Controller class for the different DataChannels. At the moment the Data Channels only take 3D Datasets and Displays them.
 */
public class DataChannelController {

    @FXML
    private LineChart graph;
    @FXML
    private ComboBox sourceSelect;


    private Integer currenSourceIndex;
    private Timer clockTimer = new Timer();
    private SourceManager manager;
    private Long startTime;
    private XYChart.Series<Long,Long> xSeries;
    private XYChart.Series<Long,Long> ySeries;
    private XYChart.Series<Long,Long> zSeries;


    /**
     * This method gets called by the application when the Channel gets created. It prepares things to be displayed in the Graph.
     */
    @FXML
    private void initialize(){
        System.out.println("Added");
        xSeries = new XYChart.Series<>();
        xSeries.setName("X");
        ySeries = new XYChart.Series<>();
        ySeries.setName("Y");
        zSeries = new XYChart.Series<>();
        zSeries.setName("Z");
        graph.getData().addAll(xSeries,ySeries,zSeries);
        startTime = 0L;
        currenSourceIndex = 0;

    }

    /**
     * This method gets called when the Source Select ComboBox changes.
     */
    @FXML
    private final EventHandler<ActionEvent> onSourceChanged(){

        xSeries = new XYChart.Series<>();
        xSeries.setName("X");
        ySeries = new XYChart.Series<>();
        ySeries.setName("Y");
        zSeries = new XYChart.Series<>();
        zSeries.setName("Z");
        graph.getData().clear();
        graph.getData().addAll(xSeries,ySeries,zSeries);

        for (String s: manager.getStringArr()) {
            sourceSelect.getItems().add(s);
            for (Source src:manager.getSourceList()) {
                if(src.name == sourceSelect.getValue()){
                    currenSourceIndex = manager.getSourceList().indexOf(src);
                }
            }
        }
        return event -> System.out.println(sourceSelect.getValue());
    }

    /**
     * This method is required to call, so that the instance gets a reference of the @see SourceManager
     * @param manager the manager that gets used to route Data from the @see SerialReader or @see DataGenerator.
     */
    public void setSourceManager(SourceManager manager){
        this.manager = manager;

        for (Source src: manager.getSourceList()) {
            if(!sourceSelect.getItems().contains(src.name)) {
                sourceSelect.getItems().add(src.name);
            }
        }
        sourceSelect.setValue(manager.getSourceList().get(currenSourceIndex).name);


        clockTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if( manager != null && manager.getSourceList().getFirst().queue.peek() != null){
                        try {
                            DataSet3D data = (DataSet3D) manager.getSourceList().get(currenSourceIndex).queue.poll();

                            if (data != null) {

                                xSeries.getData().add(new XYChart.Data(data.time.toString(), data.x));
                                ySeries.getData().add(new XYChart.Data(data.time.toString(), data.y));
                                zSeries.getData().add(new XYChart.Data(data.time.toString(), data.z));
                                if (xSeries.getData().size() > 500) {
                                    xSeries.getData().remove(0);
                                    ySeries.getData().remove(0);
                                    zSeries.getData().remove(0);
                                }
                            }
                        }catch (NullPointerException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        },0, 10);
    }


}

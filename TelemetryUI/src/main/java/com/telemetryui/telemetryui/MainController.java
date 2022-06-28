package com.telemetryui.telemetryui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;

import java.io.IOException;


/**
 * This is the Controller Class for the Main contents of the Window.
 */
public class MainController {

    SourceManager manager;

    @FXML
    private VBox channelBox;
    @FXML
    private MenuItem addChannel;

    /**
     * This method gets Called when the menu "add Channel" gets Clicked and Creates a new <code>DataChannel</code> to display Data.
     */
    @FXML
    protected void onAddChannel() throws IOException {
        System.out.println("adding Channel");
        FXMLLoader fxmlLoader = new FXMLLoader();
        SplitPane pane = fxmlLoader.load(getClass().getResource("DataChannel.fxml").openStream());
        channelBox.getChildren().add(pane);
        DataChannelController ctr = (DataChannelController) fxmlLoader.getController();

        if(this.manager != null){
            ctr.setSourceManager(manager);

        }
    }

    /**
     * This method needs to be called to give this Object a reference to the <code>SourceManager</code> so the DataChannel can access the Sources and corresponding data.
     * @param manager reference to a <code>SourceManager</code> Object.
     */
    public void setSourceManager(SourceManager manager){
        this.manager = manager;
    }
}
package com.telemetryui.telemetryui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the Main file from which the Application gets started and the <code>DataGenerator</code> and <code>SerialReadr</code> Threads get started.
 */
public class MainApplication extends Application {

    private SerialReader reader;
    private DataGenerator gen;
    private Thread genThread;
    private Thread readerThread;
    private SourceManager scrManager;


    /**
     * @param stage The <code>Stage</code> given from the Application. In most usacses this is the Window.
     */
    @Override
    public void start(Stage stage) throws IOException {

        scrManager = new SourceManager();

        if (true) {
            reader = new SerialReader(scrManager);
            readerThread = new Thread(reader, "SerialReader");
            readerThread.start();
        }

        if (false) {
            gen = new DataGenerator(scrManager);
            genThread = new Thread(gen, "DataGen");
            genThread.start();
        }


        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1500);
        MainController ctr =  fxmlLoader.getController();
        ctr.setSourceManager(scrManager);
        stage.setTitle("TelemetryUI");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Mainly used for stopping running Threads.
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        reader.running = false;
        //gen.running = false;
        super.stop();
    }

    /**
     * Starts the Application.
     * @param args commandline Arguments. They have no effect.
     */
    public static void main(String[] args) {
        launch();
    }
}
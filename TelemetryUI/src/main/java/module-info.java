module com.telemetryui.telemetryui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fazecast.jSerialComm;

    opens com.telemetryui.telemetryui to javafx.fxml;
    exports com.telemetryui.telemetryui;

}
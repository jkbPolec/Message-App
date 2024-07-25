module com.kgt.messageapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires jdk.compiler;

    opens com.kgt.messageapp to javafx.fxml;
    exports com.kgt.messageapp;
    exports com.kgt.messageapp.client;
    opens com.kgt.messageapp.client to javafx.fxml;
}
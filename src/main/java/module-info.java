module com.kgt.messageapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.kgt.messageapp to javafx.fxml;
    exports com.kgt.messageapp;
}
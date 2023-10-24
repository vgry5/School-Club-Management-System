module com.example.sms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.sms to javafx.fxml;
    exports com.example.sms;
}
module com.example.prodigy_sd_04 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.testng;
    requires org.junit.jupiter.api;


    opens com.example.prodigy_sd_04 to javafx.fxml;
    exports com.example.prodigy_sd_04;
}
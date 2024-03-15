module com.example.task_manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.task_manager to javafx.fxml;
    exports com.example.task_manager;
}
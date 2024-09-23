module me.leon.theater {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens me.leon.theater to javafx.fxml;
    exports me.leon.theater;
    opens me.leon.theater.controllers to javafx.fxml;
    exports me.leon.theater.controllers;
    exports me.leon.theater.models;
    opens me.leon.theater.models to javafx.fxml;
    exports me.leon.theater.data;
    opens me.leon.theater.data to javafx.fxml;
}
module me.leon.theater {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens me.leon.theater to javafx.fxml;
    exports me.leon.theater;
}
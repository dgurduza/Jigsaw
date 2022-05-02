module ru.hse.jigsaw {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens ru.hse.jigsaw to javafx.fxml;
    exports ru.hse.jigsaw;
    exports ru.hse.jigsaw.models;
    opens ru.hse.jigsaw.models to javafx.fxml;
}
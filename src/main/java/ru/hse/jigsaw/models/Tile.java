package ru.hse.jigsaw.models;


import javafx.geometry.Pos;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Класс для создания игровой клетки.
 */
public class Tile extends GridPane {
    Rectangle tile;

    public Tile() {
        tile = new Rectangle(50, 50);
        tile.setFill(null);
        tile.setStroke(Color.GREY);
        setAlignment(Pos.CENTER);
        getChildren().add(tile);
        setOnDragOver(dragEvent -> {
            Dragboard board = dragEvent.getDragboard();
            if (this.getId() != null && board.hasString()) {
                dragEvent.acceptTransferModes(TransferMode.ANY);
            }
        });
    }

    public void setFill(Color color) {
        tile.setFill(color);
    }

    public Paint getFill() {
        return tile.getFill();
    }
}

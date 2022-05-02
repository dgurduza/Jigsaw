package ru.hse.jigsaw;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ru.hse.jigsaw.models.Figures;
import ru.hse.jigsaw.models.Tile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Jigsaw extends Application {
    private static AnchorPane root;
    private static final int SIZE = 50;
    private static final int TILES_COUNT = 9;
    private static final int MAX_SIZE = SIZE * TILES_COUNT;
    private List<List<Integer>> gridIndexes = new ArrayList<>();
    protected static Label movesLabel;
    private static Figures figures;
    private static GridPane figureViewer;
    private int counter;
    protected static int moves = 0;

    /**
     * Создания рабочего пространства.
     *
     * @return рабочее пространство.
     */
    private Parent createRoot() {
        getGridByIndexes();
        counter = 0;
        root = new AnchorPane();
        figures = new Figures();
        root.setPrefSize(MAX_SIZE + 200, MAX_SIZE);
        for (int i = 0; i < TILES_COUNT; ++i) {
            for (int j = 0; j < TILES_COUNT; ++j) {
                Tile tile = new Tile();
                tile.setTranslateX(i * SIZE);
                tile.setTranslateY(j * SIZE);
                tile.setId(Integer.toString(counter));
                ++counter;
                tile.setOnDragDropped(dragEvent -> {
                    Dragboard board = dragEvent.getDragboard();
                    if (tile.getFill() == null && board.hasString()) {
                        paintFigure(tile);
                    }
                });
                root.getChildren().add(tile);
            }
        }
        root.getChildren().addAll(createInformationPanel(), createViewer());
        return root;
    }

    /**
     * Отрисовка фигуры
     *
     * @param tile секция поля относительно, которой будет отрисовка.
     */
    private void paintFigure(Tile tile) {
        List<Integer> indexes = getIndexes(Integer.parseInt(tile.getId()),
                figures.getFigure());
        boolean statusOfDrop = false;
        for (int index = 0; index < indexes.size(); ++index) {
            Tile workingTile = (Tile) root.getChildren().get(indexes.get(index));
            if (workingTile.getFill() == null) {
                statusOfDrop = true;
                workingTile.setFill(figures.getFigureColor());
            } else {
                statusOfDrop = false;
                clearGridByIndexes(indexes.subList(0, index));
                break;
            }
        }
        setMovesLabelText(statusOfDrop);
    }

    /**
     * Добавление новой фигуры в рабочее пространство.
     */
    protected static void getNewFigureToPane() {
        Tile[][] newFigure = figures.getNewFigure();
        clearViewer();
        for (int i = 0; i < newFigure.length; ++i) {
            for (int j = 0; j < newFigure[0].length; ++j) {
                if (newFigure[i][j] != null) {
                    figureViewer.add(newFigure[i][j], i, j);
                } else {
                    figureViewer.add(new Tile(), i, j);
                }
            }
        }
    }

    /**
     * Изменение счетчика шагов.
     *
     * @param status статус того, был ли изменено поле.
     */
    private void setMovesLabelText(boolean status) {
        if (status) {
            getNewFigureToPane();
            ++moves;
            movesLabel.setText(Integer.toString(moves));
        }
    }

    /**
     * Очистка поля для отображения новых фигур.
     */
    protected static void clearViewer() {
        for (int i = 0; i < figureViewer.getChildren().size(); ++i) {
            Tile workingTile = (Tile) figureViewer.getChildren().get(i);
            workingTile.setFill(null);
        }
    }

    /**
     * Получения индексов панели относительно индексов в родителе.
     * В Parent(GridPane) хоть и можно было расположить фигуру по индексам, но
     * в самом GridPane они хранились подряд. Метод требуется для получения сетки
     * индексов относительно хранения их в GridPane.
     */
    private void getGridByIndexes() {
        counter = 0;
        for (int i = 0; i < TILES_COUNT; ++i) {
            gridIndexes.add(new ArrayList<>());
            for (int j = 0; j < TILES_COUNT; ++j) {
                gridIndexes.get(i).add(counter);
                ++counter;
            }
        }
    }

    /**
     * Получение расположения фигуры на поле.
     *
     * @param indexOfDrop Индекс выкладки фигуры.
     * @param tiles       фигура
     * @return список индексов
     */
    private List<Integer> getIndexes(int indexOfDrop, Tile[][] tiles) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < tiles.length; ++i) {
            for (int j = 0; j < tiles[0].length; ++j) {
                if (tiles[i][j] != null) {
                    indexes.add(gridIndexes.get(i).get(j) + indexOfDrop);
                }
            }
        }
        if (tiles[0][0] == null) {
            for (int i = 0; i < indexes.size(); ++i) {
                indexes.set(i, indexes.get(i) - 1);
            }
        }
        if (checkIndexes(indexes)) {
            return new ArrayList<>();
        }
        return indexes;
    }

    /**
     * Проверка индексов на соответствие условию отрисовки.
     *
     * @param indexes индексы расположения фигуры на панели.
     * @return Вердикт.
     */
    private boolean checkIndexes(List<Integer> indexes) {
        for (int i = 0; i < indexes.size() - 1; ++i) {
            if ((indexes.get(i) + 1) % 9 == 0 && indexes.get(i + 1) % 9 == 0) {
                return true;
            }
        }
        for (int i = 0; i < indexes.size(); ++i) {
            if (indexes.get(i) > 80) {
                return true;
            }
        }
        return false;
    }

    /**
     * Очистка игровой панели, при неудачном расположении фигуры.
     *
     * @param indexes массив индексов.
     */
    private void clearGridByIndexes(List<Integer> indexes) {
        for (int k = 0; k < indexes.size(); ++k) {
            Tile workingTile = (Tile) root.getChildren().get(indexes.get(k));
            if (workingTile.getFill() != null) {
                workingTile.setFill(null);
            }
        }
    }

    /**
     * Очистка игровой панели.
     */
    protected static void clearAllGrid() {
        for (int k = 0; k < (TILES_COUNT * TILES_COUNT); ++k) {
            Tile workingTile = (Tile) root.getChildren().get(k);
            if (workingTile.getFill() != null) {
                workingTile.setFill(null);
            }
        }
    }

    private List<Label> getLabelsToPanels() {
        List<Label> list = new ArrayList<>();
        list.add(new Label("Timer: "));
        list.add(new Label("0:00:00"));
        list.add(new Label("Moves: "));
        list.add(new Label("0"));
        for (int i = 0; i < list.size(); ++i) {
            list.get(i).setFont(new Font("Times New Roman", 16));
        }
        return list;
    }

    /**
     * Метод создания области в которой отображаются фигуры.
     *
     * @return Панель.
     */
    private GridPane createViewer() {
        figureViewer = new GridPane();
        figureViewer.setId("FiguresPanel");
        figureViewer.setOnDragDetected(dragEvent -> {
            Dragboard board = figureViewer.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(figureViewer.getId());
            board.setContent(content);
        });
        AnchorPane.setTopAnchor(figureViewer, 200.0);
        AnchorPane.setBottomAnchor(figureViewer, 75.0);
        AnchorPane.setRightAnchor(figureViewer, 25.0);
        return figureViewer;
    }

    /**
     * Метод создания и оформления информационной панели.
     *
     * @return панель.
     */
    private VBox createInformationPanel() {
        VBox informationPanel = new VBox();
        HBox timerPanel = new HBox();
        HBox movesPanel = new HBox();
        HBox buttonsPanel = new HBox();
        List<Label> labelList = getLabelsToPanels();
        Button startButton = new Button("Start game");
        Button stopGameButton = new Button("Stop game");
        Stopwatch stopwatch = new Stopwatch(labelList.get(1), startButton, stopGameButton);
        buttonsPanel.setSpacing(20);
        informationPanel.setSpacing(25);
        informationPanel.setPrefSize(200, MAX_SIZE);
        informationPanel.setTranslateX(MAX_SIZE);
        informationPanel.setTranslateY(25);
        timerPanel.setAlignment(Pos.CENTER);
        movesPanel.setAlignment(Pos.CENTER);
        buttonsPanel.setAlignment(Pos.CENTER);
        movesLabel = labelList.get(3);
        timerPanel.getChildren().addAll(labelList.get(0), labelList.get(1));
        movesPanel.getChildren().addAll(labelList.get(2), labelList.get(3));
        buttonsPanel.getChildren().addAll(startButton, stopGameButton);
        informationPanel.getChildren().addAll(timerPanel, movesPanel, buttonsPanel);
        return informationPanel;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createRoot());
        stage.setTitle("Jigsaw");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
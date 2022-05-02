package ru.hse.jigsaw.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Класс для обобщения фигур.
 */
public class Figures extends Rectangle {
    private static final Random RANDOM = new Random();
    private Tile[][] figure;
    private Color figureColor = Color.ORANGE;

    public Figures() {

    }

    public Tile[][] getFigure() {
        return figure;
    }

    public Color getFigureColor() {
        return figureColor;
    }

    /**
     * Метод случайного создания фигур.
     *
     * @return Фигуру.
     */
    public Tile[][] getNewFigure() {
        figure = new Tile[3][3];
        int groupNumber = RANDOM.nextInt(4) + 1;
        switch (groupNumber) {
            case 1 -> {
                createFigureWith3Tiles();
                return figure;
            }
            case 2 -> {
                createFigureWith4Tiles();
                return figure;
            }
            case 3 -> {
                createFigureWith5Tiles();
                return figure;
            }
            case 4 -> {
                figure[0][0] = new Tile();
                getRandomColor();
                return figure;
            }
        }
        createFigureWith3Tiles();
        return figure;
    }

    /**
     * Метод, инициализирующий случайно выбранную фигуру на 3 клетки.
     */
    private void createFigureWith3Tiles() {
        int numberOfCase = RANDOM.nextInt(6) + 1;
        switch (numberOfCase) {
            case 1 -> {
                figure[0][0] = new Tile();
                figure[1][0] = new Tile();
                figure[2][0] = new Tile();
            }
            case 2 -> {
                figure[0][0] = new Tile();
                figure[0][1] = new Tile();
                figure[0][2] = new Tile();
            }
            case 3 -> {
                figure[0][0] = new Tile();
                figure[1][0] = new Tile();
                figure[0][1] = new Tile();
            }
            case 4 -> {
                figure[0][0] = new Tile();
                figure[0][1] = new Tile();
                figure[1][1] = new Tile();
            }
            case 5 -> {
                figure[0][0] = new Tile();
                figure[1][0] = new Tile();
                figure[1][1] = new Tile();
            }
            case 6 -> {
                figure[0][1] = new Tile();
                figure[1][0] = new Tile();
                figure[1][1] = new Tile();
            }
        }
        getRandomColor();
    }

    /**
     * Метод, инициализирующий случайно выбранную фигуру на 5 клеток.
     */
    private void createFigureWith5Tiles() {
        int numberOfCase = RANDOM.nextInt(8) + 1;
        switch (numberOfCase) {
            case 1 -> {
                figure[0][0] = new Tile();
                figure[0][1] = new Tile();
                figure[0][2] = new Tile();
                figure[1][0] = new Tile();
                figure[2][0] = new Tile();
            }
            case 2 -> {
                figure[0][0] = new Tile();
                figure[0][1] = new Tile();
                figure[0][2] = new Tile();
                figure[1][1] = new Tile();
                figure[2][1] = new Tile();
            }
            case 3 -> {
                figure[0][0] = new Tile();
                figure[0][1] = new Tile();
                figure[0][2] = new Tile();
                figure[1][2] = new Tile();
                figure[2][2] = new Tile();
            }
            case 4 -> {
                figure[0][0] = new Tile();
                figure[1][1] = new Tile();
                figure[1][2] = new Tile();
                figure[1][0] = new Tile();
                figure[2][0] = new Tile();
            }
            case 5 -> {
                figure[0][0] = new Tile();
                figure[2][1] = new Tile();
                figure[2][2] = new Tile();
                figure[2][0] = new Tile();
                figure[1][0] = new Tile();
            }
            case 6 -> {
                figure[0][1] = new Tile();
                figure[1][1] = new Tile();
                figure[2][0] = new Tile();
                figure[2][2] = new Tile();
                figure[2][1] = new Tile();
            }
            case 7 -> {
                figure[0][2] = new Tile();
                figure[1][2] = new Tile();
                figure[2][0] = new Tile();
                figure[2][2] = new Tile();
                figure[2][1] = new Tile();
            }
            case 8 -> {
                figure[0][2] = new Tile();
                figure[1][1] = new Tile();
                figure[1][0] = new Tile();
                figure[2][2] = new Tile();
                figure[1][2] = new Tile();
            }
        }
        getRandomColor();
    }

    /**
     * Метод, инициализирующий случайно выбранную фигуру на 4 клетки.
     */
    private void createFigureWith4Tiles() {
        int numberOfCase = RANDOM.nextInt(16) + 1;
        switch (numberOfCase) {
            case 1 -> {
                figure[0][1] = new Tile();
                figure[0][0] = new Tile();
                figure[1][0] = new Tile();
                figure[2][0] = new Tile();
            }
            case 2 -> {
                figure[1][1] = new Tile();
                figure[0][0] = new Tile();
                figure[1][0] = new Tile();
                figure[2][0] = new Tile();
            }
            case 3 -> {
                figure[0][0] = new Tile();
                figure[1][0] = new Tile();
                figure[2][0] = new Tile();
                figure[2][1] = new Tile();
            }
            case 4 -> {
                figure[0][1] = new Tile();
                figure[1][1] = new Tile();
                figure[2][1] = new Tile();
                figure[2][0] = new Tile();
            }
            case 5 -> {
                figure[0][1] = new Tile();
                figure[1][1] = new Tile();
                figure[2][1] = new Tile();
                figure[1][0] = new Tile();
            }
            case 6 -> {
                figure[0][1] = new Tile();
                figure[1][1] = new Tile();
                figure[2][1] = new Tile();
                figure[0][0] = new Tile();
            }
            case 7 -> {
                figure[0][1] = new Tile();
                figure[0][2] = new Tile();
                figure[0][0] = new Tile();
                figure[1][0] = new Tile();
            }
            case 8 -> {
                figure[0][1] = new Tile();
                figure[0][2] = new Tile();
                figure[0][0] = new Tile();
                figure[1][1] = new Tile();
            }
            case 9 -> {
                figure[0][1] = new Tile();
                figure[0][2] = new Tile();
                figure[0][0] = new Tile();
                figure[1][2] = new Tile();
            }
            case 10 -> {
                figure[1][1] = new Tile();
                figure[1][2] = new Tile();
                figure[1][0] = new Tile();
                figure[0][0] = new Tile();
            }
            case 11 -> {
                figure[1][1] = new Tile();
                figure[1][2] = new Tile();
                figure[1][0] = new Tile();
                figure[0][1] = new Tile();
            }
            case 12 -> {
                figure[1][1] = new Tile();
                figure[1][2] = new Tile();
                figure[1][0] = new Tile();
                figure[0][2] = new Tile();
            }
            case 13 -> {
                figure[0][0] = new Tile();
                figure[1][0] = new Tile();
                figure[1][1] = new Tile();
                figure[2][1] = new Tile();
            }
            case 14 -> {
                figure[0][1] = new Tile();
                figure[1][0] = new Tile();
                figure[1][1] = new Tile();
                figure[2][0] = new Tile();
            }
            case 15 -> {
                figure[0][0] = new Tile();
                figure[0][1] = new Tile();
                figure[1][1] = new Tile();
                figure[1][2] = new Tile();
            }
            case 16 -> {
                figure[0][1] = new Tile();
                figure[0][2] = new Tile();
                figure[1][0] = new Tile();
                figure[1][1] = new Tile();
            }
        }
        getRandomColor();
    }

    /**
     * Метод для получения случайного цвета фигуры.
     */
    private void getRandomColor() {
        int randomNumber = RANDOM.nextInt(5) + 1;
        switch (randomNumber) {
            case 1 -> figureColor = Color.GREEN;
            case 2 -> figureColor = Color.RED;
            case 3 -> figureColor = Color.BLUE;
            case 4 -> figureColor = Color.YELLOW;
            case 5 -> figureColor = Color.DARKBLUE;
        }
        for (int i = 0; i < figure.length; ++i) {
            for (int j = 0; j < figure[0].length; ++j) {
                if (figure[i][j] != null) {
                    figure[i][j].setFill(figureColor);
                }
            }
        }
    }
}


package ru.hse.jigsaw;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Класс, создающий таймер.
 */
public class Stopwatch {
    private Timeline timeLine;
    private boolean timerIsON = false;
    private static final String TIMEFORMAT = "0:00:00";
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    private int milliseconds = 0;

    public Stopwatch(Label timer, Button start, Button stop) {
        createTimeLine(timer);
        setActionsOnButtons(timer, start, stop);
    }

    /**
     * Метод создания таймера.
     */
    private void createTimeLine(Label timer) {
        timeLine = new Timeline(new KeyFrame(Duration.millis(1),
                event -> timer.setText(getTimeString())));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.setAutoReverse(false);
    }

    /**
     * Метод для оформления окончания игры.
     */
    private void setActionsOnButtons(Label timer, Button start, Button stop) {
        start.setOnAction(event -> {
            Jigsaw.getNewFigureToPane();
            if (timerIsON) {
                setTimerSettings(timer);
                timeLine.play();
            } else {
                timeLine.play();
                timerIsON = true;
            }
        });
        stop.setOnAction(event -> {
            String result = TIMEFORMAT;
            if (timerIsON) {
                result = getTimeString();
                setTimerSettings(timer);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("Time: " + result + "\nMoves: " + Jigsaw.moves);
            Jigsaw.moves = 0;
            Jigsaw.movesLabel.setText("0");
            Jigsaw.clearViewer();
            Jigsaw.clearAllGrid();
            alert.showAndWait();
        });
    }

    private void setTimerSettings(Label timer) {
        hours = 0;
        minutes = 0;
        seconds = 0;
        milliseconds = 0;
        timeLine.pause();
        timerIsON = false;
        timer.setText(TIMEFORMAT);
    }

    private String getTimeString() {
        if (milliseconds == 1000) {
            seconds += 1;
            milliseconds = 0;
        }
        if (seconds == 60) {
            minutes += 1;
            seconds = 0;
        }
        if (minutes == 60) {
            hours += 1;
            minutes = 0;
        }
        milliseconds += 1;
        return (hours + ":" + (((minutes / 10) == 0) ? "0" : "") + minutes
                + ":" + (((seconds / 10) == 0) ? "0" : "") + seconds);
    }
}

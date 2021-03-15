package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ToolBar extends javafx.scene.control.ToolBar {

    private MainView mainView;
    private Keeper keeper;

    public ToolBar(MainView mainView) {
        this.mainView = mainView;
        keeper = new Keeper("sample.txt");
        Button step = new Button("Step");
        step.setOnAction(this::handleStep);
        Button draw = new Button("Draw");
        draw.setOnAction(this::handleDraw);
        Button erase = new Button("Erase");
        erase.setOnAction(this::handleErase);
        Button reset = new Button("Reset");
        reset.setOnAction(this::handleReset);
        Button start = new Button("Start");
        start.setOnAction(this::handleStart);
        Button stop = new Button("Stop");
        stop.setOnAction(this::handleStop);
        Button save = new Button("Save");
        save.setOnAction(this::handleSave);
        Button read = new Button("Read");
        read.setOnAction(this::handleRead);

        this.getItems().addAll(draw, erase, reset, step, start, stop, save, read);
    }


    private void handleSave(ActionEvent actionEvent) {
        this.mainView.getSimulation().printBoard();
        this.keeper.createFile();
        this.keeper.writeToFile(mainView);
    }


    private void handleRead(ActionEvent actionEvent) {

        this.mainView.setApplicationState(MainView.SIMULATING);
        Simulation readSimulation = new Simulation(mainView.getSimulation().width, mainView.getSimulation().height);
        readSimulation.board = this.keeper.readFromFile(mainView);
        for (int x = 0; x < mainView.getSimulation().width; x++) {
            for (int y = 0; y < mainView.getSimulation().height; y++) {
                readSimulation.setState(x, y, readSimulation.getState(x, y));
            }
        }
        this.mainView.setSimulation(readSimulation);
        Simulator simulator = new Simulator(this.mainView, readSimulation);
        this.mainView.setSimulator(simulator);
        this.mainView.draw();
    }

    private void handleStop(ActionEvent actionEvent) {
        this.mainView.getSimulator().stop();
    }

    private void handleStart(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.SIMULATING);
        this.mainView.getSimulator().start();
    }

    private void handleReset(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.EDITING);
        this.mainView.draw();
    }

    private void handleErase(ActionEvent actionEvent) {
        System.out.println("erase");
        this.mainView.setDrawMode(Simulation.DEAD);
    }

    private void handleDraw(ActionEvent actionEvent) {
        System.out.println("draw");
        this.mainView.setDrawMode(Simulation.ALIVE);
    }

    private void handleStep(ActionEvent actionEvent) {
        System.out.println("step");

        this.mainView.setApplicationState(MainView.SIMULATING);

        this.mainView.getSimulation().step();
        this.mainView.draw();
    }
}

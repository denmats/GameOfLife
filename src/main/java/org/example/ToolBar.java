package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ToolBar extends javafx.scene.control.ToolBar {

    private MainView mainView;

    public ToolBar(MainView mainView){
        this.mainView = mainView;
        Button step = new Button("Step");
        step.setOnAction(this::handleStep);
        Button draw = new Button("Draw");
        draw.setOnAction(this::handleDraw);
        Button erase = new Button("Erase");
        erase.setOnAction(this::handleErase);
        Button reset = new Button("Reset");
        reset.setOnAction(this::handleReset);

        this.getItems().addAll(draw, erase, reset, step);
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

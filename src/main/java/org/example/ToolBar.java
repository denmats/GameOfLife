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

        this.getItems().addAll(draw, erase, step);
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
        this.mainView.getSimulation().step();
        this.mainView.draw();
    }
}

package org.example;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class MainView extends VBox {

    private Canvas canvas;
    private Affine affine;
    private Simulation simulation;
    private int drawMode = Simulation.ALIVE;

    public MainView() {

        this.canvas = new Canvas(400,400);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);

        this.setOnKeyPressed(this::getOnKeyPressed);

        ToolBar toolBar = new ToolBar(this);

        this.getChildren().addAll(toolBar, this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(400/10f,400/10f);

        this.simulation = new Simulation(10,10);
    }

    private void getOnKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.D){
            this.drawMode = Simulation.ALIVE; //Pressing key D set up "drawing mode"
        }else if(keyEvent.getCode() == KeyCode.E){
            this.drawMode = Simulation.DEAD; //Pressing key E set up "Erasing mode"
        }
    }


    private void handleDraw(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        System.out.println(mouseX+" , "+mouseY);

        try {
            Point2D simulationCoordinates = this.affine.inverseTransform(mouseX, mouseY);
            int simX = (int) simulationCoordinates.getX();
            int simY = (int) simulationCoordinates.getY();

            System.out.println(simX+" , "+simY);

            this.simulation.setState(simX,simY,drawMode);
            draw();
        } catch (NonInvertibleTransformException e) {
            System.out.println("Could not revert transform");
        }
    }

    public void draw(){
        GraphicsContext g = this.canvas.getGraphicsContext2D();

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0,0,400,400);

        //set scale ratio for live cells displayed on the board
        g.setTransform(affine);

        //draw live cells
        g.setFill(Color.BLACK);
        for (int x = 0; x < this.simulation.width; x++) {
            for (int y = 0; y < this.simulation.height; y++) {
                if(simulation.getState(x,y) == Simulation.ALIVE){
                    g.fillRect(x,y,1,1);
                }
            }
        }

        //draw grid
        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05f);
        for (int x = 0; x < this.simulation.width; x++) {
            g.strokeLine(x,0,x,10);
        }
        for (int y = 0; y < this.simulation.height; y++) {
            g.strokeLine(0,y,10,y);
        }
    }

    public Simulation getSimulation() {
        return this.simulation;
    }

    public void setDrawMode(int newDrawMode) {
        this.drawMode = newDrawMode;
    }
}

package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App: Game of Life.
 * Author of code up to #7 Commit is https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqbVJqenJDUEI3aFQ0eDh4cFVzU2FfbFpUWWpIZ3xBQ3Jtc0ttY2VIOFdDNnM4STVoZ2ltMkRZZi00YXpCWkJqTkhubVI4M2EwSjhud21LcEVUMW1NYlRVZlhVVXl2Uk9neFlfdVY0WVJBNjhJY0N4LWJQckdvMG1PX0VybHVrai1lajdfX3dsTG4ybkhXNDZScE1odw&q=https%3A%2F%2Fgithub.com%2FTyjoh%2FGameOfLifeSimulator%2F
 * My contribution into project: commit #
 * Rules:
 * Any live cell with fewer than two live neighbours dies, as if by underpopulation
 * Any live cell with two or three live neighbours lives on to the next generation.
 * Any live cell with more than three live neighbours dies, as if by overpopulation.
 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
 *
 * Any live cell with two or three live neighbours survives.
 * Any dead cell with three live neighbours becomes a live cell.
 * All other live cells die in the next generation. Similarly, all other dead cells stay dead.
 *
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, 640, 480);
        stage.setScene(scene);
        stage.show();

        mainView.draw();
    }

    public static void main(String[] args) {
        launch();
    }

}
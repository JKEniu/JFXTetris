package com.grandrain.tetris.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 510);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/grandrain/tetris/images/icon.png")));
        stage.setTitle("Тетрис " + getClass().getPackage().getImplementationVersion());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
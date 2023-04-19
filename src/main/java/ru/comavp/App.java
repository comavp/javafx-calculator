package ru.comavp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        InputStream iconStream = getClass().getResourceAsStream("/icon.png");
        Image image = new Image(iconStream);

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/main.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.getIcons().add(image);
        stage.setTitle("Calculator");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

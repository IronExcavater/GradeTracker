package ironbyte.gradetracker;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.*;
import ironbyte.gradetracker.controller.AppController;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        DataManager.loadData();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/app-view.fxml"), null, null,
                _ -> new AppController(stage));
        Scene scene = new Scene(fxmlLoader.load(), 700, 450);

        Image icon = Utils.getImage("images/icon/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Grade Tracker");
        stage.setScene(scene);
        stage.setMinWidth(700);
        stage.setMinHeight(450);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getScene().setFill(Color.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
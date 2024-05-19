package com.auth.authentication.services;

import java.io.IOException;

import com.auth.authentication.AuthenticationApplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class SceneService extends AuthenticationApplication {

    private static Scene scene;
    
    public static void createScene(String title, String view) throws IOException {
        Stage stage = new Stage();

        Parent parent = loadFXML(view);

        scene = new Scene(parent);
        stage.setTitle(title);
        stage.setScene(scene);
        // stage.setWidth(750);
        // stage.setHeight(500);

        stage.show();
    }

    public static Scene changeScene(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        return scene;
    }

    private static Parent loadFXML(String view) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AuthenticationApplication.class.getResource("/templates/views/" + view + ".fxml"));
        fxmlLoader.setControllerFactory(configurableApplicationContext::getBean);
        return fxmlLoader.load();
    }
}

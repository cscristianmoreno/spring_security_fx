package com.auth.authentication.controller.login;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.auth.authentication.dao.UsersDAO;
import com.auth.authentication.fxml.LoginFXML;
import com.auth.authentication.models.IObservating;
import com.auth.authentication.models.IObserverAuthFailed;
import com.auth.authentication.models.IObserverAuthSuccess;
import com.auth.authentication.services.ObserverService;
import com.auth.authentication.services.SceneService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

@Component
public class LoginController extends LoginFXML implements Initializable, IObservating, IObserverAuthSuccess, IObserverAuthFailed {

    private final UsersDAO usersDAO;
    private final ObserverService observerService;

    public LoginController(final UsersDAO usersDAO, final ObserverService observerService) {
        this.usersDAO = usersDAO;
        this.observerService = observerService;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        addObservating();
    }

    @FXML
    public void onLogin(MouseEvent event) {
        String username = fieldUsername.getText();
        String password = fieldPassword.getText();
        usersDAO.authentication(username, password);
    }

    @Override
    public void addObservating() {
        observerService.addObserver("LoginController", this);
    }

    @Override
    public void notifyAuthenticationSuccess() {
        try {
            SceneService.changeScene("main");
            observerService.notifyAuthenticationSuccess("MainController");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyAuthenticationFailed() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Credenciales incorrectas");
        alert.show();
    }
}

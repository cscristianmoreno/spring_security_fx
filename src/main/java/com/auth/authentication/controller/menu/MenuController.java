package com.auth.authentication.controller.menu;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.stereotype.Component;

import com.auth.authentication.fxml.MenuFXML;
import com.auth.authentication.services.LogoutService;
import com.jfoenix.controls.JFXButton;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

@Component
public class MenuController extends MenuFXML implements Initializable {

    enum MenuItems  {
        HOME,
        USERS,
        LOGOUT
    };

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        int pos = 0;

        for (MenuItems items : MenuItems.values()) {
            JFXButton jfxButton = (JFXButton) menu.getChildren().get(pos);
            jfxButton.setOnMouseClicked(eventHandler(items));
            pos++;
        }
    }
    
    private EventHandler<MouseEvent> eventHandler(MenuItems item) {
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                switch(item) {
                    case USERS: {
                        showUsers();
                        break;
                    }
                    case LOGOUT: {
                        LogoutService.logout();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }

        };

        return mouseEvent;
    }

    private void showUsers() {
        
    }
}

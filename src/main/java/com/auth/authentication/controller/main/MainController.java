package com.auth.authentication.controller.main;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.stereotype.Component;

import com.auth.authentication.fxml.MainFXML;
import com.auth.authentication.models.IObservating;
import com.auth.authentication.models.IObserverAuthSuccess;
import com.auth.authentication.security.CustomUserDetails;
import com.auth.authentication.services.LogoutService;
import com.auth.authentication.services.ObserverService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogEvent;
import javafx.scene.input.MouseEvent;

@Component
public class MainController extends MainFXML implements Initializable, IObserverAuthSuccess, IObservating {

    private final ObserverService observerService;
    private final JwtDecoder jwtDecoder;
    private final JwtEncoder jwtEncoder;
    private String token = "";

    public MainController(final ObserverService observerService, final JwtDecoder jwtDecoder, final JwtEncoder jwtEncoder) {
        this.observerService = observerService;
        this.jwtDecoder = jwtDecoder;
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        addObservating();
    }

    @FXML
    public void onTokenVerify(MouseEvent event) {
        try {
            Jwt jwt = jwtDecode(token);
            System.out.println(jwt.getClaims());
        }
        catch (JwtValidationException e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Tu token ha expirado");

            alert.setOnCloseRequest(new EventHandler<DialogEvent>() {

                @Override
                public void handle(DialogEvent arg0) {
                    LogoutService.logout();
                }
                
            });

            alert.show();
        }
    }

    @Override
    public void addObservating() {
        observerService.addObserver("MainController", this);
    }

    @Override
    public void notifyAuthenticationSuccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
        token = userDetails.getToken();

        String subtitle = String.format("Bienvenido %s, has iniciado sesión como: %s",
        userDetails.getName(),
        userDetails.getUsername());
        subtitleId.setText(subtitle);

        authenticationClassId.setText(authentication.getClass().getSimpleName());
        
        Jwt jwt = jwtDecode(token);

        tokenEncodeClassId.setText(jwtEncoder.getClass().getSimpleName());
        tokenDecodeClassId.setText(jwt.getClass().getSimpleName());
    }

    private Jwt jwtDecode(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt;
    }
}

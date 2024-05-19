package com.auth.authentication.controller.table;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.auth.authentication.dto.AuthenticationDTO;
import com.auth.authentication.fxml.TableFXML;
import com.auth.authentication.security.CustomUserDetails;
import com.auth.authentication.services.TableService;

import javafx.fxml.Initializable;

@Component
public class TableAuthController extends TableFXML implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        new TableService(table);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getDetails();

        AuthenticationDTO authenticationDTO = new AuthenticationDTO();

        authenticationDTO.setName(customUserDetails.getName());
        authenticationDTO.setLastname(customUserDetails.getLastname());
        authenticationDTO.setAge(customUserDetails.getAge());
        authenticationDTO.setType(authentication.getClass().getSimpleName());
        authenticationDTO.setPrincipal(authentication.getName());
        authenticationDTO.setAuthorities(authentication.getAuthorities());
        authenticationDTO.setCredentials(authentication.getCredentials());
        authenticationDTO.setAuthenticated(authentication.isAuthenticated());
        authenticationDTO.setDetails(authentication.getDetails().toString());

        table.getItems().add(authenticationDTO);
    }
    
}

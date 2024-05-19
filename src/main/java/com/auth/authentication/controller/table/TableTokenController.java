package com.auth.authentication.controller.table;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import com.auth.authentication.dto.TokenDTO;
import com.auth.authentication.fxml.TableFXML;
import com.auth.authentication.security.CustomUserDetails;
import com.auth.authentication.services.TableService;
import javafx.fxml.Initializable;

@Component
public class TableTokenController extends TableFXML implements Initializable {

    private final JwtDecoder jwtDecoder;

    public TableTokenController(final JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        new TableService(table);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getDetails();
        String token = customUserDetails.getToken();

        Jwt jwt = jwtDecoder.decode(token);

        System.out.println(jwt.getClaimAsString("scope"));
        TokenDTO tokenDTO = new TokenDTO();

        tokenDTO.setExpire(jwt.getExpiresAt());
        tokenDTO.setIssuedAt(jwt.getIssuedAt());
        tokenDTO.setIssuer(jwt.getClaimAsString("iss"));
        tokenDTO.setSubject(jwt.getSubject());
        tokenDTO.setScope(jwt.getClaimAsString("scope"));

        table.getItems().add(tokenDTO);
    }   
}

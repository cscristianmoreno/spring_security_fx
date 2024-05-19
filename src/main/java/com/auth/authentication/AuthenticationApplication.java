package com.auth.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.auth.authentication.services.SceneService;

import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication
public class AuthenticationApplication extends Application {
	protected static ConfigurableApplicationContext configurableApplicationContext; 

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		configurableApplicationContext = SpringApplication.run(AuthenticationApplication.class);
		SceneService.createScene("Autenticación", "login");
	}

	@Override 
	public void stop() {
		configurableApplicationContext.close();
	}
}

package com.main;

import com.controller.MainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		AnchorPane root = (AnchorPane) FXMLLoader.load(MainController.class.getResource("/com/ui/main.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
//		primaryStage.getIcons().add(new Image("com/coder/balance/resource/sing.jpg"));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Managment System");
		primaryStage.show();
	}
}

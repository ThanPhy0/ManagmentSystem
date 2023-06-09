package com.main;

import com.controller.FormController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		AnchorPane root = (AnchorPane) FXMLLoader.load(FormController.class.getResource("/com/ui/form.fxml"));
		Scene scene = new Scene(root);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
//		primaryStage.getIcons().add(new Image("/Images/add.png"));
//		primaryStage.setResizable(false);
		primaryStage.setTitle("Managment System");
		primaryStage.show();
	}
}

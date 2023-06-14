package com.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class FormController implements Initializable {

	@FXML
	private BorderPane borderPane;

	@FXML
	private ImageView exit;

	@FXML
	private Label Menu;

	@FXML
	private Label MenuClose;

	@FXML
	private AnchorPane slider;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setUI();
	}

	public void setUI() {
		showUI("main.fxml");
		exit.setOnMouseClicked(event -> {
			System.exit(0);
		});
//		slider.setTranslateX(-176);
//		Menu.setOnMouseClicked(event -> {
//			TranslateTransition slide = new TranslateTransition();
//			slide.setDuration(Duration.seconds(0.5));
//			slide.setNode(slider);
//			slide.setToX(0);
//			slide.play();
//
//			slider.setTranslateX(-176);
//
//			slide.setOnFinished((ActionEvent e) -> {
//				Menu.setVisible(false);
//				MenuClose.setVisible(true);
//			});
//		});
//
//		MenuClose.setOnMouseClicked(event -> {
//			TranslateTransition slide = new TranslateTransition();
//			slide.setDuration(Duration.seconds(0.5));
//			slide.setNode(slider);
//			slide.setToX(-176);
//			slide.play();
//
//			slider.setTranslateX(-176);
//
//			slide.setOnFinished((ActionEvent e) -> {
//				Menu.setVisible(true);
//				MenuClose.setVisible(false);
//			});
//		});

	}

	public void showUI(String fxml) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ui/" + fxml));
		try {
			Node content = loader.load();
			borderPane.setCenter(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void dashboardPane(ActionEvent event) {
		showUI("main.fxml");

	}

	@FXML
	public void menuPane(ActionEvent event) {
		showUI("menu.fxml");
	}

	@FXML
	public void girlsPane(ActionEvent event) {
		showUI("girls.fxml");
	}

	@FXML
	public void newRoom(ActionEvent event) {
		showUI("new_room.fxml");
	}
}

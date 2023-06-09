package com.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.database.Fetch;
import com.database.GirlsList;
import com.database.MenuItems;
import com.database.UpdateRoom;
import com.model.Girls;
import com.model.Menu;
import com.model.Orders;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController implements Initializable {
	// For Noti
	@FXML
	private AnchorPane notiForm;

	@FXML
	private Label showMessage;

	// Orders Tab
	@FXML
	private GridPane gridPane;

	@FXML
	private RowConstraints rowConstraints;

	@FXML
	private Label datetime;

	@FXML
	private Label currentDateTime;

	@FXML
	private Label room;

	@FXML
	private Label personCount;

	@FXML
	private Label section;

	@FXML
	private TableView<Orders> orderTable;

	@FXML
	private TableColumn<Orders, String> colOrders;

	@FXML
	private TableColumn<Orders, Integer> colQuantity;

	@FXML
	private TableColumn<Orders, Integer> colPrice;

	@FXML
	private ListView<String> listGirls;

	// Menu Tab

	// Girls Tab

	private int rooms;

	// for end section time
	private List<String> aryendSection;

	// for room active or not from column active, 1 is true and 0 is false.
	private List<Integer> aryActive;

	// for section end time
	private boolean checkendSection = false;

	private Timeline timeLine = new Timeline();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Fetch fetch = new Fetch();
		rooms = fetch.getRoom().size();
		refreshTime();
		System.out.println(checkendSection);

		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(10);
		gridPane.setVgap(25);

		getActiveStatusAndendSection();

		// adjust the row size for GridPane
		RowConstraints rowConstraints = new RowConstraints();
		rowConstraints.setPercentHeight(100.0 / fetch.getRoom().size());
		gridPane.getRowConstraints().add(rowConstraints);

		// Orders Tab
		colOrders.setCellValueFactory(new PropertyValueFactory<Orders, String>("name"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("quantity"));
		colPrice.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("price"));

		// Menu Tab

	}

	public void getActiveStatusAndendSection() {
		Fetch fetch = new Fetch();
		rooms = fetch.getRoom().size();
		aryendSection = new ArrayList<>();
		aryActive = new ArrayList<>();

		for (int i = 1; i <= rooms; i++) {
			aryendSection.add(fetch.getendSection(i));
			aryActive.add(fetch.getActiveStatus(i));
			System.out.println(fetch.getendSection(i));
		}
	}

	public String untilFiveMin() {
		LocalDateTime dt = LocalDateTime.now();
		LocalDateTime minusFiveMin = dt.minusMinutes(5);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM uuuu - hh:mm a");
		String fiveMinMinus = minusFiveMin.format(format);
		return fiveMinMinus;
	}

	public void refreshTime() {
		timeLine.getKeyFrames().clear();
		timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
			LocalDateTime datetime = LocalDateTime.now();
			currentDateTime.setText(datetime.format(DateTimeFormatter.ofPattern("dd MMM uuuu - hh:mm a")));
			GridPaneSetUp(rooms);
			if (checkendSection) {
				PauseTransition pause = new PauseTransition(Duration.minutes(1));
				pause.setOnFinished(e -> {
					// Code to execute after the delay
					// For example, resume the timeline
					checkendSection = false;
					timeLine.play();
				});
				pause.play();
				timeLine.pause(); // Pause the timeline during the delay
			}
		}));
		timeLine.setCycleCount(Animation.INDEFINITE);
		timeLine.play();
	}

	public void GridPaneSetUp(int rooms) {
		Fetch fetch = new Fetch();
		UpdateRoom updateRoom = new UpdateRoom();

		int numCols = 2;
		int numRows = (rooms + numCols - 1) / numCols;

		int i = 1;
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (i > rooms) {
					break; // Stop adding buttons if we have reached the total number of rooms
				}
				Button button = new Button(String.valueOf(i));
				button.setPrefWidth(40);
				button.setPrefHeight(40);
				button.setStyle("-fx-background-color: #00fc17;");

				// this string is end section time get from mysql table and put it as in array
				// list.
//				System.out.println("count - " + aryendSection.get(Integer.valueOf(button.getText())-1));
				String endSec = aryendSection.get(Integer.valueOf(button.getText()) - 1);
				int activeStatus = aryActive.get(Integer.valueOf(button.getText()) - 1);

				if (endSec.equals(untilFiveMin())) {
					showNotification("Room " + i + " will end in next 5 minutes!");
				}

				if (endSec.equals(currentDateTime.getText())) {
					button.setStyle("-fx-background-color: #ff0000;");
					if (!checkendSection) {
						if (activeStatus == 1) {
							updateRoom.updateActiveStatus(0, i);
							getActiveStatusAndendSection();
							button.setStyle("-fx-background-color: #ff0000;");
						}
						System.out.println("end - " + endSec + currentDateTime.getText());
						System.out.println("button - " + i);
						checkendSection = true;
					}
				}

				if (activeStatus == 0) {
					button.setStyle("-fx-background-color: #ff0000;");
				}
				gridPane.add(button, col, row);
				button.setOnMouseClicked(event -> {
					if (event.getClickCount() == 2) {
						int btnId = Integer.valueOf(button.getText());
						System.out.println(button.getText());
						datetime.setText(String.valueOf(fetch.setDateTime(btnId)));
						fetch.setRecord(btnId, room, personCount, section);
						ObservableList<Orders> obOrders = fetch.setOrders(btnId);
						ObservableList<String> obGirls = fetch.setGirls(btnId);
						orderTable.setItems(obOrders);
						listGirls.setItems(obGirls);
					}
				});

				i++;
			}
		}
	}

	public void showNotification(String end) {
		notiForm.setVisible(true);
		notiForm.setStyle("-fx-background-color: #4287f5;");
		notiForm.setStyle("-fx-background-radius: 30;");
		showMessage.setText(end);

		Timeline tl = new Timeline(new KeyFrame(Duration.minutes(1), event -> notiForm.setVisible(false)));
		timeLine.setCycleCount(Animation.INDEFINITE);
		tl.play();

	}

	public void checkOut() {

	}

	public void newRoom() {
		Stage stage = new Stage();
		try {
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(NewRoomController.class.getResource("/com/ui/new_room.fxml"));
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Girls Tab

}

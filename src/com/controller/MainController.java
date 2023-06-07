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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController implements Initializable {
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

	@FXML
	private Tab menuTab;

	@FXML
	private TableView<Menu> tableMenu;

	@FXML
	private TableColumn<Menu, String> menuName;

	@FXML
	private TableColumn<Menu, Integer> menuPrice;

	@FXML
	private TextField menuNameInput;

	@FXML
	private TextField menuPriceInput;

	// Girls Tab
	@FXML
	private ListView<String> girls;

	@FXML
	private TextField girlsNameInput;

	private int rooms;

	// for end section time
	private List<String> aryendSection;

	// for room active or not from column active, 1 is true and 0 is false.
	private List<Integer> aryActive;

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
		menuName.setCellValueFactory(new PropertyValueFactory<Menu, String>("name"));
		menuPrice.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("price"));
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

	public void grid() {
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		VBox vBox = new VBox();
		vBox.setSpacing(10);

		int a = 0;
		for (int i = 0; i < 6; i++) {
			Button btn = new Button(String.valueOf(a));
			vBox.getChildren().add(btn);

			ScrollPane scrollPane = new ScrollPane(vBox);
			scrollPane.setFitToHeight(true);

			gridPane.add(scrollPane, 0, 0);
		}
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

	// Menu Tab
	public void setMenuTab() {
		MenuItems menuItems = new MenuItems();
		ObservableList<Menu> obMenu = menuItems.setMenu();
		tableMenu.setItems(obMenu);
	}

	public void insertNewMenu() {
		if (menuNameInput.getText().isEmpty() || menuPriceInput.getText().isEmpty()) {
			System.out.println("Insert menu name and price!");
		} else {
			MenuItems menuItems = new MenuItems();
			Menu menu = new Menu();
			menu.setName(menuNameInput.getText().toString());
			menu.setPrice(Integer.valueOf(menuPriceInput.getText()));
			menuItems.add(menu);
		}
	}

	public void editMenuItem() {
		if (tableMenu.getSelectionModel().getSelectedItem() == null) {
			System.out.println("select item first!");
		} else {
			menuNameInput.setText(tableMenu.getSelectionModel().getSelectedItem().getName());
			menuPriceInput.setText(String.valueOf(tableMenu.getSelectionModel().getSelectedItem().getPrice()));
		}
	}

	public void updateMenuItem() {
		if (menuNameInput.getText().isEmpty() || menuPriceInput.getText().isEmpty()) {
			System.out.println("chose item and click edit button!");
		} else {
			String listItemName = tableMenu.getSelectionModel().getSelectedItem().getName();
			MenuItems menuItems = new MenuItems();
			Menu menu = new Menu();
			menu.setName(menuNameInput.getText().toString());
			menu.setPrice(Integer.valueOf(menuPriceInput.getText()));
			menuItems.edit(menu, listItemName);
		}
	}

	// Girls Tab
	public void setGirlsTab() {
		GirlsList girlsList = new GirlsList();
		ObservableList<String> obGirls = girlsList.listGirls();
		girls.setItems(obGirls);
	}

	public void insertNewGirls() {
		if (girlsNameInput.getText().isEmpty()) {
			System.out.println("Insert Girl Name!");
		} else {
			GirlsList girlsList = new GirlsList();
			Girls girls = new Girls();
			girls.setName(girlsNameInput.getText().toString());
			girlsList.add(girls);
		}
	}

	public void editGirlsItem() {
		if (girls.getSelectionModel().getSelectedItem() == null) {
			System.out.println("select item first!");
		} else {
			girlsNameInput.setText(girls.getSelectionModel().getSelectedItem());
		}
	}

	public void updateGirlsItem() {
		if (girlsNameInput.getText().isEmpty()) {
			System.out.println("chose item and click edit button!");
		} else {
			String listItemName = girls.getSelectionModel().getSelectedItem();
			GirlsList girlsList = new GirlsList();
			Girls girls = new Girls();
			girls.setName(girlsNameInput.getText().toString());
			girlsList.edit(girls, listItemName);
		}
	}

}

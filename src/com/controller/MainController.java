package com.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.database.Fetch;
import com.database.GirlsList;
import com.database.MenuItems;
import com.model.Girls;
import com.model.Menu;
import com.model.Orders;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable {
	// Orders Tab
	@FXML
	private ListView<Integer> listView;

	@FXML
	private Label datetime;

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Fetch fetch = new Fetch();
		ObservableList<Integer> obList = fetch.getRoom();
		listView.setItems(obList);

		// Orders Tab
		colOrders.setCellValueFactory(new PropertyValueFactory<Orders, String>("name"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("quantity"));
		colPrice.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("price"));

		// Menu Tab
		menuName.setCellValueFactory(new PropertyValueFactory<Menu, String>("name"));
		menuPrice.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("price"));
	}

	// Orders Tab
	public void setItem() {
		if (listView.getSelectionModel().getSelectedItem() == null) {
			System.out.println("Select a item!");
		} else {
			int id = listView.getSelectionModel().getSelectedItem();

			if (listView.getSelectionModel().getSelectedItem() == id) {
				Fetch fetch = new Fetch();
//				ObservableList<Room> ob = mysqlDB.setRecord(id, room, personCount, section);
				datetime.setText(String.valueOf(fetch.setDateTime(id)));
				fetch.setRecord(id, room, personCount, section);
				ObservableList<Orders> obOrders = fetch.setOrders(id);
				ObservableList<String> obGirls = fetch.setGirls(id);
				orderTable.setItems(obOrders);
				listGirls.setItems(obGirls);

			}
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

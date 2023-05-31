package com.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.database.Fetch;
import com.database.GirlsList;
import com.database.MenuItems;
import com.database.MySqlDB;
import com.model.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class NewRoomController implements Initializable {

	@FXML
	private ComboBox<Integer> roomList;

	@FXML
	private ComboBox<Integer> personCount;

	@FXML
	private Spinner<Integer> sectionTime;

	@FXML
	private Spinner<Integer> quantity;

	@FXML
	private TableView<Menu> menuTable;

	@FXML
	private TableColumn<Menu, String> colName;

	@FXML
	private TableColumn<Menu, Integer> colPrice;

	@FXML
	private ListView<String> girlsList;

	@FXML
	private Label orderName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Fetch fetch = new Fetch();
		MySqlDB mysqlDB = new MySqlDB();
		MenuItems menuItems = new MenuItems();
		GirlsList gList = new GirlsList();

		ObservableList<Integer> obRoom = fetch.getRoom();
		roomList.setItems(obRoom);

		ObservableList<Integer> obPerson = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		personCount.setItems(obPerson);

		ObservableList<Integer> obSection = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		SpinnerValueFactory<Integer> value = new SpinnerValueFactory.ListSpinnerValueFactory<>(obSection);
		sectionTime.setValueFactory(value);
		quantity.setValueFactory(value);

		ObservableList<Menu> obMenu = menuItems.setMenu();
		colName.setCellValueFactory(new PropertyValueFactory<Menu, String>("name"));
		colPrice.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("price"));
		menuTable.setItems(obMenu);

		ObservableList<String> obGirls = gList.listGirls();
		girlsList.setItems(obGirls);
	}

	@FXML
	public void tableClick(MouseEvent event) {
		if (event.getClickCount() == 2) {
			Menu selectedItem = menuTable.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				orderName.setText(selectedItem.getName());
			}
		}
	}

}

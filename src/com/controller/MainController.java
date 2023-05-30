package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.database.Fetch;
import com.database.GirlsList;
import com.database.MenuItems;
import com.model.Menu;
import com.model.Orders;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {
//Orders Tab
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

//Menu Tab
	@FXML
	private TableView<Menu> tableMenu;

	@FXML
	private TableColumn<Menu, String> menuName;

	@FXML
	private TableColumn<Menu, Integer> menuPrice;

	@FXML
	private Tab menuTab;

//Girls Tab
	@FXML
	private ListView<String> girls;

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

	public void setItem() {
		if (listView.getSelectionModel().getSelectedItem() == null) {
			System.out.println("Select a item!");
		} else {
			int id = listView.getSelectionModel().getSelectedItem();

			if (listView.getSelectionModel().getSelectedItem() == id) {
				Fetch fetch = new Fetch();
//				ObservableList<Room> ob = mysqlDB.setRecord(id, room, personCount, section);
				fetch.setRecord(id, room, personCount, section);
				ObservableList<Orders> obOrders = fetch.setOrders(id);
				ObservableList<String> obGirls = fetch.setGirls(id);
				orderTable.setItems(obOrders);
				listGirls.setItems(obGirls);

			}
		}
	}

	public void setMenuTab() {
		MenuItems menuItems = new MenuItems();
		ObservableList<Menu> obMenu = menuItems.setMenu();
		tableMenu.setItems(obMenu);
	}

	public void setGirlsTab() {
		GirlsList girlsList = new GirlsList();
		ObservableList<String> obGirls = girlsList.listGirls();
		girls.setItems(obGirls);
	}

}

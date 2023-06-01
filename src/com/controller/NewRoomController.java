package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.database.Fetch;
import com.database.GirlsList;
import com.database.MenuItems;
import com.database.MySqlDB;
import com.model.Menu;
import com.model.OrderTable;

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
import javafx.scene.control.TextField;
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

	@FXML
	private Label orderPrice;

	@FXML
	private TableView<OrderTable> orderTable;

	@FXML
	private TableColumn<OrderTable, String> colOrderName;

	@FXML
	private TableColumn<OrderTable, Integer> colOrderQuantity;

	@FXML
	private TableColumn<OrderTable, Integer> colOrderPrice;

	@FXML
	private ListView<String> inviteGirls;

	@FXML
	private ComboBox<Menu> menuNames;

	@FXML
	private TextField orderQuantity;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Fetch fetch = new Fetch();
		MySqlDB mysqlDB = new MySqlDB();
		MenuItems menuItems = new MenuItems();
		GirlsList gList = new GirlsList();
		Menu menu = new Menu();

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

		ObservableList<Menu> obMite = menuItems.setMenu();
		menuNames.setItems(obMite);

		ObservableList<String> obGirls = gList.listGirls();
		girlsList.setItems(obGirls);

		colOrderName.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("name"));
		colOrderQuantity.setCellValueFactory(new PropertyValueFactory<OrderTable, Integer>("quantity"));
		colOrderPrice.setCellValueFactory(new PropertyValueFactory<OrderTable, Integer>("price"));
	}

	public void orderAdd() {
		MenuItems menuItems = new MenuItems();
		Menu selectedItem = menuNames.getSelectionModel().getSelectedItem();
		if (menuNames != null) {
			String name = selectedItem.toString();
			int price = selectedItem.getPriceAsString();
			OrderTable order = new OrderTable(name,
					Integer.valueOf(orderQuantity.getText()), price);
			ObservableList<OrderTable> ob = orderTable.getItems();
			ob.add(order);
			orderTable.setItems(ob);
			orderQuantity.setText("");
		} else {
			System.out.println("choose menu!");
		}
	}

	@FXML
	public void tableClick(MouseEvent event) {
		if (event.getClickCount() == 2) {
			Menu selectedItem = menuTable.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				orderName.setText(selectedItem.getName());
				orderPrice.setText(String.valueOf(selectedItem.getPrice()));
			}
		}
	}

	@FXML
	public void girlsListClick(MouseEvent event) {
		if (event.getClickCount() == 2) {
			String girlName = girlsList.getSelectionModel().getSelectedItem().toString();
			if (girlName != null) {
				inviteGirls.getItems().add(girlName);
			}
		}
	}

	public void addtoCart() {
		if (orderName.getText().isEmpty() || orderPrice.getText().isEmpty()) {
			System.out.println("double click to you order item");
		} else {
			OrderTable order = new OrderTable(orderName.getText(), quantity.getValue(),
					Integer.valueOf(orderPrice.getText()));
			ObservableList<OrderTable> obList = orderTable.getItems();
			obList.add(order);
			orderTable.setItems(obList);
			orderName.setText("");
			orderPrice.setText("");
			quantity.getValueFactory().setValue(1);
		}
	}
}

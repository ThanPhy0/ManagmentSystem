package com.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.database.Fetch;
import com.database.GirlsList;
import com.database.MenuItems;
import com.database.NewRoomOrders;
import com.model.Menu;
import com.model.NewOrder;
import com.model.OrderTable;
import com.model.Room;

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

public class NewRoomController implements Initializable {

	@FXML
	private Label roomNumber;

	@FXML
	private ComboBox<Integer> personCount;

	@FXML
	private Spinner<Integer> sectionTime;

//	@FXML
//	private Label orderName;
//
//	@FXML
//	private Label orderPrice;

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
	private ComboBox<String> girlNames;

	@FXML
	private TextField orderQuantity;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Fetch fetch = new Fetch();
		MenuItems menuItems = new MenuItems();
		GirlsList gList = new GirlsList();

		// RoomList
		ObservableList<Integer> obRoom = fetch.getRoom();
		int newRoomNumber = obRoom.size() + 1;
		roomNumber.setText(String.valueOf(newRoomNumber));

		// Person Count
		ObservableList<Integer> obPerson = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		personCount.setItems(obPerson);

		// Section Time
		ObservableList<Integer> obSection = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		SpinnerValueFactory<Integer> value = new SpinnerValueFactory.ListSpinnerValueFactory<>(obSection);
		sectionTime.setValueFactory(value);

		// Menu Items
		ObservableList<Menu> obMite = menuItems.setMenu();
		menuNames.setItems(obMite);

		// Girls List
		ObservableList<String> girlsList = gList.listGirls();
		girlNames.setItems(girlsList);

//		ObservableList<String> obGirls = gList.listGirls();
//		girlsList.setItems(obGirls);

		colOrderName.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("name"));
		colOrderQuantity.setCellValueFactory(new PropertyValueFactory<OrderTable, Integer>("quantity"));
		colOrderPrice.setCellValueFactory(new PropertyValueFactory<OrderTable, Integer>("price"));
	}

	// SaveNewRoom
	public void SaveNewRoom() {
		NewRoomOrders roomOrders = new NewRoomOrders();
//		//Room
		Room room = new Room();
		room.setRoom(Integer.valueOf(roomNumber.getText()));
		room.setPersonCount(personCount.getValue());
		room.setSection(sectionTime.getValue());
		room.setDate(LocalDate.now());
		roomOrders.addNewRoom(room);

		MenuItems menuItems = new MenuItems();
		GirlsList gList = new GirlsList();
		int newOrder = menuItems.getMenuId("Lipo");
//		System.out.println(newOrder);

		// Order
		NewOrder nOrder = new NewOrder();
		for (int i = 0; i < orderTable.getItems().size(); i++) {
			int a = menuItems.getMenuId(colOrderName.getCellData(i));
			System.out.println(colOrderQuantity.getCellData(i));
			nOrder.setRoom(Integer.valueOf(roomNumber.getText()));
			nOrder.setMenu(a);
			nOrder.setQuantity(colOrderQuantity.getCellData(i));
			roomOrders.addNewOrder(nOrder);
		}
		for (String out : inviteGirls.getItems()) {
			int a = gList.getGirlId(out);
			System.out.println(a);
			nOrder.setRoom(Integer.valueOf(roomNumber.getText()));
			nOrder.setGirlsId(a);
			roomOrders.addInviteGirls(nOrder);
		}
	}

	public void orderAdd() {
		Menu selectedItem = menuNames.getSelectionModel().getSelectedItem();
		if (menuNames != null) {
			String name = selectedItem.toString();
			int price = selectedItem.getPriceAsString();
			int quantity = Integer.valueOf(orderQuantity.getText());
			OrderTable order = new OrderTable(name, quantity, quantity * price);
			ObservableList<OrderTable> ob = orderTable.getItems();
			ob.add(order);
			orderTable.setItems(ob);
			orderQuantity.setText("");
		} else {
			System.out.println("choose menu!");
		}
	}

	public void girlsAdd() {
		if (girlNames.getValue() != null) {
			ObservableList<String> obGirls = inviteGirls.getItems();
			obGirls.add(girlNames.getValue());
			inviteGirls.setItems(obGirls);
		} else {
			System.out.println("Choose girl first!");
		}
	}

//	@FXML
//	public void tableClick(MouseEvent event) {
//		if (event.getClickCount() == 2) {
//			Menu selectedItem = menuTable.getSelectionModel().getSelectedItem();
//			if (selectedItem != null) {
//				orderName.setText(selectedItem.getName());
//				orderPrice.setText(String.valueOf(selectedItem.getPrice()));
//			}
//		}
//	}

//	@FXML
//	public void girlsListClick(MouseEvent event) {
//		if (event.getClickCount() == 2) {
//			String girlName = girlsList.getSelectionModel().getSelectedItem().toString();
//			if (girlName != null) {
//				inviteGirls.getItems().add(girlName);
//			}
//		}
//	}

//	public void addtoCart() {
//		if (orderName.getText().isEmpty() || orderPrice.getText().isEmpty()) {
//			System.out.println("double click to you order item");
//		} else {
//			OrderTable order = new OrderTable(orderName.getText(), quantity.getValue(),
//					Integer.valueOf(orderPrice.getText()));
//			ObservableList<OrderTable> obList = orderTable.getItems();
//			obList.add(order);
//			orderTable.setItems(obList);
//			orderName.setText("");
//			orderPrice.setText("");
//			quantity.getValueFactory().setValue(1);
//		}
//	}
}

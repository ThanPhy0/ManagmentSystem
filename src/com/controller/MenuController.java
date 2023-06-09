package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.database.MenuItems;
import com.model.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MenuController implements Initializable {
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		menuName.setCellValueFactory(new PropertyValueFactory<Menu, String>("name"));
		menuPrice.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("price"));

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

	public void editMenuItem() {
		if (tableMenu.getSelectionModel().getSelectedItem() == null) {
			System.out.println("select item first!");
		} else {
			menuNameInput.setText(tableMenu.getSelectionModel().getSelectedItem().getName());
			menuPriceInput.setText(String.valueOf(tableMenu.getSelectionModel().getSelectedItem().getPrice()));
		}
	}

}

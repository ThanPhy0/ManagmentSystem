package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.database.GirlsList;
import com.model.Girls;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class GirlsController implements Initializable {

	@FXML
	private ListView<String> girls;

	@FXML
	private TextField girlsNameInput;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
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

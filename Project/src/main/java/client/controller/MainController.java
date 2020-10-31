package client.controller;

import client.model.Ship;
import client.model.SimpleTableModel;
import globalmodels.ShipInformation;

public class MainController {//обрабатывает действие пользователя
    private Ship ship;

    public MainController(Ship ship) {
        this.ship = ship;
    }

    public SimpleTableModel getSimpleTableModel() {
        return ship.getTable();
    }

    public void addButtonClicked() {
        ship.getTable().addRow();
    }

    public void portButtonClicked(String status, boolean priority) {
        ship.moveToPort(status, priority);
    }

    public void finishDownload() {
        ship.finishLoading();
    }

    public ShipInformation informationButtonClicked() {
        return ship.getInfo();
    }

}

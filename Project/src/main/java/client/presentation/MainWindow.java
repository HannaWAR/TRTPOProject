package client.presentation;

import client.controller.MainController;
import client.model.Ship;
import globalmodels.Cargo;
import globalmodels.ShipInformation;
import utils.Window;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;

import static utils.Constants.PROCEEDING_STATUS;

@SuppressWarnings("deprecation")
public class MainWindow extends Window {
    private final Ship ship;
    private JButton portButton;
    private JTable table;
    private JComboBox<String> loadBox;
    private JPanel panel;
    private JButton addButton;
    private JButton informationButton;
    private JCheckBox priorityCheckBox;
    private MainController controller;


    public MainWindow(Ship ship) {
        super("Ship");
        controller = new MainController(ship);
        this.ship = ship;
        init(panel);
        String name = JOptionPane.showInputDialog(this, "Введите имя корабля:");
        ship.getInfo().setName(name);

    }

    public static void main(String[] args) {
        try {
            new MainWindow(new Ship());
        } catch (ConnectException e) {
            JOptionPane.showMessageDialog(null, "Проблемы с сетью. Сервер не доступен. Повторите попытку позже.", "Ощибка", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ignored) {
        }
    }

    @Override
    public void initView() {
        loadBoxConfig();
        table.setModel(controller.getSimpleTableModel());

    }

    @Override
    public void initObservers() {//описания действий по событиям
        controller.getSimpleTableModel().wrongDataEvent.addObserver((observable, o) -> {
            JOptionPane.showMessageDialog(this, "Введите корректные данные", "Ошибка", JOptionPane.ERROR_MESSAGE);
        });

        ship.updateInfo.addObserver((observable, o) -> {
            var info = (ShipInformation) o;
            for (ActionListener actionListener : portButton.getActionListeners()) {
                portButton.removeActionListener(actionListener);
            }
            boolean enabled = !info.getStatus().equals(PROCEEDING_STATUS);
            loadBox.setEnabled(enabled);
            portButton.setText("В порт");
            for (ActionListener actionListener : portButton.getActionListeners()) {
                portButton.removeActionListener(actionListener);
            }
            portButton.addActionListener(actionEvent1 -> {
                controller.portButtonClicked(loadBox.getSelectedItem().toString(), priorityCheckBox.isSelected());
            });
            priorityCheckBox.setEnabled(enabled);
        });

        ship.noCargoes.addObserver((observable, o) -> {
            JOptionPane.showMessageDialog(this, "Корабль не может быть отправлен в порт. Заполните корректно таблицу грузов.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        });

        ship.successfulToPort.addObserver((observable, o) -> {
            loadBox.setEnabled(false);
            priorityCheckBox.setEnabled(false);
            portButton.setText("Завершить загрузку");
            for (ActionListener actionListener : portButton.getActionListeners()) {
                portButton.removeActionListener(actionListener);
            }
            portButton.addActionListener(actionEvent1 -> {
                controller.finishDownload();
            });
        });

    }

    @Override
    public void initController() {
        addButton.addActionListener(actionEvent -> {
            controller.addButtonClicked();
        });
        portButton.addActionListener(actionEvent -> {
            controller.portButtonClicked(loadBox.getSelectedItem().toString(), priorityCheckBox.isSelected());
        });

        informationButton.addActionListener(actionEvent -> {
            var info = controller.informationButtonClicked();
            StringBuilder string = new StringBuilder();
            string.append(String.format("Статус: %s\n", info.getStatus()));
            string.append(String.format("%s\nГрузы:\n", info.isPriority() ? "Приоритетный" : "Неприоритетный"));
            if (info.getCargoes() == null) {
                string.append("Грузы отстутсвуют");
            } else {
                for (Cargo cargo : info.getCargoes()) {
                    string.append(String.format("%s - %.2fкг\n", cargo.getType(), cargo.getWeight()));
                }
            }
            JOptionPane.showMessageDialog(this, string);
        });
    }

    private void loadBoxConfig() {
        loadBox.addItem("Загрузка");
        loadBox.addItem("Разгрузка");

        ship.getInfo().setStatus("Загрузка");

    }
}

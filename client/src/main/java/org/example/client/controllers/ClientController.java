package org.example.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.client.utils.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab MyParcelsTab;
    @FXML
    private TableColumn<PackageInfo, String> parcelNumberColumn;
    @FXML
    private TableColumn<PackageInfo, String> statusColumn;
    @FXML
    private TableColumn<PackageInfo, String> sendingDateColumn;
    @FXML
    private TableColumn<PackageInfo, String> estimatedDeliveryDateColumn;
    @FXML
    private TableColumn<PackageInfo, String> expressDeliveryColumn;
    @FXML
    private TableColumn<PackageInfo, String> fragileColumn;
    @FXML
    private TableColumn<PackageInfo, String> insuranceValueColumn;
    @FXML
    private TableColumn<PackageInfo, String> senderColumn;
    @FXML
    private TableColumn<PackageInfo, String> recipientColumn;
    @FXML
    private TableColumn<PackageInfo, String> weightColumn;
    @FXML
    private TableColumn<PackageInfo, String> dimensionsColumn;
    @FXML
    private TableColumn<PackageInfo, String> informationColumn;
    @FXML
    private TableView packagesTableView;
    @FXML
    private TableColumn<LetterInfo, String> parcelNumberColumnLetter;
    @FXML
    private TableColumn<LetterInfo, String> statusColumnLetter;
    @FXML
    private TableColumn<LetterInfo, String> sendingDateColumnLetter;
    @FXML
    private TableColumn<LetterInfo, String> estimatedDeliveryDateColumnLetter;
    @FXML
    private TableColumn<LetterInfo, String> formatColumLetter;
    @FXML
    private TableColumn<LetterInfo, String> priorityColumnLetter;
    @FXML
    private TableColumn<LetterInfo, String> senderColumnLetter;
    @FXML
    private TableColumn<LetterInfo, String> recipientColumnLetter;
    @FXML
    private TableView letterTableView;
    @FXML
    private TextField parcelIDTextInput;

    private ServerClient serverClient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serverClient = new ServerClient();
        serverClient.connect();
        setupTableColumns();
        initPackageData();
        initLetterData();
        listenForNotifications();
    }

    private void setupTableColumns() {
        parcelNumberColumn.setCellValueFactory(new PropertyValueFactory<>("parcelNumber"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        sendingDateColumn.setCellValueFactory(new PropertyValueFactory<>("sendingDate"));
        estimatedDeliveryDateColumn.setCellValueFactory(new PropertyValueFactory<>("estimatedDeliveryDate"));
        expressDeliveryColumn.setCellValueFactory(new PropertyValueFactory<>("expressDelivery"));
        fragileColumn.setCellValueFactory(new PropertyValueFactory<>("fragile"));
        insuranceValueColumn.setCellValueFactory(new PropertyValueFactory<>("insuranceValue"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        recipientColumn.setCellValueFactory(new PropertyValueFactory<>("recipient"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        dimensionsColumn.setCellValueFactory(new PropertyValueFactory<>("dimensions"));
        informationColumn.setCellValueFactory(new PropertyValueFactory<>("information"));

        parcelNumberColumnLetter.setCellValueFactory(new PropertyValueFactory<>("parcelNumber"));
        statusColumnLetter.setCellValueFactory(new PropertyValueFactory<>("status"));
        sendingDateColumnLetter.setCellValueFactory(new PropertyValueFactory<>("sendingDate"));
        estimatedDeliveryDateColumnLetter.setCellValueFactory(new PropertyValueFactory<>("estimatedDeliveryDate"));
        formatColumLetter.setCellValueFactory(new PropertyValueFactory<>("format"));
        priorityColumnLetter.setCellValueFactory(new PropertyValueFactory<>("priority"));
        senderColumnLetter.setCellValueFactory(new PropertyValueFactory<>("sender"));
        recipientColumnLetter.setCellValueFactory(new PropertyValueFactory<>("recipient"));
    }

    public void backToLogin(ActionEvent event) throws IOException {
        serverClient.disconnect();
        ViewSwitcher.switchScene(event, "login-view", "login-style",800, 500, this);
    }

    public void backToMainView(ActionEvent event) {
        tabPane.getSelectionModel().select(MyParcelsTab);
    }

    private void initPackageData() {
        String packageData = loadPackageDataFromServer();

        String[] lines = packageData.split("\\|");

        List<PackageInfo> packageInfoList = new ArrayList<>();

        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                String[] parts = line.split(",");

                if (parts.length >= 12) {
                    PackageInfo packageInfo = getPackageInfo(parts);
                    packageInfoList.add(packageInfo);
                }
            }
        }

        ObservableList<PackageInfo> packageInfoObservableList = FXCollections.observableArrayList(packageInfoList);
        packagesTableView.setItems(packageInfoObservableList);
    }

    private static PackageInfo getPackageInfo(String[] parts) {
        PackageInfo packageInfo = new PackageInfo();
        packageInfo.setParcelNumber(parts[0]);
        packageInfo.setStatus(parts[1]);
        packageInfo.setSendingDate(parts[2]);
        packageInfo.setEstimatedDeliveryDate(parts[3]);
        packageInfo.setExpressDelivery(parts[4].equals("1") ? "Yes" : "No");
        packageInfo.setFragile(parts[5].equals("1") ? "Yes" : "No");
        packageInfo.setInsuranceValue(parts[6]);
        packageInfo.setSender(parts[7]);
        packageInfo.setRecipient(parts[8]);
        packageInfo.setWeight(parts[9]);
        packageInfo.setDimensions(parts[10]);
        packageInfo.setInformation(parts[11]);
        return packageInfo;
    }


    private String loadPackageDataFromServer() {
        serverClient.sendCommand("PACKAGE_INFO:" + GlobalState.getUserId());
        return serverClient.receiveResponse();
    }

    private void initLetterData() {
        String letterData = loadLetterDataFromServer();

        if(letterData != null) {
            String[] lines = letterData.split("\\|");

            List<LetterInfo> letterInfoList = new ArrayList<>();

            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");

                    if (parts.length >= 8) {
                        LetterInfo letterInfo = getLetterInfo(parts);
                        letterInfoList.add(letterInfo);
                    }
                }
            }

            ObservableList<LetterInfo> letterInfoObservableList = FXCollections.observableArrayList(letterInfoList);
            letterTableView.setItems(letterInfoObservableList);
        }
    }

    private LetterInfo getLetterInfo(String[] parts) {
        LetterInfo letterInfo = new LetterInfo();
        letterInfo.setParcelNumber(parts[0]);
        letterInfo.setStatus(parts[1]);
        letterInfo.setSendingDate(parts[2]);
        letterInfo.setEstimatedDeliveryDate(parts[3]);
        letterInfo.setFormat(parts[4]);
        letterInfo.setPriority(parts[5].equals("1") ? "Yes" : "No");
        letterInfo.setSender(parts[6]);
        letterInfo.setRecipient(parts[7]);
        return letterInfo;
    }

    private String loadLetterDataFromServer() {
        serverClient.sendCommand("LETTER_INFO:" + GlobalState.getUserId());
        return serverClient.receiveResponse();
    }

    public void trackParcel(ActionEvent event) {
        String parcelId = parcelIDTextInput.getText();
        if (!parcelId.isEmpty()) {
            String command = "TRACK_PARCEL:" + parcelId;
            serverClient.sendCommand(command);
        }
    }

    private void listenForNotifications() {
        Thread listenerThread = new Thread(() -> {
                String notification;
                while ((notification = serverClient.receiveResponse()) != null) {
                    System.out.println("Otrzymano powiadomienie: " + notification);
                    updateUI(notification);
                }
        });
        listenerThread.start();
    }

    private void updateUI(String notification) {

    }

    public void changeParcelStatus(ActionEvent event) {
        System.out.println("Jestem klientem o ID: " + GlobalState.getUserId());
        String parcelId = parcelIDTextInput.getText();
        if (!parcelId.isEmpty()) {
            String command = "TEST_CHANGE_STATUS:" + parcelId +":Dostarczono";
            serverClient.sendCommand(command);
        }
    }
}

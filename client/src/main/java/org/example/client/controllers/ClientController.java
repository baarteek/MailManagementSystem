package org.example.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.client.utils.GlobalState;
import org.example.client.utils.PackageInfo;
import org.example.client.utils.ServerClient;
import org.example.client.utils.ViewSwitcher;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableColumns();
        initPackageData();
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
    }

    public void backToLogin(ActionEvent event) throws IOException {
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

                    packageInfoList.add(packageInfo);
                }
            }
        }

        ObservableList<PackageInfo> packageInfoObservableList = FXCollections.observableArrayList(packageInfoList);
        packagesTableView.setItems(packageInfoObservableList);
    }



    private String loadPackageDataFromServer() {
        String packageData = "";
        ServerClient serverClient = new ServerClient();
        try {
            serverClient.connect();
            String command = "PACKAGE_INFO:" + GlobalState.getUserId();
            serverClient.sendCommand(command);
            packageData =  serverClient.receiveResponse();
            System.out.println(packageData);
        } finally {
            serverClient.disconnect();
        }
        return packageData;
    }
}

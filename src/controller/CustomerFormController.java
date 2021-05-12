package controller;

import db.DataBase;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tm.CustomerTM;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {
    public AnchorPane root;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public TextField txtSearchHere;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOperate;

    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory("salary"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllCustomers(DataBase.customerDataTable); // Alt + Enter then Enter

        //--------------------

        tblCustomer.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setSelectedValue(newValue);
                    }
                });

        txtSearchHere.textProperty().
                addListener((observable, oldValue, newValue) -> {
                    search(newValue);
                });

        //----------------------
    }

    private void search(String searchText) {
        ArrayList<Customer> cList=new ArrayList<>();
        for (Customer c : DataBase.customerDataTable
        ) {
            if (c.getId().contains(searchText) ||
                    c.getName().contains(searchText) ||
                    c.getAddress().contains(searchText)
            ) {
                cList.add(c);
            }
        }

        loadAllCustomers(cList);
    }

    private void setSelectedValue(CustomerTM tm) {//
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        /*txtSalary.setText(tm.getSalary()+"");*/
        txtSalary.setText(String.valueOf(tm.getSalary()));
    }

    private void loadAllCustomers(ArrayList<Customer> list) {
        List<Customer> cList = list;
        ObservableList<CustomerTM> obList =
                FXCollections.observableArrayList();
        for (Customer c : cList
        ) {
            Button btn = new Button("Delete");
            CustomerTM tm = new CustomerTM(c.getId(), c.getName(),
                    c.getAddress(), c.getSalary(), btn);
            obList.add(tm);
            //---------------
            btn.setOnAction((e) -> { // Lamda Expression -->1.8 --> Functional Interface
                DataBase.customerDataTable.remove(c);
                loadAllCustomers(DataBase.customerDataTable);

               /* for (Customer temp:cList
                     ) {
                    if (temp.getId().equals(tm.getId())){
                        DataBase.customerDataTable.remove(temp);
                        loadAllCustomers();
                        return;
                    }
                }*/


            });
            //---------------

        }
        tblCustomer.setItems(obList);


    }


    public void btnBackToHome(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.
                load(this.getClass().
                        getResource("/view/DashBoardForm.fxml"))));
        stage.centerOnScreen();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        for (Customer customer : DataBase.customerDataTable
        ) {
            if (customer.getId().equals(txtId.getText())) {
                /*Customer temp= new Customer(
                        txtId.getText(),txtName.getText(),
                        txtAddress.getText(),
                        Double.parseDouble(txtSalary.getText())
                );
                DataBase.customerDataTable.remove(customer);
                DataBase.customerDataTable.add(temp);*/
                customer.setId(txtId.getText());
                customer.setName(txtName.getText());
                customer.setAddress(txtAddress.getText());
                customer.setSalary(Double.parseDouble(txtSalary.getText()));
                loadAllCustomers(DataBase.customerDataTable);
                return;
            }
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            double salary = Double.parseDouble(txtSalary.getText());
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.toString(),
                    ButtonType.OK).show();
            return;
        }

        Customer c1 = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );
        boolean isAdded = DataBase.customerDataTable.add(c1);
        if (isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved !!",
                    ButtonType.OK).show();
           // loadAllCustomers();// - refresh the table
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again !!",
                    ButtonType.OK).show();
        }

    }
}

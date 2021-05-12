package controller;

import db.DataBase;
import entity.Customer;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tm.ItemTM;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;

public class ItemFormController {
    public AnchorPane root;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;
    public TextField txtSearch;
    public TableView<ItemTM> tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQtyOnHand;
    public TableColumn colUnitPrice;
    public TableColumn colOperate;


    public void initialize() {

        colCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllItems();


        tblItem.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue!=null){
                        setSelectedValue(newValue);
                    }


                });
    }

    private void setSelectedValue(ItemTM tm) {
        txtCode.setText(tm.getCode());
        txtDescription.setText(tm.getDescription());
        txtQtyOnHand.setText(String.valueOf(tm.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(tm.getUnitPrice()));
    }

    private void loadAllItems() {
        List<Item> iList = DataBase.itemDataTable;
        ObservableList<ItemTM> obList = FXCollections.observableArrayList();

        for (Item i : iList
        ) {
            Button btn = new Button("Delete");
            ItemTM tm = new ItemTM(i.getCode(), i.getDescription(), i.getQtyOnHand(), i.getUnitPrice(), btn);
            obList.add(tm);

            btn.setOnAction((e) -> {
                boolean remove = DataBase.itemDataTable.remove(i);
                if (remove) {
                    loadAllItems();
                }
            });

        }

        tblItem.setItems(obList);

    }


    public void btnBackToHome(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.
                load(this.getClass().
                        getResource("/view/DashBoardForm.fxml"))));
        stage.centerOnScreen();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            int qty = Integer.parseInt(txtQtyOnHand.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING,
                    "Something went wrong !",
                    ButtonType.OK).show();
            return;
        }

        Item i1 = new Item(
                txtCode.getText(),
                txtDescription.getText(),
                Integer.parseInt(txtQtyOnHand.getText()),
                Double.parseDouble(txtUnitPrice.getText())
        );
        boolean isAdded = DataBase.itemDataTable.add(i1);
        if (isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved !!",
                    ButtonType.OK).show();
            loadAllItems();// - refresh the table
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again !!",
                    ButtonType.OK).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        for (Item item:DataBase.itemDataTable
             ) {
            if (txtCode.getText().equals(item.getCode())){
                item.setCode(txtCode.getText());
                item.setDescription(txtDescription.getText());
                item.setQtyOnHand(Integer.parseInt(txtQtyOnHand.getText()));
                item.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
                loadAllItems();
                return;
            }
        }
    }

}

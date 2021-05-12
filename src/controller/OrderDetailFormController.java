package controller;

import db.DataBase;
import entity.Customer;
import entity.ItemDetail;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tm.OrderTM;
import tm.PlacedItemTM;

public class OrderDetailFormController {
    public Label lblOrderTime;
    public Label lblCusId;
    public Label lblCusName;
    public Label lblCusAddress;
    public Label lblCusSalary;
    public TableView tbl;
    public TableColumn colItemId;
    public TableColumn colUnitPrice;
    public TableColumn colPlacedQTY;
    public Label lblOrdersDate;
    public Label lblOrderId;

    public void initialize(){
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colPlacedQTY.setCellValueFactory(new PropertyValueFactory<>("placedQty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }

    public  void setData(OrderTM tm){
       loadTopBarDetails(tm);
       loadBottomBarDetails(tm);
       loadDataTable(tm);
    }

    private void loadDataTable(OrderTM tm) {

        ObservableList<PlacedItemTM> obList =
                FXCollections.observableArrayList();

        for (Order o:DataBase.orderDataTable
             ) {
            if (o.getOrderId().equals(tm.getOrderId())){

                for (ItemDetail detail:o.getDetails()
                     ) {
                    obList.add(new PlacedItemTM(detail.getItemCode(),
                            detail.getUnitPrice(),detail.getQty()));
                }
            }
        }
        tbl.setItems(obList);

    }

    private void loadBottomBarDetails(OrderTM tm) {
        for (Customer c: DataBase.customerDataTable
             ) {
            if (c.getId().equals(tm.getCustomerId())){
                lblCusId.setText(c.getId());
                lblCusName.setText(c.getName());
                lblCusAddress.setText(c.getAddress());
                lblCusSalary.setText(String.valueOf(c.getSalary()));
                return;
            }
        }
    }

    private void loadTopBarDetails(OrderTM tm) {
        lblOrderId.setText(tm.getOrderId());
        lblOrdersDate.setText(tm.getOrderDate());
        lblOrderTime.setText(tm.getOrderTime());
    }

}

package controller;

import db.DataBase;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tm.OrderTM;

import java.io.IOException;

public class OrderFormController {
    public AnchorPane root;
    public TableView<OrderTM> tblOrder;
    public TableColumn colOrderI;
    public TableColumn colCustomerId;
    public TableColumn colOrderDate;
    public TableColumn colOrderTime;
    public TableColumn colOrderCost;

    public void initialize() {
        colOrderI.setCellValueFactory(new PropertyValueFactory("orderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory("customerId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory("orderDate"));
        colOrderTime.setCellValueFactory(new PropertyValueFactory("orderTime"));
        colOrderCost.setCellValueFactory(new PropertyValueFactory("totalCost"));

        loadAllOrders();


        tblOrder.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        loadNewWindow(newValue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

    }

    private void loadNewWindow(OrderTM tm) throws IOException {
        FXMLLoader loader = new
                FXMLLoader(getClass().
                getResource("/view/OrderDetailForm.fxml"));
        Parent parent = loader.load();
        OrderDetailFormController controller=loader.getController();
       controller.setData(tm);
       /* OrderDetailFormController.setData(tm);*/
        Stage stage= new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void loadAllOrders() {
        ObservableList<OrderTM> obList =
                FXCollections.observableArrayList();
        for (Order tempOrder : DataBase.orderDataTable
        ) {
            System.out.println(tempOrder.getOrderId());
            obList.add(new OrderTM(
                            tempOrder.getOrderId(),
                            tempOrder.getCusId(),
                            tempOrder.getOrderDate(),
                            tempOrder.getOrderTime(),
                            tempOrder.getTotalCost()
                    )
            );
        }
        tblOrder.setItems(obList);

    }


    public void btnBackToHome(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.
                load(this.getClass().
                        getResource("/view/DashBoardForm.fxml"))));
        stage.centerOnScreen();
    }
}

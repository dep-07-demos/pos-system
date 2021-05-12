package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {


    public AnchorPane root;

    public void imgOpenCustomerForm(MouseEvent mouseEvent) throws IOException {
        setUI("CustomerForm");
    }

    public void imgOpenItemForm(MouseEvent mouseEvent) throws IOException {
       setUI("ItemForm");
    }

    public void imgOpenPlaceOrderForm(MouseEvent mouseEvent) throws IOException {
       setUI("OrderForm");
    }

    public void imgOpenOrderForm(MouseEvent mouseEvent) throws IOException {
        setUI("PlaceOrderForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.
                load(this.getClass().
                        getResource("/view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

}

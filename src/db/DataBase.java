package db;

import entity.Customer;
import entity.Item;
import entity.Order;

import java.util.ArrayList;

public class DataBase {
    public static ArrayList<Customer> customerDataTable=new ArrayList();
    public static ArrayList<Item> itemDataTable=new ArrayList();
    public static ArrayList<Order> orderDataTable=new ArrayList();

    static {
        customerDataTable.add(new Customer("C001","Kamal","Colombo",2500));
        customerDataTable.add(new Customer("C002","Bandara","Panadura",1500));
        customerDataTable.add(new Customer("C003","Namal","Galle",2500));

        //------------------------------------
        itemDataTable.add(new Item("I001","Sample 1",25,45));
        itemDataTable.add( new Item("I002","Sample 2",30,15));
        itemDataTable.add(new Item("I003","Sample 3",82,20));

    }

}

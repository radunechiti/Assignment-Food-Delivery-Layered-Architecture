package Bll;

import Dao.OrderDAO;
import Models.Order;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class OrderValidators {

    public static Order findOrderById(int id_Order)
    {
        Order order =  OrderDAO.findOrderById(id_Order);
        if (order==null)
            throw new NoSuchElementException("Error");
        return order;
    }
    public static ArrayList<Order> findOrderByIdUser(int id_user)
    {
        return OrderDAO.findOrderByIdUser(id_user);
    }
    public static int insert(Order order)
    {
        return OrderDAO.insert(order);
    }
    public static void update(Order order)
    {
        OrderDAO.update(order);
    }
}

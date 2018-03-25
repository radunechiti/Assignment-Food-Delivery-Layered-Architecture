package Bll;

import Dao.OrderDAO;
import Models.Order;

import java.util.NoSuchElementException;

public class OrderValidators {

    public static Order findOrderById(int id_Order)
    {
        Order order =  OrderDAO.findOrderById(id_Order);
        if (order==null)
            throw new NoSuchElementException("Eror");
        return order;
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

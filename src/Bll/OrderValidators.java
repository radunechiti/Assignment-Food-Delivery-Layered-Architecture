package Bll;

import Dao.OrderDAO;
import Models.Order;
import Validators.AddressEmptyValidator;
import Validators.Validator;

import java.util.ArrayList;
import java.util.List;

public class OrderValidators {

    private List<Validator<Order>> validators;

    public OrderValidators() {
        this.validators =  new ArrayList<Validator<Order>>();
        validators.add(new AddressEmptyValidator());
    }

    public static Order findOrderById(int id_Order)
    {
        return OrderDAO.findOrderById(id_Order);
    }
    public static ArrayList<Order> findOrderByIdUser(int id_user)
    {
        return OrderDAO.findOrderByIdUser(id_user);
    }
    public int insert(Order order)
    {
        for(Validator<Order> v: validators)
            v.validate(order);
        return OrderDAO.insert(order);
    }/*
    public static void update(Order order)
    {
        OrderDAO.update(order);
    }*/
}

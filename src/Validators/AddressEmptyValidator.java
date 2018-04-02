package Validators;

import Models.Order;

public class AddressEmptyValidator implements Validator<Order>
{
    public void validate(Order order)
    {
        if(order.getAdress().equals(""))
            throw new IllegalArgumentException("You must enter an address!");
    }
}

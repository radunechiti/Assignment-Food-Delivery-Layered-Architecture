package Validators;

import javax.swing.JOptionPane;

import Models.Product;

public class QuantityValidator implements Validator<Product>
{
    private static final int MIN_Quantity = 0;
    private static final int MAX_Quantity = 100;
    public void validate(Product p)
    {
        try{
            if (p.getQuantity() < MIN_Quantity || p.getQuantity() > MAX_Quantity)
                throw new IllegalArgumentException("The quantity limit is not respected!");
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "The quantity limit is not respected!");
        }
    }
}

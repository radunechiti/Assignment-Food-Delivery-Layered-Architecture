package Validators;
import javax.swing.JOptionPane;

import Models.Product;

public class PriceValidator implements Validator<Product>
{
    private static final int MIN_Price = 1;
    private static final int MAX_Price = 10000;
    public void validate(Product p)
    {
        try{
            if (p.getPrice() < MIN_Price || p.getPrice() > MAX_Price)
                throw new IllegalArgumentException("The price limit is not respected!");
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "The price limit is not respected!");
        }
    }
}

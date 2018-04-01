package Validators;
import javax.swing.JOptionPane;

import Models.Product;

public class PriceValidator implements Validator<Product>
{
    private static final int MIN_Price = 1;
    private static final int MAX_Price = 10000;
    public void validate(Product product)
    {
        if (product.getPrice() < MIN_Price || product.getPrice() > MAX_Price)
            throw new IllegalArgumentException("The price limit is not respected!");
    }
}

package Bll;

import Dao.ProductDAO;
import Models.Product;
import Validators.PriceValidator;
import Validators.Validator;
import Validators.QuantityValidator;

import java.util.ArrayList;
import java.util.List;

public class ProductValidators {

    private List<Validator<Product>> validators;

    public ProductValidators() {
        this.validators =  new ArrayList<Validator<Product>>();
        validators.add(new QuantityValidator());
        validators.add(new PriceValidator());
    }

    public static Product findProduct(int id_product)
    {
        return ProductDAO.findProductById(id_product);
    }
    public void insertProduct(Product product)
    {
        for(Validator<Product> v: validators)
            v.validate(product);
        ProductDAO.insertProduct(product);
    }
    public void updateProduct(Product product)
    {
        for(Validator<Product> v: validators)
            v.validate(product);
        ProductDAO.updateProduct(product);
    }

    public void deleteProduct(Product product)
    {
        for(Validator<Product> v: validators)
            v.validate(product);
        ProductDAO.deleteProduct(product);
    }

    public static ArrayList<Product> getProducts()
    {
        return ProductDAO.getProducts();
    }

}

package Bll;

import Dao.CosDAO;
import Dao.ProductDAO;
import Models.Cos;
import Models.Product;

import javax.swing.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.NoSuchElementException;

public class CosValidators {

    public void insert(Cos cos)
    {
        Product product = ProductDAO.findProductById(cos.getId_product());
        int available = product.getQuantity();
        try{
            if(available < cos.getQuantity())
                throw new Exception("Error");
            CosDAO.insert(cos);
        }catch(Exception e)
        {
            //JOptionPane.shwoMessageDialog(null, "Cantitate insuficienta");
        }
    }

    public static ArrayList<Cos> showAll()
    {
        return CosDAO.getCosList();
    }
    public static ArrayList<Cos> findByCos(int id)
    {
        return CosDAO.findByOrder(id);
    }
}

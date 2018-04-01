package Bll;

import Dao.CosDAO;
import Dao.ProductDAO;
import Models.Cos;
import Models.Product;
import Validators.Validator;

import javax.swing.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.NoSuchElementException;

public class CosValidators {

    private List<Validator<Cos>> validators;

    public CosValidators() {
        this.validators =  new ArrayList<Validator<Cos>>();
    }

    public ArrayList<Cos> findByOrder(int id_Order)
    {
        return CosDAO.findByOrder(id_Order);
    }
    public int insert(Cos cos)
    {
        for(Validator<Cos> v: validators)
            v.validate(cos);
        return CosDAO.insert(cos);
    }

    public static ArrayList<Cos> getCosList()
    {
        return CosDAO.getCosList();
    }

}

package Bll;

import Dao.IstoricDAO;
import Models.Istoric;
import Validators.Validator;

import java.util.ArrayList;
import java.util.List;

public class IstoricValidators {

    private List<Validator<Istoric>> validators;

    public IstoricValidators() {
        this.validators =  new ArrayList<Validator<Istoric>>();
    }

    public static ArrayList<Istoric> getIstoricByIdUser(int id_user)
    {
        return IstoricDAO.getIstoricByIdUser(id_user);
    }
    public void insert(Istoric istoric)
    {
        for(Validator<Istoric> v: validators)
            v.validate(istoric);
        IstoricDAO.insert(istoric);
    }
}

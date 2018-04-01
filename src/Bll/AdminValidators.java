package Bll;

import Dao.AdminDAO;

import Models.Admin;

import Validators.Validator;

import java.util.ArrayList;
import java.util.List;

public class AdminValidators {

    private List<Validator<Admin>> validators;

    public AdminValidators() {
        this.validators =  new ArrayList<Validator<Admin>>();
        //validators.add(new EmailValidator());
    }

    public Admin find(Admin admin)
    {
        return AdminDAO.find(admin);
    }
}

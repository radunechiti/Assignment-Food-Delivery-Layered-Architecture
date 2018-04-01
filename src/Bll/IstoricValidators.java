package Bll;

import Dao.IstoricDAO;
import Models.Istoric;

import java.util.ArrayList;

public class IstoricValidators {

    public static ArrayList<Istoric> showByIdUser(int id_user)
    {
        return IstoricDAO.getIstoricByIdUser(id_user);
    }
    public void insert(Istoric istoric)
    {
        IstoricDAO.insert(istoric);
    }
}

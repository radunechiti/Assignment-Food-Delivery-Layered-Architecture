package Bll;
import java.util.ArrayList;
import java.util.List;

import Dao.UserDAO;
import Models.User;
import Validators.*;

public class UserValidators {

    private List<Validator<User>> validators;

    public UserValidators() {
        this.validators =  new ArrayList<Validator<User>>();
        validators.add(new EmailValidator());
        validators.add(new PasswordEmptyValidator());
        validators.add(new NumeEmptyValidator());
    }
    public User find(User user)
    {
        return UserDAO.findUser(user);
    }
    public void insert(User user)
    {
        for(Validator<User> v: validators)
            v.validate(user);
        UserDAO.insertUser(user);
    }
    public void update(User user)
    {
        for(Validator<User> v: validators)
            v.validate(user);
        UserDAO.updateUser(user);
    }
    public void updateUserDetails(User user)
    {
        for(Validator<User> v: validators)
            v.validate(user);
        UserDAO.updateUserDetails(user);
    }

    public void delete(User user)
    {
        for(Validator<User> v: validators)
            v.validate(user);
        UserDAO.deleteUser(user);
    }

    public static ArrayList<User> getUsers()
    {
        return UserDAO.getUsers();
    }
}

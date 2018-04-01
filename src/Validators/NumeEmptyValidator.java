package Validators;

import Models.User;

public class NumeEmptyValidator implements Validator<User>
{
    public void validate(User user)
    {
        if (user.getNume().equals(""))
            throw new IllegalArgumentException("Please insert a name!");
    }
}

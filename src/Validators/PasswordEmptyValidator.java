package Validators;

import Models.User;

public class PasswordEmptyValidator implements Validator<User>
{
    public void validate(User user)
    {
        if (user.getPassword().equals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"))
            throw new IllegalArgumentException("Please insert a password!");
    }
}

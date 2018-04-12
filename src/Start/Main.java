package Start;

import Connection.ConnectionFactory;
import Logic.MainController;
import View.*;

public class Main {

    public static void main(String[] args)
    {

        LoginView loginView = new LoginView();
        AdminView adminView =  new AdminView();
        CreateUserView createUserView = new CreateUserView();
        UserView userView = new UserView();
        IstoricView istoricView = new IstoricView();
        UpdateUserView updateUserView = new UpdateUserView();
        MainController mainController = new MainController(loginView,adminView,createUserView,userView, istoricView, updateUserView);
        ConnectionFactory c = new ConnectionFactory();
    }
}

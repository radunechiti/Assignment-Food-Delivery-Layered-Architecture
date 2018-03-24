package Start;

import Logic.Controller;
import View.AdminView;
import View.CreateUserView;
import View.Menu;
import View.UserView;

public class Main {

    public static void main(String[] args)
    {

        Menu menu = new Menu();
        AdminView adminView =  new AdminView();
        CreateUserView createUserView = new CreateUserView();
        UserView userView = new UserView();
        Controller controller = new Controller(menu,adminView,createUserView,userView);
    }
}

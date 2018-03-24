package Logic;

import View.AdminView;
import View.Menu;
import View.CreateUserView;
import View.UserView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {
    private Menu menu;
    private AdminView adminView;
    private CreateUserView createUserView;
    private UserView userView;

    public Controller(Menu menu, AdminView adminView, CreateUserView createUserView, UserView userView) {
        this.menu = menu;
        this.adminView = adminView;
        this.createUserView = createUserView;
        this.userView = userView;
        this.menu.setVisible(true);

        this.menu.ButtonSignIn(new SignIn_Listener());
        this.menu.ButtonSignUp(new SignUp_Listener());
    }

    public class SignUp_Listener implements ActionListener //gata
    {
        public void actionPerformed(ActionEvent e)
        {
            createUserView.setVisible(true);
        }
    }
    public class SignIn_Listener implements ActionListener //gata
    {
        public void actionPerformed(ActionEvent e)
        {
            //adminView.setVisible(true);
            userView.setVisible(true);
        }
    }

}

package Logic;

import Bll.ProductValidators;
import Bll.UserValidators;
import Models.Product;
import Models.User;
import View.AdminView;
import View.Menu;
import View.CreateUserView;
import View.UserView;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
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

        this.createUserView.ButtonCreateNewAccount(new CreateNewAccount_Listener());
        this.adminView.ClickUser(new ClickUser_Listener());
        this.adminView.ClickProduct(new ClickProduct_Listener());
        this.adminView.ButtonAddUser(new ButtonAddUser_Listener());
        this.adminView.ButtonUpdateUser(new ButtonUpdateUser_Listener());
        this.adminView.ButtonDeleteUser(new ButtonDeleteUser_Listener());
        this.adminView.ButtonAddProduct(new ButtonAddProduct_Listener());
        this.adminView.ButtonUpdateProduct(new ButtonUpdateProduct_Listener());
        this.adminView.ButtonDeleteProduct(new ButtonDeleteProduct_Listener());

    }

    public class CreateNewAccount_Listener implements ActionListener ///////gata
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                String email = createUserView.jTextField1.getText();
                String pass = createUserView.jTextField1.getText();
                String nume = createUserView.jTextField1.getText();
                boolean active = true;
                boolean loyal = false;

                User user = new User(pass, email, nume, active, loyal);
                UserValidators userValidators = new UserValidators();
                userValidators.insert(user);
                adminView.showUser();
                JOptionPane.showMessageDialog(null, "Felicitari! Ati creat un cont nou.");
                createUserView.dispose();
            }catch(NumberFormatException exp){
                JOptionPane.showMessageDialog(null, "Introduceti date valide");
            }
        }
    }
    public class SignUp_Listener implements ActionListener    ////gata
    {
        public void actionPerformed(ActionEvent e)
        {
            createUserView.setVisible(true);
        }
    }
    public class SignIn_Listener implements ActionListener  //gataaaaaaaaaaaaaaaa
    {
        public void actionPerformed(ActionEvent e)
        {
            adminView.setVisible(true);
            adminView.showUser();
            adminView.showProduct();
        }
    }
    public class ClickUser_Listener implements MouseListener   //gataaaaaaaaaaaaa
    {
        public void mouseClicked(java.awt.event.MouseEvent e)
        {
            adminView.showClickUser();
        }
        public void mouseEntered(java.awt.event.MouseEvent e) {}	 //cod inutil dar necesar pentru a functiona mouseListener
        public void mouseExited(java.awt.event.MouseEvent e) {}
        public void mousePressed(java.awt.event.MouseEvent e) {}
        public void mouseReleased(java.awt.event.MouseEvent e) {}
    }
    public class ClickProduct_Listener implements MouseListener   //gataaaaaaaaaa
    {
        public void mouseClicked(java.awt.event.MouseEvent e)
        {
            adminView.showClickProduct();
        }
        public void mouseEntered(java.awt.event.MouseEvent e) {}	 //cod inutil dar necesar pentru a functiona mouseListener
        public void mouseExited(java.awt.event.MouseEvent e) {}
        public void mousePressed(java.awt.event.MouseEvent e) {}
        public void mouseReleased(java.awt.event.MouseEvent e) {}
    }
    public class ButtonAddUser_Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

        }
    }
    public class ButtonUpdateUser_Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                int i = adminView.jTable1.getSelectedRow();
                TableModel model = adminView.jTable1.getModel();
                int id = (Integer)model.getValueAt(i,0);
                String email = adminView.jTextField1.getText();
                String pass = adminView.jTextField2.getText();
                String nume = adminView.jTextField3.getText();
                boolean active = true;
                boolean loyal;
                if(adminView.jComboBox2.getSelectedItem().equals("Yes"))
                    loyal = true;
                else
                    loyal = false;


                User user = new User(id, pass, email, nume, active, loyal);
                UserValidators userValidators = new UserValidators();

                userValidators.update(user);
                adminView.showUser();
            }catch(NumberFormatException exp){
                JOptionPane.showMessageDialog(null, "Introduceti date valide");
            }
        }
    }
    public class ButtonDeleteUser_Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                int i = adminView.jTable1.getSelectedRow();
                TableModel model = adminView.jTable1.getModel();
                int id = (Integer)model.getValueAt(i,0);
                String email = adminView.jTextField1.getText();
                String pass = adminView.jTextField2.getText();
                String nume = adminView.jTextField3.getText();
                boolean loyal;
                if(adminView.jComboBox2.getSelectedItem().equals("Yes"))
                    loyal = true;
                else
                    loyal = false;
                User user = new User(id,pass,email,nume,false,loyal);
                UserValidators userValidators = new UserValidators();

                userValidators.delete(user);
                adminView.showUser();
            }catch(NumberFormatException exp){
                JOptionPane.showMessageDialog(null, "Introduceti date valide");
            }
        }
    }
    public class ButtonAddProduct_Listener implements ActionListener    ///gata
    {
        public void actionPerformed(ActionEvent e)
        {
            try{
                String name = adminView.jTextField5.getText();
                int pret = Integer.parseInt(adminView.jTextField6.getText());
                int quantity = Integer.parseInt(adminView.jTextField7.getText());

                Product product = new Product(name, pret, quantity);
                ProductValidators productValidators = new ProductValidators();

                productValidators.insertProduct(product);
                adminView.showProduct();

            }catch(NumberFormatException exp){
                JOptionPane.showMessageDialog(null, "Introduceti date valide");
            }
        }
    }
    public class ButtonUpdateProduct_Listener implements ActionListener   ////gata
    {
        public void actionPerformed(ActionEvent e)
        {
            try{
                int i = adminView.jTable2.getSelectedRow();
                TableModel model = adminView.jTable2.getModel();
                int id = (Integer)model.getValueAt(i,0);
                String name = adminView.jTextField5.getText();
                int pret = Integer.parseInt(adminView.jTextField6.getText());
                int quantity = Integer.parseInt(adminView.jTextField7.getText());

                Product product = new Product(id, name, pret, quantity);
                ProductValidators productValidators = new ProductValidators();

                productValidators.updateProduct(product);
                adminView.showProduct();

            }catch(NumberFormatException exp){
                JOptionPane.showMessageDialog(null, "Introduceti date valide");
            }
        }
    }
    public class ButtonDeleteProduct_Listener implements ActionListener    /////gata
    {
        public void actionPerformed(ActionEvent e)
        {
            try{
                int i = adminView.jTable2.getSelectedRow();
                TableModel model = adminView.jTable2.getModel();
                int id = (Integer)model.getValueAt(i,0);

                Product product = new Product(id);
                ProductValidators productValidators = new ProductValidators();

                productValidators.deleteProduct(product);
                adminView.showProduct();

            }catch(NumberFormatException exp){
                JOptionPane.showMessageDialog(null, "Introduceti date valide");
            }
        }
    }

}

package Logic;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import Bll.*;
import Models.*;
import View.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;
import static java.security.SecureRandom.getInstance;


public class MainController {
    private LoginView loginView;
    private AdminView adminView;
    private CreateUserView createUserView;
    private UserView userView;
    private IstoricView istoricView;

    public MainController(LoginView loginView, AdminView adminView, CreateUserView createUserView, UserView userView, IstoricView istoricView) {
        this.loginView = loginView;
        this.adminView = adminView;
        this.createUserView = createUserView;
        this.userView = userView;
        this.istoricView = istoricView;
        this.loginView.setVisible(true);

        this.loginView.ButtonSignIn(new SignIn_Listener());
        this.loginView.ButtonSignUp(new SignUp_Listener());

        this.createUserView.ButtonCreateNewAccount(new CreateNewAccount_Listener());
        this.adminView.ClickUser(new ClickUser_Listener());
        this.adminView.ClickProduct(new ClickProduct_Listener());
        this.adminView.ButtonAddUser(new ButtonAddUser_Listener());
        this.adminView.ButtonUpdateUser(new ButtonUpdateUser_Listener());
        this.adminView.ButtonDeleteUser(new ButtonDeleteUser_Listener());
        this.adminView.ButtonAddProduct(new ButtonAddProduct_Listener());
        this.adminView.ButtonUpdateProduct(new ButtonUpdateProduct_Listener());
        this.adminView.ButtonDeleteProduct(new ButtonDeleteProduct_Listener());
        this.userView.ButtonAddComanda(new ButtonAddComanda_Listener());
        this.userView.ButtonFinishComanda(new ButtonFinishComanda_Listener());
        this.userView.ButtonIstoric(new ButtonIstoric_Listener());
        this.userView.ClickProducts(new ClickProducts_Listener());
        this.adminView.ButtonReport(new ButtonReport_Listener());


    }
    private static String securePassword(String password) throws NoSuchAlgorithmException
    {
        StringBuffer sb =  new StringBuffer();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            //StringBuffer sb =  new StringBuffer();
            for(int i=0; i<hash.length; i++)
            {
                String s = Integer.toHexString(0xff & hash[i]);
                if(s.length()==1)
                    sb.append('0');
                sb.append(s);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    public class ButtonReport_Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                int i = adminView.jTable1.getSelectedRow();
                TableModel model = adminView.jTable1.getModel();
                String email = (String)model.getValueAt(i,1);
                String password = (String)model.getValueAt(i,1);
                UserValidators userValidators = new UserValidators();
                User user = new User(email, password);
                User logat = userValidators.find(user);
                try{
                    PrintStream fout = new PrintStream(new FileOutputStream("Report.txt"));
                    fout.println("User details");
                    fout.println("Name: " + logat.getNume());
                    fout.println("Email: " + logat.getEmail());
                    fout.println("Loyal: " + logat.getLoyal());
                    fout.println("Activ: " + logat.getActive());

                    OrderValidators orderValidators = new OrderValidators();
                    ArrayList <Order> list = orderValidators.findOrderByIdUser(logat.getId());
                    for(Order o: list)
                    {
                        fout.println();
                        fout.println("Comanda nr: " + o.getId());
                        fout.println("Adresa: " + o.getAdress());
                        fout.println("Payment: " + o.getPayment());
                        fout.println("Total: " + o.getTotal());
                        fout.println("Date: " + o.getDate());
                    }
                }catch(FileNotFoundException e1){
                    JOptionPane.showMessageDialog(null, "Eroare");
                }
        }
    }
    public class CreateNewAccount_Listener implements ActionListener  //gata----------------------------------
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                String email = createUserView.jTextField1.getText();
                String pass = createUserView.jTextField2.getText();
                String nume = createUserView.jTextField3.getText();
                User user = new User(securePassword(pass), email, nume, true, false);
                UserValidators userValidators = new UserValidators();
                userValidators.insert(user);
                adminView.showUser();
                JOptionPane.showMessageDialog(null, "Felicitari! Ati creat un cont nou.");
                createUserView.dispose();
            }catch(IllegalArgumentException exp){
                JOptionPane.showMessageDialog(null, exp.getMessage());
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
        }
    }
    public class SignUp_Listener implements ActionListener            //gata----------------------------------
    {
        public void actionPerformed(ActionEvent e)
        {
            createUserView.setVisible(true);
        }
    }
    public class SignIn_Listener implements ActionListener            //gata
    {
        public void actionPerformed(ActionEvent e)
        {

            try {
                String email = loginView.jTextField1.getText().toString();
                String password = loginView.jPasswordField1.getText().toString();
                System.out.println(email);
                System.out.println(password);
                String encryptedPassword = securePassword(password);
                System.out.println(encryptedPassword);
                UserValidators userValidators = new UserValidators();
                User user = userValidators.find(new User(email, encryptedPassword));

                if (user == null) {
                    AdminValidators adminValidators = new AdminValidators();
                    Admin admin = adminValidators.find(new Admin(email, encryptedPassword));
                    if (admin != null) {
                        adminView.showUser();
                        adminView.showProduct();
                        adminView.setVisible(true);
                    }
                    else
                        throw new IllegalArgumentException("Introducti date valide!");
                }
                else
                {
                    userView.setVisible(true);
                    ArrayList<Product> list = ProductValidators.getProducts();
                    userView.showProduct(list);
                    userView.jTextField1.setText(String.valueOf(user.getId()));
                    userView.jTextField2.setText(user.getNume());
                }
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
            catch(NumberFormatException exp){
                JOptionPane.showMessageDialog(null, exp.getMessage());
            }
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
                String pass = securePassword(adminView.jTextField2.getText());
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
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
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
                int pret = parseInt(adminView.jTextField6.getText());
                int quantity = parseInt(adminView.jTextField7.getText());

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
                int pret = parseInt(adminView.jTextField6.getText());
                int quantity = parseInt(adminView.jTextField7.getText());

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
    public class ButtonAddComanda_Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try{
                int id = parseInt(userView.jTextField4.getText());
                ProductValidators productValidators = new ProductValidators();
                Product product= productValidators.findProduct(id);
                product.setQuantity(parseInt(userView.jTextField7.getText()));
                ArrayList<Product> list = ProductValidators.getProducts();//update product
                for(Product p: list)
                    if(p.getId()==product.getId())
                        p.setQuantity(p.getQuantity()-product.getQuantity());
                userView.showProduct(list);
                userView.showCos(product);
                userView.setTotal(product.getPrice()*product.getQuantity());

            }catch(NumberFormatException exp){
                JOptionPane.showMessageDialog(null, "Introduceti date valide");
            }
        }
    }
    public class ButtonFinishComanda_Listener implements ActionListener    /////gata
    {
        public void actionPerformed(ActionEvent e)
        {
            try{  //create order
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                String payment;
                if(userView.jComboBox1.getSelectedItem().equals("Cash"))
                    payment = "Cash";
                else
                    payment = "Card";
                Order order = new Order(userView.jTextField6.getText().toString(), parseInt(userView.jTextField3.getText().toString()),parseInt(userView.jTextField1.getText()), sqlDate, payment);
                OrderValidators orderValidators = new OrderValidators();
                int id_order = orderValidators.insert(order);
                //create cos
                TableModel model3 = userView.jTable2.getModel();
                int id_product, quantity;
                String name;
                CosValidators cosValidators = new CosValidators();
                ProductValidators productValidators = new ProductValidators();
                for(int i=0; i<model3.getRowCount(); i++)
                {
                    id_product = (Integer) model3.getValueAt(i, 0);
                    name = (String) model3.getValueAt(i, 1);
                    quantity = (Integer) model3.getValueAt(i, 3);

                    Product product = productValidators.findProduct(id_product);
                    product.setQuantity(product.getQuantity()-quantity);
                    cosValidators.insert(new Cos(id_product, id_order, quantity));
                    productValidators.updateProduct(product);
                }
                Istoric istoric = new Istoric(parseInt(userView.jTextField3.getText().toString()), id_order);
                System.out.println(istoric.getId_User());
                System.out.println(istoric.getId_Order());
                IstoricValidators istoricValidators = new IstoricValidators();
                istoricValidators.insert(istoric);
                ArrayList<Istoric> list = istoricValidators.getIstoricByIdUser(1); //////trebuie sa schimb userul
                istoricView.showIstoric(list);
                adminView.showProduct();

            }catch(NumberFormatException exp){
                JOptionPane.showMessageDialog(null, "Introduceti date valide");
            }
        }
    }
    public class ButtonIstoric_Listener implements ActionListener    /////gata
    {
        public void actionPerformed(ActionEvent e)
        {
            istoricView.setVisible(true);
            int id_user = 1;
            IstoricValidators istoricValidators = new IstoricValidators();
            ArrayList<Istoric> list = istoricValidators.getIstoricByIdUser(id_user);  //trebuie sa schimb userul
            istoricView.showIstoric(list);
        }
    }

    public class ClickProducts_Listener implements MouseListener   //gataaaaaaaaaa
    {
        public void mouseClicked(java.awt.event.MouseEvent e)
        {
            userView.showClickProduct();
        }
        public void mouseEntered(java.awt.event.MouseEvent e) {}	 //cod inutil dar necesar pentru a functiona mouseListener
        public void mouseExited(java.awt.event.MouseEvent e) {}
        public void mousePressed(java.awt.event.MouseEvent e) {}
        public void mouseReleased(java.awt.event.MouseEvent e) {}
    }

}

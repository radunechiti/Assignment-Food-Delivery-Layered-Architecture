package Models;

import java.util.Date;

public class Order {

    private int id;
    private String adress;
    private String payment;
    private int total;
    private int id_user;
    private java.sql.Date date;

    public Order(String adress, int total, int id_user, java.sql.Date data, String payment) {
        this.adress = adress;
        this.total = total;
        this.id_user = id_user;
        java.sql.Date date = data;
        this.payment = payment;
    }

    public Order(int id, String adress, String payment, int total, int id_user, java.sql.Date data) {
        this.id = id;
        this.adress = adress;
        this.payment = payment;
        this.total = total;
        this.id_user = id_user;
        java.sql.Date date = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}


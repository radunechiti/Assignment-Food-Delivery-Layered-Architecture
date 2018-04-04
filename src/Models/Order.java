package Models;

import java.sql.Timestamp;

public class Order {

    private int id;
    private String adress;
    private String payment;
    private int total;
    private int id_user;
    private Timestamp time;

    public Order(String adress, int total, int id_user, Timestamp time, String payment) {
        this.adress = adress;
        this.total = total;
        this.id_user = id_user;
        this.time = time;
        this.payment = payment;
    }

    public Order(int id, String adress, String payment, int total, int id_user, Timestamp time) {
        this.id = id;
        this.adress = adress;
        this.payment = payment;
        this.total = total;
        this.id_user = id_user;
        this.time = time;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}


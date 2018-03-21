package Models;

public class Order {

    private int id;
    private String adress;
    private int total;
    private int id_user;

    public Order(int id, String adress, int total, int id_user) {
        this.id = id;
        this.adress = adress;
        this.total = total;
        this.id_user = id_user;
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
}


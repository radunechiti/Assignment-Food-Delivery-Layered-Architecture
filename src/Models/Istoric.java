package Models;

public class Istoric {

    private int id;
    private int id_User;
    private int id_Order;

    public Istoric(int id, int id_User, int id_Order) {
        this.id = id;
        this.id_User = id_User;
        this.id_Order = id_Order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    public int getId_Order() {
        return id_Order;
    }

    public void setId_Order(int id_Order) {
        this.id_Order = id_Order;
    }
}

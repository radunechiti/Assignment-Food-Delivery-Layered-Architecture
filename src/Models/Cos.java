package Models;

public class Cos {

    private int id;
    private int total;
    private int id_client;

    public Cos(int id, int total, int id_client) {
        this.id = id;
        this.total = total;
        this.id_client = id_client;
    }

    public Cos(int total, int id_client) {
        this.total = total;
        this.id_client = id_client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
}

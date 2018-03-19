package Models;

public class Order {

    private int id;
    private int quantity;
    private int id_cos;
    private int id_product;

    public Order(int id, int quantity, int id_cos, int id_product) {
        this.id = id;
        this.quantity = quantity;
        this.id_cos = id_cos;
        this.id_product = id_product;
    }

    public Order(int quantity, int id_cos, int id_product) {
        this.quantity = quantity;
        this.id_cos = id_cos;
        this.id_product = id_product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId_Cos() {
        return id_cos;
    }

    public void setId_Cos(int id_Cos) {
        this.id_cos = id_Cos;
    }

    public int getId_Product() {
        return id_product;
    }

    public void setId_Product(int id_Product) {
        this.id_product = id_Product;
    }
}

package Models;

public class User {

    private int id;
    private String password;
    private String email;
    private String nume;
    private boolean flag;

    public User(int id, String password, String email, String nume, boolean flag) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.nume = nume;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}

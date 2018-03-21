package Models;

public class User {

    private int id;
    private String pasword;
    private String email;
    private boolean flag;

    public User(int id, String pasword, String email, boolean flag) {
        this.id = id;
        this.pasword = pasword;
        this.email = email;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

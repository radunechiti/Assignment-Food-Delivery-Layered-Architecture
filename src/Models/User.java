package Models;

public class User {

    private String username;
    private String pasword;
    private String email;
    private boolean flag;

    public User(String username, String pasword, String email, boolean flag) {
        this.username = username;
        this.pasword = pasword;
        this.email = email;
        this.flag = flag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

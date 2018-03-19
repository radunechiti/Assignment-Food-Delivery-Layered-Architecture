package Models;

public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private boolean flag;

    public User(int id, String name, String surname, String email, boolean flag)
    {
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.flag=flag;
    }

    public User(String name, String surname, String email, boolean flag)
    {
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.flag=flag;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

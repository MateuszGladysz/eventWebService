package application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userAccount")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String email;


    @NotNull
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    public UserProfile userProf;

    public UserAccount() {
    }

    public UserAccount(long id) {
        this.id = id;
    }

    public UserAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserProfile getUserProf() {
        return userProf;
    }

    public void setUserProf(UserProfile userProf) {
        this.userProf = userProf;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

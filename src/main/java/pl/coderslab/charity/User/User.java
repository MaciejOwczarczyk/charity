package pl.coderslab.charity.User;

import pl.coderslab.charity.Authorities.Authorities;
import pl.coderslab.charity.Insitution.Institution;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @NotBlank
    @Column(nullable = false, name = "first_name")
    private String firstName;

    @NotBlank
    @Column(nullable = false, name = "last_name")
    private String lastName;


    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public List<Authorities> getAuthorities() {
//        return authorities;
//    }

//    public void setAuthorities(List<Authorities> authorities) {
//        this.authorities = authorities;
//    }
}

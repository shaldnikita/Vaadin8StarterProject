package ru.cwt.Monitoring.backend.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author n.shaldenkov on 19.11.2017
 */

@Entity
public class User extends AbstractEntity{

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String role;

    private boolean locked = false;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /*public UserConfig getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }*/
}

package io.kodikuldeep.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user_profile")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;

    public AppUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public AppUser() {
    }

}

package ru.job4j.automarket.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class User.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 13.01.2021
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public static User of(String name, String email, String phone, String password, Role role) {
        User user = new User();
        user.name = name;
        user.email = email;
        user.phone = phone;
        user.password = password;
        user.role = role;
        return user;
    }
}

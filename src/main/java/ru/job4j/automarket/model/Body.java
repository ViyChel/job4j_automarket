package ru.job4j.automarket.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class Body.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 25.01.2022
 */
@Getter
@Setter
@Entity
@Table(name = "body")
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public static Body of() {
        return new Body();
    }
}

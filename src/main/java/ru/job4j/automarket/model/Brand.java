package ru.job4j.automarket.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Class Brand.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 16.10.2021
 */
@Entity
@Data
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}

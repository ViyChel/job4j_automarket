package ru.job4j.automarket.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class Photo.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 14.01.2021
 */
@Getter
@Setter
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public static Photo of(String name) {
        final Photo photo = new Photo();
        photo.name = name;
        return photo;
    }
}

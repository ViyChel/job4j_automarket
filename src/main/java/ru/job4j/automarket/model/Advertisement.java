package ru.job4j.automarket.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class Advertisement.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 13.01.2021
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "advertisements")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private boolean status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();


    public static Advertisement of(String description) {
        Advertisement advertisement = new Advertisement();
        advertisement.description = description;
        advertisement.setDate(new Date());
        return advertisement;
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);
    }
}

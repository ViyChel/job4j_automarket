package ru.job4j.automarket.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
@Getter
@Setter
@Entity
@Table(name = "advertisements")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "price")
    private String price;

    @Column(name = "city")
    private String city;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    private boolean status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    /**
     * Of advertisement.
     *
     * @return the advertisement
     */
    public static Advertisement of() {
        Advertisement advertisement = new Advertisement();
        advertisement.setDate(new Date());
        return advertisement;
    }

    /**
     * Add photo.
     *
     * @param photo the photo
     */
    public void addPhoto(Photo photo) {
        this.photos.add(photo);
    }

    /**
     * Date format string.
     *
     * @param date the date
     * @return the string
     */
    public String dateFormat(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return localDate.format(formatter);
    }
}

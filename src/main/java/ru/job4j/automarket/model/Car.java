package ru.job4j.automarket.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class Car.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 13.01.2021
 */
@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "model")
    private String model;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @Column(name = "transmission")
    private String transmission;

    @ManyToOne
    @JoinColumn(name = "body_id")
    private Body body;

    @Column(name = "color")
    private String color;

    @Column(name = "mileage")
    private String mileage;

    @Column(name = "year")
    private String year;

    @Column(name = "gear")
    private String gear;

    public static Car of() {
        Car car = new Car();
        car.setBrand(Brand.of());
        car.setBody(Body.of());
        car.setEngine(Engine.of());
        return car;
    }
}

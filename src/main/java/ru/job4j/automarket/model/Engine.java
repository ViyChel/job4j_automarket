package ru.job4j.automarket.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class Engine.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 06.12.2021
 */
@Getter
@Setter
@Entity
@Table(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private final String name = "engine";

    @Column(name = "engine_type")
    private String engineType;

    @Column(name = "engine_power")
    private String enginePower;

    @Column(name = "engine_volume")
    private String engineVolume;

    public static Engine of() {
        return new Engine();
    }
}

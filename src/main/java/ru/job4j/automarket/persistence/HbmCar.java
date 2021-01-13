package ru.job4j.automarket.persistence;

import lombok.extern.slf4j.Slf4j;
import ru.job4j.automarket.model.Car;

import java.util.List;

/**
 * Class HbmCar.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 14.01.2021
 */
@Slf4j
public class HbmCar implements Store<Car> {
    private static final HbmCar STORE = new HbmCar();

    public static HbmCar getStore() {
        return STORE;
    }

    @Override
    public Car add(Car model) {
        this.tx(session -> session.save(model));
        return model;
    }

    @Override
    public boolean replace(int id, Car model) {
        return this.tx(session -> {
            session.update(String.valueOf(id), model);
            return true;
        });
    }

    @Override
    public boolean delete(int id) {
        return this.tx(session -> {
            Car model = session.get(Car.class, id);
            session.remove(model);
            return true;
        });
    }

    @Override
    public List<Car> findAll() {
        return this.tx(session -> session.createQuery("from Car", Car.class).list());
    }

    @Override
    public List<Car> findByName(String name) {
        return this.tx(session -> session.createQuery("from Car where model = :model", Car.class)
                .setParameter("model", name).list()
        );
    }

    @Override
    public Car findById(int id) {
        return this.tx(session -> session.get(Car.class, id));
    }
}

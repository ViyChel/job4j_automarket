package ru.job4j.automarket.persistence;


import ru.job4j.automarket.model.Brand;

import java.util.List;

/**
 * Class HbmBrand.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 16.10.2021
 */
public class HbmBrand implements Store<Brand> {
    private static final HbmBrand STORE = new HbmBrand();

    public static HbmBrand getStore() {
        return STORE;
    }


    @Override
    public Brand add(Brand model) {
        this.tx(session -> session.save(model));
        return model;
    }

    @Override
    public boolean replace(int id, Brand model) {
        return this.tx(session -> {
            session.update(String.valueOf(id), model);
            return true;
        });
    }

    @Override
    public boolean delete(int id) {
        return this.tx(session -> {
            Brand model = session.get(Brand.class, id);
            session.remove(model);
            return true;
        });
    }

    @Override
    public List<Brand> findAll() {
        return this.tx(session -> session.createQuery("from Brand ", Brand.class).list());
    }

    @Override
    public List<Brand> findByName(String name) {
        return this.tx(session -> session.createQuery("from Brand where name = :name", Brand.class)
                .setParameter("name", name).list());
    }

    @Override
    public Brand findById(int id) {
        return this.tx(session -> session.get(Brand.class, id));
    }
}

package ru.job4j.automarket.persistence;

import ru.job4j.automarket.model.Body;

import java.util.List;

/**
 * Class HbmBody.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 18.02.2022
 */
public class HbmBody implements Store<Body> {
    private static final HbmBody STORE = new HbmBody();

    public static HbmBody getStore() {
        return STORE;
    }

    @Override
    public Body add(Body model) {
        this.tx(session -> session.save(model));
        return model;
    }

    @Override
    public boolean replace(int id, Body model) {
        return this.tx(session -> {
            session.update(String.valueOf(id), model);
            return true;
        });
    }

    @Override
    public boolean delete(int id) {
        return this.tx(session -> {
            Body model = session.get(Body.class, id);
            session.remove(model);
            return true;
        });
    }

    @Override
    public List<Body> findAll() {
        return this.tx(session -> session.createQuery("from Body ", Body.class).list());
    }

    @Override
    public List<Body> findByName(String name) {
        return this.tx(session -> session.createQuery("from Body where name = :name", Body.class)
                .setParameter("name", name).list());
    }

    @Override
    public Body findById(int id) {
        return this.tx(session -> session.get(Body.class, id));
    }
}

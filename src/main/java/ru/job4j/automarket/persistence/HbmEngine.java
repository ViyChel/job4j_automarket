package ru.job4j.automarket.persistence;

import ru.job4j.automarket.model.Engine;

import java.util.List;

/**
 * Class HbmEngine.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 29.01.2022
 */
public class HbmEngine implements Store<Engine> {
    private static final HbmEngine STORE = new HbmEngine();

    public static HbmEngine getStore() {
        return STORE;
    }

    @Override
    public Engine add(Engine model) {
        this.tx(session -> session.save(model));
        return model;
    }

    @Override
    public boolean replace(int id, Engine model) {
        return this.tx(session -> {
            session.update(String.valueOf(id), model);
            return true;
        });
    }

    @Override
    public boolean delete(int id) {
        return this.tx(session -> {
            Engine model = session.get(Engine.class, id);
            session.remove(model);
            return true;
        });
    }

    @Override
    public List<Engine> findAll() {
        return this.tx(session -> session.createQuery("from Engine ", Engine.class).list());
    }

    @Override
    public List<Engine> findByName(String name) {
        return this.tx(session -> session.createQuery("from Engine where name = :name", Engine.class)
                .setParameter("name", name).list());
    }

    @Override
    public Engine findById(int id) {
        return this.tx(session -> session.get(Engine.class, id));
    }
}

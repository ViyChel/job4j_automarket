package ru.job4j.automarket.persistence;

import ru.job4j.automarket.model.Photo;

import java.util.List;

/**
 * Class HbmPhoto.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 14.01.2021
 */
public class HbmPhoto implements Store<Photo> {
    private static final HbmPhoto STORE = new HbmPhoto();

    public static HbmPhoto getStore() {
        return STORE;
    }

    @Override
    public Photo add(Photo model) {
        this.tx(session -> session.save(model));
        return model;
    }

    @Override
    public boolean replace(int id, Photo model) {
        return this.tx(session -> {
            session.update(String.valueOf(id), model);
            return true;
        });
    }

    @Override
    public boolean delete(int id) {
        return this.tx(session -> {
            Photo model = session.get(Photo.class, id);
            session.remove(model);
            return true;
        });
    }

    @Override
    public List<Photo> findAll() {
        return this.tx(session -> session.createQuery("from Photo ", Photo.class).list());
    }

    @Override
    public List<Photo> findByName(String name) {
        return this.tx(session -> session.createQuery("from Photo where name = :name", Photo.class)
                .setParameter("name", name).list());
    }

    @Override
    public Photo findById(int id) {
        return this.tx(session -> session.get(Photo.class, id));
    }
}

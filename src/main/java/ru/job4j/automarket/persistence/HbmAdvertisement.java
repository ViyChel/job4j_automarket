package ru.job4j.automarket.persistence;

import ru.job4j.automarket.model.Advertisement;

import java.util.List;

/**
 * Class HbmAdvertisement.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 14.01.2021
 */
public class HbmAdvertisement implements Store<Advertisement> {
    private static final HbmAdvertisement STORE = new HbmAdvertisement();

    public static HbmAdvertisement getStore() {
        return STORE;
    }

    @Override
    public Advertisement add(Advertisement model) {
        this.tx(session -> session.save(model));
        return model;
    }

    @Override
    public boolean replace(int id, Advertisement model) {
        return this.tx(session -> {
            session.update(String.valueOf(id), model);
            return true;
        });
    }

    @Override
    public boolean delete(int id) {
        return this.tx(session -> {
            Advertisement model = session.get(Advertisement.class, id);
            session.remove(model);
            return true;
        });
    }

    @Override
    public List<Advertisement> findAll() {
        return this.tx(session -> session.createQuery("from Advertisement ", Advertisement.class).list());
    }

    @Override
    public List<Advertisement> findByName(String name) {
        return this.tx(session -> session.createQuery("from Advertisement where description = :description", Advertisement.class)
                .setParameter("description", name).list()
        );
    }

    @Override
    public Advertisement findById(int id) {
        return this.tx(session -> session.get(Advertisement.class, id));
    }
}

package ru.job4j.automarket.persistence;

import ru.job4j.automarket.model.User;

import java.util.List;

/**
 * Class HbmUser.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 14.01.2021
 */
public class HbmUser implements Store<User> {
    private static final HbmUser STORE = new HbmUser();

    public static HbmUser getStore() {
        return STORE;
    }

    @Override
    public User add(User model) {
        this.tx(session -> session.save(model));
        return model;
    }

    @Override
    public boolean replace(int id, User model) {
        return this.tx(session -> {
            session.update(String.valueOf(id), model);
            return true;
        });
    }

    @Override
    public boolean delete(int id) {
        return this.tx(session -> {
            User model = session.get(User.class, id);
            session.remove(model);
            return true;
        });
    }

    @Override
    public List<User> findAll() {
        return this.tx(session -> session.createQuery("from User", User.class).list());
    }

    @Override
    public List<User> findByName(String name) {
        return this.tx(session -> session.createQuery("from User where name = :name", User.class)
                .setParameter("name", name).list());
    }

    public User findByEmail(String email) {
        return this.tx(session -> {
            User user = session.createQuery("from User where email = :email", User.class)
                    .setParameter("email", email).uniqueResult();
            return user != null ? user : new User();
        });
    }

    @Override
    public User findById(int id) {
        return this.tx(session -> session.get(User.class, id));
    }
}

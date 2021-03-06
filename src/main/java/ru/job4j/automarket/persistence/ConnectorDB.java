package ru.job4j.automarket.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Class ConnectorDB.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 14.01.2021
 */
public class ConnectorDB {

    private ConnectorDB() {
    }

    public static SessionFactory getInstance() {
        return Holder.SESSION_FACTORY;
    }

    private static final class Holder {
        private final static StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder()
                .configure().build();
        private final static SessionFactory SESSION_FACTORY = new MetadataSources(REGISTRY)
                .buildMetadata().buildSessionFactory();
    }
}
import edu.jpa_study.entities.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.*;

import org.junit.jupiter.api.Assertions;

public class HelloWorldJPATest {

    @Test
    // используем EntityManagerFactory и persistence.xml
    public void storeLoadMessage1() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("HelloHibernatePU");
        try {
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            Message message = new Message();
            message.setText("Hello World");
            em.persist(message);

            transaction.commit();


            transaction = em.getTransaction();
            transaction.begin();

            List<Message> messages = em.createQuery("SELECT m FROM Message m", Message.class).getResultList();
            messages.get(messages.size() - 1).setText("Hello Hibernate!");

            transaction.commit();

            Assertions.assertAll(
                    () -> Assertions.assertEquals(1, messages.size()),
                    () -> Assertions.assertEquals("Hello Hibernate!", messages.get(0).getText())
            );

            em.close();
        } finally {
            emf.close();
        }
    }

    @Test
    // Используем Sessionfactory и hibernate.cfg.xml
    void storeLoadMessage2() {
        try (SessionFactory sessionFactory = createSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Message message = new Message();
            message.setText("Hello World");

            session.persist(message);
            session.getTransaction().commit();


            session.beginTransaction();

            CriteriaQuery<Message> criteriaQuery = session.getCriteriaBuilder().createQuery(Message.class);
            criteriaQuery.from(Message.class);
            List<Message> messages = session.createQuery(criteriaQuery).getResultList();

            messages.get(messages.size() - 1).setText("Hello Hibernate!");

            session.persist(message);
            session.getTransaction().commit();

            Assertions.assertAll(
                    () -> Assertions.assertEquals(1, messages.size()),
                    () -> Assertions.assertEquals("Hello Hibernate!", messages.get(0).getText())
            );
        }
    }

    private static SessionFactory createSessionFactory() {
        Configuration cfg = new Configuration();
        cfg.configure().addAnnotatedClass(Message.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(cfg.getProperties()).build();
        return cfg.buildSessionFactory(serviceRegistry);
    }
}

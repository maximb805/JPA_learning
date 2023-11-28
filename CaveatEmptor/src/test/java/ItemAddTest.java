import edu.jpa_study.model.AuctionType;
import edu.jpa_study.model.Bid;
import edu.jpa_study.model.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.*;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ItemAddTest {
    @Test
    public void addItemTest() {

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("CaveatEmptorPU");

//        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
//        Validator v = vf.getValidator();

        {
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Calendar c = new GregorianCalendar();
            c.set(2024, Calendar.JANUARY, 3);

            Bid bid11 = new Bid();
            bid11.setAmount(new BigDecimal(21000));
            bid11.setCreatedOn(new Date());

            Bid bid12 = new Bid();
            bid12.setAmount(new BigDecimal(22000));
            bid12.setCreatedOn(new Date());

            Bid bid13 = new Bid();
            bid13.setAmount(new BigDecimal(23000));
            bid13.setCreatedOn(new Date());

            Bid bid14 = new Bid();
            bid14.setAmount(new BigDecimal(24000));
            bid14.setCreatedOn(new Date());

            Bid bid21 = new Bid();
            bid21.setAmount(new BigDecimal(2000));
            bid21.setCreatedOn(new Date());

            Bid bid22 = new Bid();
            bid22.setAmount(new BigDecimal(2300));
            bid22.setCreatedOn(new Date());

            Item item1 = new Item("Bicycle", 8.5, "Downhill professional bicycle",
                    new Date(), AuctionType.HIGHEST_BID, true, new BigDecimal(20000),
                    new Date(), c.getTime());
            Item item2 = new Item("Scateboard", 3.6, "Just not bad scateboard",
                    new Date(), AuctionType.HIGHEST_BID, true, new BigDecimal(1500),
                    new Date(), c.getTime());
            item1.addBid(bid11);
            item1.addBid(bid12);
            item1.addBid(bid13);
            item1.addBid(bid14);

            item2.addBid(bid21);
            item2.addBid(bid22);

//            Set<ConstraintViolation<Item>> violations = v.validate(item);
//            ConstraintViolation<Item> violation = violations.iterator().next();
//            String failedProperty = violation.getPropertyPath().iterator().next().getName();
//            System.out.println(failedProperty);

            em.persist(item1);
            em.persist(item2);
            for (Bid b : item1.getBids()) {
                em.persist(b);
            }
            for (Bid b : item2.getBids()) {
                em.persist(b);
            }
            transaction.commit();
            em.close();
        }
        {
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            CriteriaQuery<Item> criteriaQuery = em.getCriteriaBuilder().createQuery(Item.class);
            criteriaQuery.from(Item.class);
            List<Item> itemList = em.createQuery(criteriaQuery).getResultList();
            Item item1 = itemList.get(0);
            Item item2 = itemList.get(1);

//            item1.getBids().forEach(System.out::println);
//            item2.getBids().forEach(System.out::println);

            System.out.println(item1);
            System.out.println(item2);

//            System.out.println(item1.getBidsCount());
//            System.out.println(item2.getBidsCount());


            transaction.commit();
            em.close();
        }
        emf.close();
    }
}

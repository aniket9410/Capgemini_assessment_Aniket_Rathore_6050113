package cg.demo.association.test1;

import jakarta.persistence.*;
import java.util.*;

public class OrderDaoImpl implements OrderDao {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("JPAHibernate");

    EntityManager em = emf.createEntityManager();

    
    @Override
    public void addOrder(Order order) {

        em.getTransaction().begin();

        Customer c = em.find(Customer.class,
                order.getCustomer().getCustomerId());

        order.setCustomer(c);

        em.persist(order);

        em.getTransaction().commit();
    }

    @Override
    public Order viewOrderById(int orderId) {

        return em.find(Order.class, orderId);
    }

    @Override
    public List<Order> viewOrdersByCustomerName(String name) {

        String jpql =
        "select o from Order o where o.customer.customerName = :name";

        TypedQuery<Order> query =
        em.createQuery(jpql, Order.class);

        query.setParameter("name", name);

        return query.getResultList();
    }
}
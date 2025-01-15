import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainCreateOrder {
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {			
            // start a transaction
            session.beginTransaction();

            // create a product
            Product product = new Product("Switch");

            // create the orders
            Date currentDate = new Date();
            Order order1 = new Order(currentDate, "Patrick Start");
            Order order2 = new Order(currentDate, "Spongebob Squarepants");

            // add orders to product
            product.addOrder(order1);
            product.addOrder(order2);

            // save the orders
            session.save(order1);
            session.save(order2);
            System.out.println("Saved orders: " + product.getOrders());

            // save the product (this will also update the join table)
            session.save(product);

            // commit transaction
            session.getTransaction().commit();
        }

        finally {

            // add clean up code
            session.close();
            factory.close();
        }
    }
}

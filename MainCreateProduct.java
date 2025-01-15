import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainCreateProduct {
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
                
            // create an order
            Date currentDate = new Date();
            Order order = new Order(currentDate, "James Earl");

            // create the products
            Product product1 = new Product("iPhone");
            Product product2 = new Product("Nike Backpack");
                
            // add products to the order
            order.addProduct(product1);
            order.addProduct(product2);

            // save the products
            session.save(product1);
            session.save(product2);
            System.out.println("Saved products: " + order.getProducts());

            // save the order (this will also update the join table)
            session.save(order);

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

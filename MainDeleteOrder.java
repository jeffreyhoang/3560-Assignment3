import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainDeleteOrder {
    public static void main(String[] args) {
        // create session factory
			SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Order.class)
            .addAnnotatedClass(Product.class)
            .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the order from database
            int id = 1;
            Order order = session.get(Order.class, id);

            // delete the order and other related products from the join table but not the products table
            session.delete(order);

            // commit transaction
            session.getTransaction().commit();
				
            System.out.println("Done!");
        }
        finally {
            // add clean up code
            session.close();
            factory.close();
        }
    }
}

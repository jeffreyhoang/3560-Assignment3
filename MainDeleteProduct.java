import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainDeleteProduct {
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

            // get the product from database
            int id = 10;
            Product product = session.get(Product.class, id);

            // delete the product and other related order from the join table but not the orders table
            session.delete(product);

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

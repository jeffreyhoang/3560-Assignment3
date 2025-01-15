package hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainCreate {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().
					                 configure("hibernate.cfg.xml").
                                     addAnnotatedClass(Professor.class).
					                 addAnnotatedClass(Customer.class).
					                 buildSessionFactory();
			
        Session session = factory.getCurrentSession();
        
        try {
            // create objects
            Professor professor = new Professor("Building 8 Room 356", "Artificial Intelligence");
            Customer customer = new Customer("Abby Wong", "123 Thorton Av. Irvine, CA");
            
            // associate the objects
            professor.setCustomer(customer);

            // start transaction
            session.beginTransaction();
            
            session.save(professor);
            
            // commit transaction to database
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            factory.close();
        }

        System.out.println("Finished!");
    }	
}

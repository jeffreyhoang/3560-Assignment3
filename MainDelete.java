package hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainDelete {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().
					                 configure("hibernate.cfg.xml").
                                     addAnnotatedClass(Professor.class).
					                 addAnnotatedClass(Customer.class).
					                 buildSessionFactory();
			
        Session session = factory.getCurrentSession();
        
        try {
            
            // begin session
            session.beginTransaction();

            // retrive the professor
            int id = 1;
            Professor professor = session.get(Professor.class, id);

            // delete professor AND will also delete associated customer because of CascadeType.ALL
            session.delete(professor);

            // commit to database
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            factory.close();
        }

        System.out.println("Finished!");
    }	
}

package team6.DAO;

import team6.entities.Route;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RouteDAO {
    private final EntityManager em;

    public RouteDAO(EntityManager em) {
        this.em = em;
    }



    public void save(Route route) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(route);
        transaction.commit();

        System.out.println("Complimenti " + route.getRouteName()+ " è stato creato con successo!");
    }

    public Route findById(int id) {
        return em.find(Route.class, id);
    }

    public void findAndDeleteById(int id) {
        Route route = this.findById(id);

        if (route != null) {
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            em.remove(route);
            transaction.commit();

            System.out.println("Complimenti hai eliminato con successo la tratta " + route.getRouteName() );
        } else {
            System.out.println("Ops! non ho trovato nessuna tratta tramite il codice ID che mi hai fornito");
        }
    }
}

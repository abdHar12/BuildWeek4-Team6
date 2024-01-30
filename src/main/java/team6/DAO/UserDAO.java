package team6.DAO;

import team6.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDAO {

    private final EntityManager em;


    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User user) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(user);
        transaction.commit();

        System.out.println("Complimenti " + user.getName() + " " + user.getSurname() + " è stato creato con successo!");
    }

    public User findById(long id) {

        return em.find(User.class, id);

    }

    public void findAndDeleteById(long id) {

        User user = this.findById(id);

        if (user != null) {

            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            em.remove(user);
            transaction.commit();

            System.out.println("Complimenti hai eliminato con successo l'utente " + user.getName() + " " + user.getSurname());

        } else {
            System.out.println("Ops! non ho trovato nessun utente tramite il codice ID che mi hai fornito ");
        }
    }


}

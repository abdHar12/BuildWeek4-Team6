package team6.DAO;

import team6.abstractclasses.Bookable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BookableDAO {

    private final EntityManager em;


    public BookableDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Bookable ele) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(ele);
        transaction.commit();

        System.out.println("Complimenti il biglietto / abbonamento " + ele.getId() + " è stato creato con successo!");
    }

    public Bookable findById(long id) {

        return em.find(Bookable.class, id);

    }

    public void findAndDeleteById(long id) {

        Bookable ele = this.findById(id);

        if (ele != null) {

            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            em.remove(ele);
            transaction.commit();

            System.out.println("Complimenti hai eliminato con successo il biglietto / abbonamento con id  " + ele.getId());

        } else {
            System.out.println("Ops! non ho trovato nessun biglietto / abbonamento tramite il codice ID che mi hai fornito ");
        }
    }


    //VIDIMAZIONE DEI BIGLIETTI PER UN VEICOLO

    public void validationTicket (){



    }
}

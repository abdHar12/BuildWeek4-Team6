package team6.DAO;

import team6.abstractclasses.Bookable;
import team6.entities.Ticket;
import team6.expetions.ElementsNotFound;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

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


    public List<Ticket> getAllTickets() throws ElementsNotFound {
        TypedQuery<Ticket> getAllTickets=em.createNamedQuery("getAllTickets", Ticket.class);
        List<Ticket> elements= getAllTickets.getResultList();
        if(elements.isEmpty()) throw new ElementsNotFound();
        return elements;
    }

    public Ticket getByIndex(List<Ticket> list,int index){

     return list.get(index);
    }


    //VIDIMAZIONE DEI BIGLIETTI PER UN VEICOLO

    public void validationTicket (){



    }
}

package team6.DAO;

import team6.abstractclasses.Sellers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SellersDAO {

    private final EntityManager em;


    public SellersDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Sellers seller) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(seller);
        transaction.commit();

        System.out.println("Complimenti il venditore con id " + seller.getId() +" è stato creato con successo!");
    }


    public Sellers findById(long id) {

    return em.find(Sellers.class, id);

}

public void findAndDeleteById(long id) {

    Sellers seller = this.findById(id);

    if (seller != null) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.remove(seller);
        transaction.commit();

        System.out.println("Complimenti hai eliminato con successo il venditore con id  " + seller.getId() + " a " +seller.getPlace());

    } else {
        System.out.println("Ops! non ho trovato nessun venditore tramite il codice ID che mi hai fornito ");
    }
}

}

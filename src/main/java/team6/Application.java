package team6;

import team6.DAO.BookableDAO;
import team6.DAO.SellersDAO;
import team6.DAO.UserDAO;
import team6.abstractclasses.Bookable;
import team6.abstractclasses.Sellers;
import team6.entities.LicensedSeller;
import team6.entities.Subscription;
import team6.entities.User;
import team6.entities.VendingMachine;
import team6.enums.ActiveEnum;
import team6.enums.SubDuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4bw");
    public static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        BookableDAO bookableDAO = new BookableDAO(em);
        UserDAO userDAO = new UserDAO(em);
        SellersDAO sellersDAO = new SellersDAO(em);

        System.out.println("creazione utente e bookable");

        User userProva = new User("Cosmin", "Siffredi", LocalDate.now());

        Sellers sellerProva = new LicensedSeller("Roma", "Tabacchino di Ruane");

        Sellers sellerDue = new VendingMachine("Trento", ActiveEnum.ACTIVE);

        Bookable subProva = new Subscription(80.50, LocalDate.now(), sellerProva, SubDuration.MONTHLY, userProva);



        userDAO.save(userProva);

        sellersDAO.save(sellerProva);
        sellersDAO.save(sellerDue);
        bookableDAO.save(subProva);

        System.out.println("Hello World!");
    }
}

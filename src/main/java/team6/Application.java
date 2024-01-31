package team6;

import team6.DAO.*;
import team6.abstractclasses.Bookable;
import team6.abstractclasses.Sellers;
import team6.entities.*;
import team6.enums.ActiveEnum;
import team6.enums.SubDuration;
import team6.enums.VehicleType;

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
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        MaintenanceDAO maintenanceDAO = new MaintenanceDAO(em);

        System.out.println("creazione utente e bookable");

        User userProva = new User("Cosmin", "Siffredi", LocalDate.now());

        Sellers sellerProva = new LicensedSeller("Roma", "Tabacchino di Ruane");

        Sellers sellerDue = new VendingMachine("Trento", ActiveEnum.ACTIVE);

        Bookable subProva = new Subscription(80.50, LocalDate.now(), sellerProva, SubDuration.MONTHLY, userProva);

        Vehicle vehicleProva = new Vehicle(VehicleType.BUS);



        Maintenance maintenanceProvaDue = new Maintenance("cambio gomme due", LocalDate.now().minusDays(5), vehicleProva);
        userDAO.save(userProva);
        vehicleDAO.save(vehicleProva);
        sellersDAO.save(sellerProva);
        sellersDAO.save(sellerDue);
        bookableDAO.save(subProva);


        maintenanceDAO.save(maintenanceProvaDue);
        maintenanceDAO.setDateOfEndMaintenance(maintenanceProvaDue, LocalDate.now());
        Maintenance maintenanceProvaTre = new Maintenance("cambio gomme tre", LocalDate.now().minusDays(3), vehicleProva);
        maintenanceDAO.save(maintenanceProvaTre);

//        maintenanceDAO.save(maintenanceProvaTre);

//        maintenanceDAO.setDateOfEndMaintenance(maintenanceProvaDue, LocalDate.now());
       // maintenanceDAO.getMaintencanceList(vehicleProva);
        System.out.println(maintenanceDAO.getMaintencanceList(vehicleProva));
        System.out.println("Hello World!");
    }
}

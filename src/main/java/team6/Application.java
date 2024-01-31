package team6;

import com.github.javafaker.Faker;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4bw");
    public static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        //TUTTI I DAO
        BookableDAO bookableDAO = new BookableDAO(em);
        UserDAO userDAO = new UserDAO(em);
        SellersDAO sellersDAO = new SellersDAO(em);
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        MaintenanceDAO maintenanceDAO = new MaintenanceDAO(em);
        RouteDAO routeDAO = new RouteDAO(em);

        //SETTO IL FAKER
        Faker faker = new Faker(Locale.ITALY);


        //CREO SELLER
        int nSeller = 3;

        for (int i = 1; i <= nSeller; i++) {
            Sellers licensedSeller = new LicensedSeller(faker.address().city(), faker.harryPotter().spell() + " shop");
            sellersDAO.save(licensedSeller);

            Sellers machine = new VendingMachine(faker.address().city(), ActiveEnum.ACTIVE);
            sellersDAO.save(machine);
        }

        //CREO USER CON LA SUA SUB
        int nUser = 20;

        for (int i = 1; i <= nUser; i++) {
            User user = new User(faker.name().firstName(), faker.name().lastName(), LocalDate.of(faker.number().numberBetween(2021, 2024), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 30)));
            userDAO.save(user);

            Random random = new Random();
            int numRandom = random.nextInt( 2)+1;


            Bookable sub = new Subscription(LocalDate.of(faker.number().numberBetween(2021, 2024), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 30)), sellersDAO.findById(faker.number().numberBetween(1, nSeller * 2)), numRandom == 1 ? SubDuration.WEEKLY : SubDuration.MONTHLY, user);
            bookableDAO.save(sub);
        }

//        //CREO SUBSCRIPTION
//        for (int i = 1; i < nUser; i++) {
//
//        }

        System.out.println("creazione utente e bookable");

//        User userProva = new User("Cosmin", "Siffredi", LocalDate.now());
//        userDAO.save(userProva);
//
//        Sellers sellerProva = new LicensedSeller("Roma", "Tabacchino di Ruane");
//        sellersDAO.save(sellerProva);
//
//        Sellers sellerDue = new VendingMachine("Trento", ActiveEnum.ACTIVE);
//        sellersDAO.save(sellerDue);
//
//        Bookable subProva = new Subscription(LocalDate.now(), sellerProva, SubDuration.MONTHLY, userProva);
//        bookableDAO.save(subProva);

        Vehicle vehicleProva = new Vehicle(VehicleType.BUS);
        vehicleDAO.save(vehicleProva);

        Vehicle vehicleDue = new Vehicle(VehicleType.TRAM);
        vehicleDAO.save(vehicleDue);

        Route routeProva = new Route("Marano Vincentino", "Bassano del Grappa", 25);
        routeDAO.save(routeProva);


        //creo 3 tickets
//        Ticket tck1 = new Ticket(30.20, LocalDate.now(), sellerProva);
//        Ticket tck2 = new Ticket(10.20, LocalDate.now(), sellerDue);
//        Ticket tck3 = new Ticket(20.20, LocalDate.now(), sellerProva);
//        bookableDAO.save(tck1);
//        bookableDAO.save(tck3);
//        bookableDAO.save(tck2);


        Maintenance maintenanceProvaDue = new Maintenance("cambio gomme due", LocalDate.now().minusDays(5), vehicleProva);
        maintenanceDAO.save(maintenanceProvaDue);

        maintenanceDAO.setDateOfEndMaintenance(maintenanceProvaDue, LocalDate.now());

        Maintenance maintenanceProvaTre = new Maintenance("cambio gomme tre", LocalDate.now().minusDays(3), vehicleProva);
        maintenanceDAO.save(maintenanceProvaTre);
        routeProva.setVehicleList(new ArrayList<>(Arrays.asList(vehicleProva)));

        routeDAO.save(routeProva);
        System.out.println("hellooooooo");


//        vehicleProva.setTickets(new ArrayList<>(Arrays.asList(tck1, tck2)), LocalDate.now());
//        vehicleDue.setTickets(new ArrayList<>(Arrays.asList(tck3)), LocalDate.now());
//
//        bookableDAO.save(tck1);
//        bookableDAO.save(tck2);
//        bookableDAO.save(tck3);
//        vehicleDAO.save(vehicleProva);
//        vehicleDAO.save(vehicleDue);

        System.out.println(maintenanceDAO.getMaintencanceList(vehicleProva));
        System.out.println("Hello World!");
    }
}

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
import java.util.*;

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
            int numRandom = random.nextInt(2) + 1;


            Bookable sub = new Subscription(LocalDate.of(faker.number().numberBetween(2021, 2024), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 30)), sellersDAO.findById(faker.number().numberBetween(1, nSeller * 2)), numRandom == 1 ? SubDuration.WEEKLY : SubDuration.MONTHLY, user);
            bookableDAO.save(sub);
        }


        //CREO TICKETS

        int nTickets = 50;

        List<Sellers> allSellers = sellersDAO.getAllSellers();

        for (int i = 0; i <= nTickets; i++) {

            Random random = new Random();


            Bookable ticket = new Ticket(faker.number().randomDouble(2, 3, 12), LocalDate.of(faker.number().numberBetween(2021, 2024), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 30)), allSellers.get(random.nextInt(0, allSellers.size())));
            bookableDAO.save(ticket);
        }

        //CREO VEICOLI

        int nVehicles = 40;

        for (int i = 0; i < nVehicles; i++) {

            Vehicle vehicleTram = new Vehicle(VehicleType.TRAM);
            Vehicle vehicleBus = new Vehicle(VehicleType.BUS);
            vehicleDAO.save(vehicleTram);
            vehicleDAO.save(vehicleBus);
            System.out.println("ciaooooooooooooooooooooooooooooooooooo");
        }


        //CREO LE ROTTE

        int nRoutes = nVehicles ;

        for (int i = 0; i < nRoutes; i++) {

            Random random = new Random();

            List<Vehicle> vehicleList = vehicleDAO.getAllVehicles();

            Route route = new Route(faker.address().city(), faker.address().city(), random.nextInt(10, 300));
            route.setVehicleList(new ArrayList<>(Arrays.asList(vehicleList.get(i), vehicleList.get(i + nRoutes))));
            routeDAO.save(route);
        }


//        Maintenance maintenanceProvaDue = new Maintenance("cambio gomme due", LocalDate.now().minusDays(5), vehicleProva);
//        maintenanceDAO.save(maintenanceProvaDue);
//
//        maintenanceDAO.setDateOfEndMaintenance(maintenanceProvaDue, LocalDate.now());
//
//        Maintenance maintenanceProvaTre = new Maintenance("cambio gomme tre", LocalDate.now().minusDays(3), vehicleProva);
//        maintenanceDAO.save(maintenanceProvaTre);
//
//
//        vehicleProva.setTickets(new ArrayList<>(Arrays.asList(tck1, tck2)), LocalDate.now());
//        vehicleDue.setTickets(new ArrayList<>(Arrays.asList(tck3)), LocalDate.now());



        System.out.println("Hello World!");
    }
}

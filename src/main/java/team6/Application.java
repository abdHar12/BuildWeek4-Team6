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

        //SETTO IL FAKER -----------------------------------------------------------------------------------------------
        Faker faker = new Faker(Locale.ITALY);

        //CREO SELLER --------------------------------------------------------------------------------------------------
        int nSeller = 3;

        for (int i = 1; i <= nSeller; i++) {
            Sellers licensedSeller = new LicensedSeller(faker.address().city(), faker.harryPotter().spell() + " shop");
            sellersDAO.save(licensedSeller);

            Sellers machine = new VendingMachine(faker.address().city(), ActiveEnum.ACTIVE);
            sellersDAO.save(machine);
        }

        //CREO USER CON LA SUA SUB -------------------------------------------------------------------------------------
        int nUser = 20;

        for (int i = 1; i <= nUser; i++) {
            User user = new User(faker.name().firstName(), faker.name().lastName(), LocalDate.of(faker.number().numberBetween(2022, 2024), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 30)));
            userDAO.save(user);

            Random random = new Random();
            int numRandom = random.nextInt(2) + 1;

            //se la card dell'user è scaduta arriverà un mess di errore in console, possiamo risettare la scadenza con un set

            try {
                Bookable sub = new Subscription(LocalDate.of(faker.number().numberBetween(2024, 2024), faker.number().numberBetween(1, 2), faker.number().numberBetween(1, 30)), sellersDAO.findById(faker.number().numberBetween(1, nSeller * 2)), numRandom == 1 ? SubDuration.WEEKLY : SubDuration.MONTHLY, user);
                bookableDAO.save(sub);
            }catch (UnsupportedOperationException e){
                System.err.println("La card per " + user.getName() + " " + user.getSurname() +" con id " +user.getCardNumber() + " è scaduta, rinnovala per poter attivare una subscription");

            }

        }


        //CREO TICKETS -------------------------------------------------------------------------------------------------

        int nTickets = 50;

        List<Sellers> allSellers = sellersDAO.getAllSellers();

        for (int i = 0; i <= nTickets; i++) {

            Random random = new Random();


            Bookable ticket = new Ticket(faker.number().randomDouble(2, 3, 12), LocalDate.of(faker.number().numberBetween(2021, 2024), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 30)), allSellers.get(random.nextInt(0, allSellers.size())));
            bookableDAO.save(ticket);
        }

        //CREO VEICOLI -------------------------------------------------------------------------------------------------

        int nVehicles = 40;

        for (int i = 0; i < nVehicles; i++) {

            Vehicle vehicleTram = new Vehicle(VehicleType.TRAM);
            Vehicle vehicleBus = new Vehicle(VehicleType.BUS);
            vehicleDAO.save(vehicleTram);
            vehicleDAO.save(vehicleBus);
        }


        //CREO LE ROTTE ------------------------------------------------------------------------------------------------

        int nRoutes = nVehicles;

        for (int i = 0; i < nRoutes; i++) {

            Random random = new Random();

            List<Vehicle> vehicleList = vehicleDAO.getAllVehicles();

            Route route = new Route(faker.address().city(), faker.address().city(), random.nextInt(10, 300));
            route.setVehicleList(new ArrayList<>(Arrays.asList(vehicleList.get(i), vehicleList.get(i + nRoutes))));
            routeDAO.save(route);
        }


        //SETTO DEI TICKET CHE VIDIMO SU UN DETERMIANTO MEZZO ----------------------------------------------------------

        //lista di tickets
        List<Ticket> ticketsList = bookableDAO.getAllTickets();

        //veicolo sul quale vogliamo vidimare i tickets tramite id
        Vehicle vehicleForTest = vehicleDAO.findById(3);

        //creo lista di biglietti da vidimare tramite indice nella lista presa in precedenza
        List<Ticket> ticketUsing = new ArrayList<>(Arrays.asList(ticketsList.get(1), ticketsList.get(2), ticketsList.get(3)));

        //CONTROLLO PER VEDERE DI NON POTER VIDIMARE UN TICKET PIU DI UNA VOLTA
        //List<Ticket> ticketUsingDue = new ArrayList<>(Arrays.asList(ticketsList.get(3), ticketsList.get(6)));

        //vidimo i tickets
        vehicleForTest.setTickets(ticketUsing, LocalDate.now());

        //salvo
        vehicleDAO.save(vehicleForTest);

        //METTO IN MANUTENZIONE IL VEICOLO ----------------------------------------------------------------------------

        //creo la manutenzione
        Maintenance maintenance = new Maintenance("Cambio gomme e olio", LocalDate.of(2024, 1, 10), vehicleForTest);
        maintenanceDAO.save(maintenance);


        //CONTROLLO CHE NON MI LASCI VIDIMARE DEI BIGLIETTI DATO CHE NON E' ATTIVO MA IN MANUTENZIONE
        //vehicleForTest.setTickets(ticketUsingDue,LocalDate.now());
        // vehicleDAO.save(vehicleForTest);

        maintenanceDAO.setDateOfEndMaintenance(maintenance, LocalDate.now());
        maintenanceDAO.save(maintenance);

        //DOPO  IL SET DELLA FINE MANUTENZIONE POSSO VIDIMARE I BIGLETTI
        //vehicleForTest.setTickets(ticketUsingDue,LocalDate.now());
        //vehicleDAO.save(vehicleForTest);

        //CREO UN ALTRA MANUTENZIONE PER LO STESSO VEICOLO
        //Maintenance maintenanceDue = new Maintenance("Cambio pastiglie freni", LocalDate.of(2025, 1, 10), vehicleForTest);
        //maintenanceDAO.save(maintenanceDue);

        //se non finisce una manutenzione un veicolo non posso farne partire un altra
        //Maintenance maintenanceTre = new Maintenance("Cosmin lo vuole provare", LocalDate.of(2026, 1, 10), vehicleForTest);
        //maintenanceDAO.save(maintenanceDue);

        System.out.println("Hello World!");
    }
}

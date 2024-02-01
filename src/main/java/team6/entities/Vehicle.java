package team6.entities;


import team6.enums.VehicleState;
import team6.enums.VehicleType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "getAllVehicles", query = "SELECT v FROM Vehicle v")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private int numberOfSeats;

    @Enumerated(EnumType.STRING)
    private VehicleState state;

    @OneToMany(mappedBy = "vehicle")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "vehicleMain")
    private List<Maintenance> maintenanceList;


    @ManyToMany
    @JoinTable(name = "vehicle_route", // DEFINIAMO COME SARA' FATTA LA JUNCTION TABLE
            joinColumns = @JoinColumn(name = "vehicle_id"), // DEFINIAMO I NOMI DELLE SUE COLONNE
            inverseJoinColumns = @JoinColumn(name = "route_id")
    )
    private List<Route> routeList;


    public Vehicle() {
    }

    public Vehicle(VehicleType type) {
        this.type = type;
        if (Objects.equals(this.type.toString(), "TRAM")) {
            this.numberOfSeats = 40;
        } else if (Objects.equals(this.type.toString(), "BUS")) {
            this.numberOfSeats = 70;
        }
        this.state = VehicleState.ACTIVE;
    }


//    public void setTicketsList(List<Ticket> tickets) {
//        if (Objects.equals(this.state.toString(), "ACTIVE")) {
//            this.tickets = tickets;
//            this.tickets.forEach(ticket -> {
//                ticket.setDateSell(LocalDate.now());
//            //   ticket.setVehicle(this);
//            });
//
//            System.out.println("Biglietto vidimato correttamente !!");
//        } else if (Objects.equals(this.state.toString(), "IN_MAINTENANCE")) {
//            System.out.println("Il veicolo è in manutenzione, non puoi salire !");
//        }
//    }

    public long getId() {
        return id;
    }


    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public VehicleState getState() {
        return state;
    }

    public void setState(VehicleState state) {
        this.state = state;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets, LocalDate date) {
        if (Objects.equals(this.state.toString(), "ACTIVE")) {

            boolean allTicketsValid = tickets.stream().allMatch(ticket -> ticket.isValid());

            if (allTicketsValid) {
                this.tickets = tickets;

                tickets.forEach(ticket -> {
                    ticket.setDateValidation(date);
                    ticket.setValid(false);
                    ticket.setVehicle(this);
                });
            } else {
                System.out.println("Attenzione, uno o più dei biglietti che vogliono essere vidimati non è valido !");
            }
        } else {
            System.out.println("Il mezzo è in manutenzione, NON puoi vidimare il tuo biglietto per questo veicolo !");
        }

    }

    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }

    public void setMaintenanceList(List<Maintenance> maintenanceList) {
        this.maintenanceList = maintenanceList;
    }
}

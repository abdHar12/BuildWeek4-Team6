package team6.entities;


import team6.enums.VehicleState;
import team6.enums.VehicleType;

import javax.persistence.*;
import java.util.List;

@Entity
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

    public Vehicle() {
    }

    public Vehicle(VehicleType type) {
        this.type = type;
        if (this.type.toString() == "TRAM" )
        {
            this.numberOfSeats = 40;
        } else if (this.type.toString() == "BUS") {
            this.numberOfSeats = 70;
        }
        this.state = VehicleState.ACTIVE;
    }

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

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }

    public void setMaintenanceList(List<Maintenance> maintenanceList) {
        this.maintenanceList = maintenanceList;
    }
}

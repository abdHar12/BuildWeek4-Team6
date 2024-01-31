package team6.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @Column(name = "route_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

@Column(name = "route_name")
    private String routeName;

@Transient
    private String departure;
@Transient
    private String arrivals;

@Column(name = "duration_minutes")
    private int durationMinutes;

    @ManyToMany
    @JoinTable(name = "vehicle_route", // DEFINIAMO COME SARA' FATTA LA JUNCTION TABLE
            joinColumns = @JoinColumn(name = "route_id"), // DEFINIAMO I NOMI DELLE SUE COLONNE
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicleList;



//CONSTRUCTORS


    public Route() {
    }

    public Route(String departure, String arrivals, int durationMinutes) {

        this.routeName = departure + " - " + arrivals;
        this.departure = departure;
        this.arrivals = arrivals;
        this.durationMinutes = durationMinutes;
    }

    // GETTERS AND SETTERS


    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrivals() {
        return arrivals;
    }

    public void setArrivals(String arrivals) {
        this.arrivals = arrivals;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int averageDuration) {
        this.durationMinutes = averageDuration;
    }

    // TOSTRING

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", routeName='" + routeName + '\'' +
                ", departure='" + departure + '\'' +
                ", arrivals='" + arrivals + '\'' +
                ", averageDuration=" + durationMinutes +
                '}';
    }
}

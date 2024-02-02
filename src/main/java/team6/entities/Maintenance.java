package team6.entities;

import team6.enums.VehicleState;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@NamedQuery(name = "getMaintenanceByVehicle", query = "SELECT m FROM Maintenance m WHERE m.vehicleMain = :vehicle")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type_maintenance")
    private String typeMaintenance;

    @Column(name = "date_of_start_maintenance", nullable = false)
    private LocalDate dateOfStartMaintenance;

    @Column(name = "date_of_end_maintenance")
    private LocalDate dateOfEndMaintenance;

    @ManyToOne
    private Vehicle vehicleMain;

    public Maintenance() {
    }

    public Maintenance(String typeMaintenance, LocalDate dateOfStartMaintenance, Vehicle vehicle) {
        this.vehicleMain = vehicle;
        if (vehicleMain.getState().equals(VehicleState.ACTIVE)) {
            this.typeMaintenance = typeMaintenance;
            this.dateOfStartMaintenance = dateOfStartMaintenance;
            this.vehicleMain.setState(VehicleState.IN_MAINTENANCE);
        } else {
            System.err.println("Il veicolo è già in manutenzione");
        }

    }

    public long getId() {
        return id;
    }

    public String getTypeMaintenance() {
        return typeMaintenance;
    }

    public void setTypeMaintenance(String typeMaintenance) {
        this.typeMaintenance = typeMaintenance;
    }

    public LocalDate getDateOfStartMaintenance() {
        return dateOfStartMaintenance;
    }

    public void setDateOfStartMaintenance(LocalDate dateOfStartMaintenance) {
        this.dateOfStartMaintenance = dateOfStartMaintenance;
    }

    public LocalDate getDateOfEndMaintenance() {
        return dateOfEndMaintenance;
    }

    public void setDateOfEndMaintenance(LocalDate dateOfEndMaintenance) {
        this.dateOfEndMaintenance = dateOfEndMaintenance;
        this.vehicleMain.setState(VehicleState.ACTIVE);
    }

    public Vehicle getVehicle() {
        return vehicleMain;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicleMain = vehicle;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "id=" + id +
                ", typeMaintenance='" + typeMaintenance + '\'' +
                ", dateOfStartMaintenance=" + dateOfStartMaintenance +
                ", dateOfEndMaintenance=" + dateOfEndMaintenance +
                ", vehicleMain=" + vehicleMain +
                '}';
    }
}

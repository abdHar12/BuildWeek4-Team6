package team6.entities;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
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
        this.typeMaintenance = typeMaintenance;
        this.dateOfStartMaintenance = dateOfStartMaintenance;
        this.vehicleMain = vehicle;
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
    }

    public Vehicle getVehicle() {
        return vehicleMain;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicleMain = vehicle;
    }


}

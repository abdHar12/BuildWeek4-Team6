package team6.entities;

import team6.abstractclasses.Bookable;
import team6.abstractclasses.Sellers;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@DiscriminatorValue("tickets")
public class Ticket extends Bookable {

    @Column(name = "date_validation")
    private LocalDate dateValidation;


    protected boolean valid;


    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


    public Ticket() {
    }

    public Ticket(double price, LocalDate dateSell, Sellers placeSell) {
        super( dateSell, placeSell);
        this.valid = true;
        this.price=price;
    }



    public LocalDate getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(LocalDate dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                ", id=" + id +
                ", price=" + price +
                ", dateSell=" + dateSell +
                "dateValidation=" + dateValidation +
                ", placeSell=" + placeSell +
                ", valid=" + valid +
                "} ";
    }
}

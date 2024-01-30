package team6.entities;

import team6.abstractclasses.Bookable;
import team6.abstractclasses.Sellers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "tickets")
public class Ticket extends Bookable {

    @Column(name = "date_validation")
    private LocalDate dateValidation;

    @Column(nullable = false)
    protected boolean valid;

    public Ticket() {}

    public Ticket(double price, LocalDate dateSell, Sellers placeSell,  boolean valid  ) {
        super(price, dateSell, placeSell);
        this.valid = true;
    }

    public LocalDate getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(LocalDate dateValidation) {
        this.dateValidation = dateValidation;
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

package team6.abstractclasses;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "bookables")
public abstract class Bookable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    protected long id;

    @Column(nullable = false)
    protected double price;


    @Column(name = "date_sell",nullable = false)
    protected LocalDate dateSell;



    @ManyToOne
    @JoinColumn(name = "place_sell")
    protected Sellers placeSell;




    public Bookable (){}


    public Bookable(double price, LocalDate dateSell, Sellers placeSell) {
        this.price = price;
        this.dateSell = dateSell;
        this.placeSell = placeSell;

    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateSell() {
        return dateSell;
    }

    public void setDateSell(LocalDate dateSell) {
        this.dateSell = dateSell;
    }

    public Sellers getPlaceSell() {
        return placeSell;
    }

    public void setPlaceSell(Sellers placeSell) {
        this.placeSell = placeSell;
    }



}







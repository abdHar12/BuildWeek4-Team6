package team6.abstractclasses;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "bookables")

//query per ricerca in lasso di tempo e venditore
@NamedQuery(name = "getBookableByDateSeller", query = "SELECT b FROM Bookable b WHERE b.placeSell = :seller AND b.dateSell BETWEEN :startDate AND :endDate ")


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


    public Bookable( LocalDate dateSell, Sellers placeSell) {
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







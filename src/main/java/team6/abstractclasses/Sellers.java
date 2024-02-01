package team6.abstractclasses;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "seller_type")
@NamedQuery(name="getAllSellers", query="SELECT s FROM Sellers s")
public abstract class   Sellers {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    protected long id;

@Column(nullable = false)
protected String place;

@OneToMany(mappedBy = "placeSell")
private List<Bookable> listOfBuys;

    public Sellers() {
    }

    public Sellers( String place) {

        this.place = place;
    }

    public long getId() {
        return id;
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


}

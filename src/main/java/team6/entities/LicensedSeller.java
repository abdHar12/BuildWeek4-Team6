package team6.entities;

import team6.abstractclasses.Sellers;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LicensedSeller extends Sellers {

    @Column(name = "name_of_shop")
    private String nameOfShop;


    public LicensedSeller(String place, String nameOfShop) {
        super(place);
        this.nameOfShop = nameOfShop;
    }

    public LicensedSeller() {
    }

    public String getNameOfShop() {
        return nameOfShop;
    }

    public void setNameOfShop(String nameOfShop) {
        this.nameOfShop = nameOfShop;
    }

    @Override
    public String toString() {
        return "LicensedSeller{" +
                "nameOfShop='" + nameOfShop + '\'' +
                "id=" + id +
                ", place='" + place + '\'' +
                '}';
    }


}

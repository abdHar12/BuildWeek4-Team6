package team6.entities;

import team6.abstractclasses.Sellers;
import team6.enums.ActiveEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class VendingMachine extends Sellers {

    @Enumerated(EnumType.STRING)
    private ActiveEnum state;


    public VendingMachine (){}

    public VendingMachine(String place, ActiveEnum state) {
        super(place);
        this.state = state;
    }

    public ActiveEnum getState() {
        return state;
    }

    public void setState(ActiveEnum state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "VendingMachine{" +
                "state=" + state +
                ", id=" + id +
                ", place='" + place + '\'' +
                "} ";
    }
}


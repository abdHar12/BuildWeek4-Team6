package team6.entities;

import team6.abstractclasses.Bookable;
import team6.abstractclasses.Sellers;
import team6.enums.SubDuration;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("subscriptions")
public class Subscription extends Bookable {


    @Enumerated(EnumType.STRING)
    private SubDuration duration;

    @Column(name = "date_expiration")
    private LocalDate dateExpiration;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    protected boolean valid;

    public Subscription() {
    }

    public Subscription(LocalDate dateSell, Sellers placeSell, SubDuration duration, User user) {
        super(dateSell, placeSell);
        this.duration = duration;
        this.user = user;
        this.valid = true;
        if (duration.equals(SubDuration.WEEKLY)) {
            this.dateExpiration = dateSell.plusWeeks(1);
            this.price = 30.0;
        } else if (duration.equals(SubDuration.MONTHLY)) {
            this.dateExpiration = dateSell.plusMonths(1);
            this.price = 60.0;
        }

    }

    public SubDuration getDuration() {
        return duration;
    }

    public void setDuration(SubDuration duration) {
        this.duration = duration;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }


    @Override
    public String toString() {
        return "Subscription{" +
                "duration=" + duration +
                ", dateExpiration=" + dateExpiration +
                ", user=" + user +
                ", valid=" + valid +
                ", id=" + id +
                ", price=" + price +
                ", dateSell=" + dateSell +
                ", placeSell=" + placeSell +
                "} ";
    }
}

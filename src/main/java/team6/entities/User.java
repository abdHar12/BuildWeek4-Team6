package team6.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_number")
    private long cardNumber;


    private String name;

    private String surname;

    @Column(name = "date_expired")
    private LocalDate dateExpired;

    @Column(name = "date_activation")
    private LocalDate dateActivation;

    @OneToOne(mappedBy = "user")
    private Subscription subscription;
    public User(){}

    public User( String name, String surname, LocalDate dateActivation) {

        this.name = name;
        this.surname = surname;
        this.dateExpired = dateActivation.plusYears(1);
        this.dateActivation = dateActivation;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(LocalDate dateExpired) {
        this.dateExpired = dateExpired;
    }

    public LocalDate getDateActivation() {
        return dateActivation;
    }

    public void setDateActivation(LocalDate dateActivation) {
        this.dateActivation = dateActivation;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "User{" +
                "cardNumber=" + cardNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateExpired=" + dateExpired +
                ", dateActivation=" + dateActivation +
                ", subscription=" + subscription +
                '}';
    }
}

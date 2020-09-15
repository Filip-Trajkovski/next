package com.next.reservations.core.domain;


import javax.persistence.*;

@Entity
@Table(schema = "reservations", name = "reservation_details")
public class ReservationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "comment")
    private String comment;

    @Column(name = "number_of_players", nullable = false)
    private int numberOfPlayers;

    public ReservationDetails(){}

    public ReservationDetails(String fullName, String email, String phoneNumber, String comment, int numberOfPlayers) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        this.numberOfPlayers = numberOfPlayers;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}

package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Package {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "destinationId")
    private Destination destination;

    @Column
    private String name;

    @Column
    private String price;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private String extraDetails;

    @Column
    private Integer numberOfBookings;

    @Column
    private String status;

    @OneToMany(mappedBy = "packageId")
    private List<Booking> bookings;

    public Package() {
    }

    public Package(String id, Destination destination, String name, String price, LocalDate startDate, LocalDate endDate,
                   String extraDetails, Integer numberOfBookings, String status, List<Booking> bookings) {
        this.id = id;
        this.destination = destination;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraDetails = extraDetails;
        this.numberOfBookings = numberOfBookings;
        this.status = status;
        this.bookings = bookings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Destination getDestination() { return destination; }

    public void setDestination(Destination destination) { this.destination = destination; }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    public Integer getNumberOfBookings() {
        return numberOfBookings;
    }

    public void setNumberOfBookings(Integer numberOfBookings) {
        this.numberOfBookings = numberOfBookings;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Package \"" + name + "\"\n" +
                "id = " + id +
                ", destination: " + destination.getDestination() +
                ", price: " + price + "€" + "\n" +
                "startDate: " + startDate +
                ", endDate: " + endDate + "\n" +
                "extraDetails: " + extraDetails + "\n" +
                "number of possible bookings: " + numberOfBookings +
                ", status: " + status + "\n";
    }
}

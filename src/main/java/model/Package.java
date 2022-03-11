package model;

import javax.persistence.*;
import java.util.Date;

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
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String extraDetails;

    @Column
    private Integer numberOfBookings;

    @Column
    private String status;

    public Package() {
    }

    public Package(String id, Destination destination, String name, String price, Date startDate, Date endDate,
                   String extraDetails, Integer numberOfBookings, String status) {
        this.id = id;
        this.destination = destination;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraDetails = extraDetails;
        this.numberOfBookings = numberOfBookings;
        this.status = status;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
}

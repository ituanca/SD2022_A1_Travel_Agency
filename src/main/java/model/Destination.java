package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Destination {

    @Id
    private String id;

    @Column
    private String destination;

    @OneToMany(mappedBy = "destination")
    private List<Package> packages;

    public Destination() {
    }

    public Destination(String id, String destination, List<Package> packages) {
        this.id = id;
        this.destination = destination;
        this.packages = packages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }
}

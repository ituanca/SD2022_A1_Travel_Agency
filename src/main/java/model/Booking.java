package model;

import javax.persistence.*;

@Entity
@Table(name="booking")
public class Booking {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "packageId")
    private Package packageId;

    public Booking() {
    }

    public Booking(String id, User user, Package packageInstance) {
        this.id = id;
        this.user = user;
        this.packageId = packageInstance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Package getPackageInstance() {
        return packageId;
    }

    public void setPackageInstance(Package packageInstance) {
        this.packageId = packageInstance;
    }
}

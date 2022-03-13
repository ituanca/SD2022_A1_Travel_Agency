package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    private String id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    public User(){}

    public User(String id, String firstName, String lastName, String username, String email, String password, List<Booking> bookings) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bookings = bookings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}

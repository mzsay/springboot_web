package app.hibernate;

/**
 * Created by saifularifin on 6/22/2016.
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 * Represents an User for this web application.
 */
@Entity
@Table(name = "users")
public class User {
    // ------------------------
    // PRIVATE FIELDS
    // ------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String email;
    @NotNull
    private String name;
    // ------------------------
    // PUBLIC METHODS
    // ------------------------
    public User() { }
    public User(long id) {
        this.id = id;
    }
    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long value) {
        this.id = value;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String value) {
        this.email = value;
    }
    public String getName() {
        return name;
    }
    public void setName(String value) {
        this.name = value;
    }
}
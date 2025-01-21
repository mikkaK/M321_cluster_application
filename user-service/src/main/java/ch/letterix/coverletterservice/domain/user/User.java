package ch.letterix.coverletterservice.domain.user;

import ch.letterix.coverletterservice.core.generic.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user")
@JsonIgnoreProperties(value = { "id" })
public class User extends AbstractEntity {

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 30, message = "Name must be less than 30 characters")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Surname is mandatory")
    @Size(max = 30, message = "Surname must be less than 30 characters")
    private String surname;

    @Column(name="age")
    @NotBlank(message = "Age is mandatory")
    @Size(min = 1, max = 3, message = "Age must be between 1 and 3 characters")
    private int age;

    @Column(name="address")
    @NotBlank(message = "Address is mandatory")
    @Size(max = 50, message = "Address must be less then 50 characters")
    private String address;

    @Column(name="email", unique = true)
    @NotBlank(message = "Email is mandatory")
    @Size(max = 40, message = "Email must be less then 40 characters")
    private String email;

    public User() {
    }

    public User(UUID id, String name, String surname, int age, String address, String email) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
        this.email = email;
    }
}

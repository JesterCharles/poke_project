package com.revature.pokedex.trainer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data // this handles toString, hashCode, equals() and your getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainer")
public class Trainer {

    @NotBlank(message = "Hey you need a first name, it cannot be blank")
    @NotNull
    private String fname;
    private String lname;
    @Id
    @Email(message = "please provide valid email")
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(message = "Minimum eight characters, at least one letter, one number and one special character required:", regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String password;
    @Size(min = 10, max = 1000)
    private String dob;

}

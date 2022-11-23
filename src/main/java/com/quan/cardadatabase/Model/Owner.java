package com.quan.cardadatabase.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Owner")
@Table(name = "owner")
public class Owner {
    @Id
   
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
        
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NonNull
    @NotBlank(message = "firstname cannot be blank")
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NonNull
    @NotBlank(message = "lastname cannot be blank")
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NonNull
    @Email(message = "email must be in form of email")
    @NotBlank(message = "email cannot be blank")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
     @JsonBackReference
    @OneToMany(mappedBy = "carOwner", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();
}

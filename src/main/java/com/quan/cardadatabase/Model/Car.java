package com.quan.cardadatabase.Model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "car")
@Entity(name = "Card") 
public class Car {
    @Id
    @SequenceGenerator(
        name = "car_sequence",
        sequenceName = "car_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "car_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NonNull
    @NotBlank(message = "brand cannot be blank")
    @Column(name = "brand", nullable = false)
    private String brand;

    @NonNull
    @NotBlank(message = "model cannot be blank")
    @Column(name = "model", nullable = false)
    private String  model;

    @NonNull
    @NotBlank(message = "color cannot be blank")
    @Column(name = "color", nullable = false)
    private String  color;

    @NonNull
    @NotBlank(message = "registerNumber cannot be blank")
    @Column(name = "registerNumber", nullable = false)
    private String registerNumber;

    @NonNull
    //@Past
    @Column(name = "year", nullable = false)
    private int year;

    @NonNull
    @Min(value = 0, message = "price cannot be lower than 0")
    @Column(name = "price", nullable = false)
    private double price;

   
    @NonNull
    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "carOwner_id", referencedColumnName = "id")
    private Owner carOwner;
    
  

    @Override
    public String toString() {
        return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", color=" + color + ", registerNumber="
                + registerNumber + ", year=" + year + ", price=" + price + "]";
    }


  
}

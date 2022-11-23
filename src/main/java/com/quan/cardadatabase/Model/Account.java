package com.quan.cardadatabase.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Account")
@Table(name = "account")
public class Account {
    @Id
    @SequenceGenerator(
        name = "account_sequence",
        sequenceName = "account_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "account_sequence"
    )
    @Column(name = "id", updatable = false)
    private long id;
    
    @NonNull
    @NotBlank(message = "username cannot be blank")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NonNull
    @NotBlank(message = "password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Override
    public String toString() {
        return "Account [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

    
}

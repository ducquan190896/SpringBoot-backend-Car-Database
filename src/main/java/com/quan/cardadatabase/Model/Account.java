package com.quan.cardadatabase.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @RequiredArgsConstructor
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
    

    @NotBlank(message = "username cannot be blank")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    
    @NotBlank(message = "password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Override
    public String toString() {
        return "Account [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

    public Account( String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
}

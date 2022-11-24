package com.quan.cardadatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.quan.cardadatabase.Model.Account;
import com.quan.cardadatabase.Model.Car;
import com.quan.cardadatabase.Model.Owner;
import com.quan.cardadatabase.Model.Role;
import com.quan.cardadatabase.Model.RoleType;
import com.quan.cardadatabase.Repository.AccountRepos;
import com.quan.cardadatabase.Repository.CarRepos;
import com.quan.cardadatabase.Repository.OwnerRepos;
import com.quan.cardadatabase.Repository.RoleRepos;

@SpringBootApplication
public class CardadatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardadatabaseApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CarRepos carRepos, OwnerRepos ownerRepos, AccountRepos accountRepos, RoleRepos roleRepos) {
		return args -> {
			Owner owner1 = new Owner("John" , "Johnson", "john@gmail.com");
			Owner owner2 = new Owner("Mary" , "Robinson", "Mary@gmail.com");
			ownerRepos.save(owner1);
			ownerRepos.save(owner2);
			
			Car car2 = new Car("Nissan", "Leaf", "White",
            "SSJ-3002", 2014, 29000, owner2);
			Car car3 =  new Car("Toyota", "Prius", "Silver",
            "KKO-0212", 2018, 39000, owner2);
			Car car1 = new Car("Ford", "Mustang", "Red", 
            "ADF-1121", 2017, 59000, owner1);
			
			
			
			carRepos.save(car1);
			carRepos.save(car2);
			carRepos.save(car3);

			Role user = new Role(RoleType.USER);
			Role manager = new Role(RoleType.MANAGER);
			Role admin = new Role(RoleType.ADMIN);

			roleRepos.save(user);
			roleRepos.save(manager);
			roleRepos.save(admin);

			Account quanAdmin = new Account("quan_admin", "123456");
			quanAdmin.setRole(admin);
			quanAdmin.setPassword(new BCryptPasswordEncoder().encode(quanAdmin.getPassword()));
			accountRepos.save(quanAdmin);
			Account quan = new Account("quan", "123456");
			quan.setPassword(new BCryptPasswordEncoder().encode(quan.getPassword()));
			
			quan.setRole(user);
			accountRepos.save(quan);
			
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

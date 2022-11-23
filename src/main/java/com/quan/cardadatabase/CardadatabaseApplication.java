package com.quan.cardadatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.quan.cardadatabase.Model.Car;
import com.quan.cardadatabase.Model.Owner;
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
	
			Car car1 = new Car("Ford", "Mustang", "Red", 
            "ADF-1121", 2017, 59000, owner1);
			Car car2 = new Car("Nissan", "Leaf", "White",
            "SSJ-3002", 2014, 29000, owner2);
			Car car3 =  new Car("Toyota", "Prius", "Silver",
            "KKO-0212", 2018, 39000, owner2);
			car1.setCarOwner(owner2);
			car2.setCarOwner(owner1);
			car3.setCarOwner(owner2);
		
			
			ownerRepos.save(owner1);
			ownerRepos.save(owner2);
			carRepos.save(car1);
			carRepos.save(car2);
			carRepos.save(car3);
		};
	}
}

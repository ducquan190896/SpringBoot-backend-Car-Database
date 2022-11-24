package com.quan.cardadatabase.Controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.cardadatabase.Model.Owner;
import com.quan.cardadatabase.Service.OwnerService;

@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    @GetMapping("/all")
    public ResponseEntity<List<Owner>> getAll() {
        return new ResponseEntity<List<Owner>>(ownerService.getOwners(), HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Owner> getAccountByEmail(@PathVariable String email) {
        return new ResponseEntity<>(ownerService.getOwner(email), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> saveOwner(@Valid @RequestBody Owner onwer) {
        ownerService.saveOwner(onwer);
        return new ResponseEntity<HttpStatus>( HttpStatus.CREATED);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @Valid @RequestBody Owner owner) {
        return new ResponseEntity<Owner>(ownerService.updateOwner(id, owner), HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

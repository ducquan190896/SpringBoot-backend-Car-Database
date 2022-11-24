package com.quan.cardadatabase.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.cardadatabase.Exception.EntityNotFoundException;
import com.quan.cardadatabase.Model.Owner;
import com.quan.cardadatabase.Repository.OwnerRepos;

@Service
public class OwnerServiceImp implements OwnerService{
    @Autowired
    OwnerRepos ownerRepos;

    @Override
    public void deleteOwner(Long id) {
        
        Optional<Owner> entity = ownerRepos.findById(id);
        Owner owner = ischeck(entity, id);
        ownerRepos.delete(owner);
    }

    @Override
    public Owner getOwner(String email) {
        // TODO Auto-generated method stub
        Optional<Owner> entity = ownerRepos.findByEmail(email);
        Owner owner = ischeck(entity, 404L);
        return owner;
    }

    @Override
    public List<Owner> getOwners() {
        return ownerRepos.findAll();
    }

    @Override
    public void saveOwner(Owner owner) {
        Optional<Owner> entity = ownerRepos.findByEmail(owner.getEmail());
        if(entity.isPresent()) {
            throw new EntityNotFoundException("the owner with email " + owner.getEmail() + " already exist");
        }
        ownerRepos.save(owner);
        
    }

    @Override
    public Owner updateOwner(Long id, Owner owner) {
        Optional<Owner> entity = ownerRepos.findById(id);
        Owner owner1 = ischeck(entity, id);
        owner1.setFirstname(owner.getFirstname());
        owner1.setLastname(owner.getLastname());
        owner1.setEmail(owner.getEmail());
       return ownerRepos.save(owner1);
        
    }

    private Owner ischeck(Optional<Owner> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the owner with the id " + id + " not found");
    }
    
}

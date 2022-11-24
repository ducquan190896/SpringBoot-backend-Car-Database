package com.quan.cardadatabase.Service;

import java.util.List;

import com.quan.cardadatabase.Model.Owner;

public interface OwnerService {
    void saveOwner(Owner owner);
    void deleteOwner(Long id);
    Owner getOwner(String email);
    List<Owner> getOwners();
    Owner updateOwner(Long id, Owner owner);
}

package com.sda.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sda.store.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}

package com.sda.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sda.store.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

	List<Status> findByOrderByIdStauts();

}

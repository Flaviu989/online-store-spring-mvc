package com.sda.store.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.store.model.Status;
import com.sda.store.repository.StatusRepository;
import com.sda.store.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;

	@Override
	public Status findStatus(int id) {
		return statusRepository.findById(id).orElse(null);
	}
}

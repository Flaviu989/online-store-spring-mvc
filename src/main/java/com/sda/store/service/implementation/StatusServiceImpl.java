package com.sda.store.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.sda.store.repository.StatusRepository;
import com.sda.store.service.StatusService;

public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;
}

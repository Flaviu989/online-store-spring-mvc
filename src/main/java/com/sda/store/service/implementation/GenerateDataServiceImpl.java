package com.sda.store.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.store.model.Status;
import com.sda.store.repository.StatusRepository;
import com.sda.store.service.GenerateDataService;

@Service
public class GenerateDataServiceImpl implements GenerateDataService {

	@Autowired
	private StatusRepository statusRepository;

	@Override
	public void generateDefaultStatus() {
		statusRepository.deleteAll();

		List<Status> defaultStatuses = new ArrayList<Status>();
		Status beforeLogin = new Status();
		beforeLogin.setDescription("Not Authenticated");
		defaultStatuses.add(beforeLogin);

		Status beforeBuy = new Status();
		beforeBuy.setDescription("In Progress");
		defaultStatuses.add(beforeBuy);

		Status afterBuy = new Status();
		afterBuy.setDescription("Order Placed");
		defaultStatuses.add(afterBuy);

		Status afterDelivery = new Status();
		afterDelivery.setDescription("Delivered");
		defaultStatuses.add(afterDelivery);

		statusRepository.saveAll(defaultStatuses);

	}

	@Override
	public void printToConsole() {
		System.out.println("\n--- CURRENT POSSIBLE STATUSES ---");
		statusRepository.findAll().forEach(s -> System.out.println(s));
	}

	@PostConstruct
	public void generateAndPrintData() {
		if (statusRepository.findAll().size() < 4 || statusRepository.findAll().size() > 4) {
			generateDefaultStatus();
		}
		printToConsole();
	}

}

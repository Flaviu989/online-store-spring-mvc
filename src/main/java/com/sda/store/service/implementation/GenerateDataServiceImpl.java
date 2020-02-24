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
	
	private final String status1 = "In progress";
	private final String status2 = "Order placed";
	private final String status3 = "Delivered";

	@Autowired
	private StatusRepository statusRepository;

	@Override
	public void generateDefaultStatus() {
		statusRepository.deleteAll();

		List<Status> defaultStatuses = new ArrayList<Status>();

		Status beforeBuy = new Status();
		beforeBuy.setIdStauts(1);
		beforeBuy.setDescription(status1);
		defaultStatuses.add(beforeBuy);

		Status afterBuy = new Status();
		afterBuy.setIdStauts(2);
		afterBuy.setDescription(status2);
		defaultStatuses.add(afterBuy);

		Status afterDelivery = new Status();
		afterDelivery.setIdStauts(3);
		afterDelivery.setDescription(status3);
		defaultStatuses.add(afterDelivery);

		statusRepository.saveAll(defaultStatuses);

	}

	@Override
	public void printToConsole() {
		System.out.println("\n--- CURRENT POSSIBLE STATUSES ---");
		statusRepository.findByOrderByIdStauts().forEach(s -> System.out.println(s));
	}

	@PostConstruct
	public void generateAndPrintData() {
		List<Status> statuses = statusRepository.findByOrderByIdStauts();
		if (statuses.size() == 3) {
			
			if (!statuses.get(0).getDescription().equals(status1) || !statuses.get(1).getDescription().equals(status2)
					|| !statuses.get(2).getDescription().equals(status3))
				generateDefaultStatus();
		} else
			generateDefaultStatus();
		printToConsole();
	}

}

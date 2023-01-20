package com.auction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.auction.service.TransactionHistoryService;

@SpringBootApplication
@EnableMongoRepositories
public class ApiApplication implements CommandLineRunner{

	@Autowired
	TransactionHistoryService thRepo;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("hello world");
		// TransactionHistory history = new TransactionHistory(10, 100000.0, Date.valueOf("2023-1-20"));
		// Client client = new Client();
		// client.setFirstName("Ravaka");
		// client.setId(1);
		// history.setClient(client);
		// thRepo.save(history);

		// List<TransactionHistory> th = thRepo.findByClientId(1);
		// for (TransactionHistory transactionHistory : th) {
		// 	System.out.println(transactionHistory.toString());
		// }
		
	}

}

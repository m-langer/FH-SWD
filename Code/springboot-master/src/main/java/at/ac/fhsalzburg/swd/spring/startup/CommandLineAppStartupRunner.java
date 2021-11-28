package at.ac.fhsalzburg.swd.spring.startup;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import at.ac.fhsalzburg.swd.spring.dao.old.Customer;
import at.ac.fhsalzburg.swd.spring.dao.old.CustomerRepository;
import at.ac.fhsalzburg.swd.spring.services.*;
import at.ac.fhsalzburg.swd.spring.services.old.CustomerServiceInterface;
import at.ac.fhsalzburg.swd.spring.services.old.OrderServiceInterface;
import at.ac.fhsalzburg.swd.spring.services.old.ProductServiceInterface;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    // @Autowired
    // CustomerServiceInterface customerService;
    
    // @Autowired
    // ProductServiceInterface productService;
    
    // @Autowired
    // OrderServiceInterface orderService;
    
    // @Autowired
    // CustomerRepository repo;

    @Autowired
    IPersonalDataService personaDataService;






   // Initialize System with preset accounts and stocks
    @Override
    @Transactional // this method runs within one database transaction; performing a commit at the end
    public void run(String... args) throws Exception {

        
    	// customerService.addCustomer("Max", "Mustermann", "max@muster.man", "123");
    	// productService.addProduct("first product", 3.30f);
    	// Customer customer = customerService.getAll().iterator().next();
    	// customer.setCredit(100l);
    	// customer = customerService.getById(1l);
    	// orderService.addOrder(new Date(),customer, productService.getAll());
    }
}

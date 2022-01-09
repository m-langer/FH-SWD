package at.ac.fhsalzburg.swd.spring.startup;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import at.ac.fhsalzburg.swd.spring.dao.Customer;
import at.ac.fhsalzburg.swd.spring.dao.CustomerRepository;
import at.ac.fhsalzburg.swd.spring.enums.mediaCategory;
import at.ac.fhsalzburg.swd.spring.services.CustomerServiceInterface;
import at.ac.fhsalzburg.swd.spring.services.IMediaService;
import at.ac.fhsalzburg.swd.spring.services.IPaymentService;
import at.ac.fhsalzburg.swd.spring.services.IPersonalDataService;
import at.ac.fhsalzburg.swd.spring.services.OrderServiceInterface;
import at.ac.fhsalzburg.swd.spring.services.ProductServiceInterface;

@Profile("!test")
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    CustomerServiceInterface customerService;

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    OrderServiceInterface orderService;

    @Autowired
    CustomerRepository repo;

    @Autowired
    IPersonalDataService personalDataService;

    @Autowired
    IMediaService mediaService;

    @Autowired
    IPaymentService paymentService;

    // Initialize System with preset accounts and stocks
    @Override
    @Transactional // this method runs within one database transaction; performing a commit at the
                   // end
    public void run(String... args) throws Exception {
        personalDataService.addData("Max", "Mustermann1", "Musterhausen 1", new Date(10, 4, 2005), "hi@test.com");
        personalDataService.addData("Michael", "Mustermann2", "Musterhausen 6", new Date(12, 6, 2002), "mich@test.com");
        personalDataService.addData("Max", "Musterfrau", "Musterhausen 1", new Date(10, 4, 1998), "frau@test.com");
        mediaService.addBook("Test buch", "Test Auto", "123456789", 319, mediaCategory.thriller, false);
        mediaService.addMovie("Test Movie", "Adult Director", "18", 50, mediaCategory.action, true);
        mediaService.addCD("Casset Player", "Lena", 70, mediaCategory.thriller);
        paymentService.addPayment(personalDataService.getByLastName("Mustermann1").get(0).getId(), 10, "Hi das ist ein Test");


        customerService.addCustomer("Max", "Mustermann3", "max@muster.man", "123");
        productService.addProduct("first product", 3.30f);
        Customer customer = customerService.getAll().iterator().next();
        customer.setCredit(100l);
        customer = customerService.getById(1l);
        orderService.addOrder(new Date(), customer, productService.getAll());

    }
}

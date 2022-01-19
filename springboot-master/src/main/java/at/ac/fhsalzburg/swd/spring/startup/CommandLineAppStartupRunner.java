package at.ac.fhsalzburg.swd.spring.startup;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import at.ac.fhsalzburg.swd.spring.mediaManagement;
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

    @Autowired
    mediaManagement mediaMgnt;

    // Initialize System with preset accounts and stocks
    @Override
    @Transactional // this method runs within one database transaction; performing a commit at the
                   // end
    public void run(String... args) throws Exception {
        personalDataService.addData("Max", "Mustermann1", "Musterhausen 1", new Date(10, 4, 2005), "hi@test.com");
        personalDataService.addData("Michael", "Mustermann2", "Musterhausen 6", new Date(12, 6, 2002), "mich@test.com");
        personalDataService.addData("Max", "Musterfrau", "Musterhausen 1", new Date(10, 4, 1998), "frau@test.com");
        mediaService.addBook("Ein Toller Roman", "Rita Falk", "123456789", 259, mediaCategory.thriller, false, 3);
        mediaService.addBook("Einzig mögliche Zeit", "Wolfgang Joop", "987654321", 319, mediaCategory.thriller, false, 3);
        mediaService.addBook("Das Universum", "Stephen Hawking", "73759365836", 195, mediaCategory.physics, true, 3);
        mediaService.addMovie("James Bond", "Quentin Terentino", "6", 50, mediaCategory.thriller, false,9);
        mediaService.addMovie("Adult Movie", "James Cameron", "18", 50, mediaCategory.action, true,9);
        mediaService.addCD("Casset Player", "Lena", 70, mediaCategory.fantasy, 21);
        paymentService.addPayment(personalDataService.getByLastName("Mustermann1").get(0).getId(), 10, "Eine Zahlung von 10€ ist überfällig.");

        customerService.addCustomer("Max", "Mustermann3", "max@muster.man", "123");
        productService.addProduct("first product", 3.30f);
        Customer customer = customerService.getAll().iterator().next();
        customer.setCredit(100l);
        customer = customerService.getById(1l);
        orderService.addOrder(new Date(), customer, productService.getAll());

    }
}

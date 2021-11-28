package at.ac.fhsalzburg.swd.spring.services.old;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhsalzburg.swd.spring.dao.old.Customer;
import at.ac.fhsalzburg.swd.spring.dao.old.Order;
import at.ac.fhsalzburg.swd.spring.dao.old.OrderRepository;
import at.ac.fhsalzburg.swd.spring.dao.old.Product;


@Service
public class OrderService implements OrderServiceInterface {
    
	@Autowired
	private CustomerServiceInterface customerService;
	
	@Autowired
    private OrderRepository repo;
    
    public OrderService() {
    	
    }
	
	
	@Override
	public Order addOrder(Date date, Customer customer, Iterable<Product> products) {
		List<Product> productlist = new ArrayList<Product>();	
		products.forEach(productlist::add);
		
		if (customerService.hasCredit(customer)) {
			Order order = new Order(date,customer,productlist);
			order=repo.save(order);
	        
			return order;
		}
		else {
			return null;
		}
	    
	}

	
	@Override
	public Iterable<Order> getAll() {
		return repo.findAll();
	}
	
	@Override
	public Order getById(Long id) {
		return repo.findById(id).get();
	}
	
	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
}

package at.ac.fhsalzburg.swd.spring.services.old;
import java.util.Date;

import at.ac.fhsalzburg.swd.spring.dao.old.Customer;
import at.ac.fhsalzburg.swd.spring.dao.old.Order;
import at.ac.fhsalzburg.swd.spring.dao.old.Product;

public interface OrderServiceInterface {
	
	public abstract Order addOrder(Date date, Customer customer, Iterable<Product> products);
	
	public abstract Iterable<Order> getAll();
	
	public abstract Order getById(Long id);
	
	public abstract void deleteById(Long id);

}

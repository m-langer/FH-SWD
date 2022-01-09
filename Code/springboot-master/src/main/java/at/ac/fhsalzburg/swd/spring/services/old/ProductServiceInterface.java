package at.ac.fhsalzburg.swd.spring.services.old;

import java.util.Collection;

import at.ac.fhsalzburg.swd.spring.dao.old.Product;

public interface ProductServiceInterface {
	
	public abstract boolean addProduct(String name, float price);
	
	public abstract boolean addProduct(Product product);
	
	public abstract Collection<Product> getAll();
	
	public abstract Product getById(Long id);
	
	public abstract void deleteById(Long id);

}
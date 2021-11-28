package at.ac.fhsalzburg.swd.spring.dao.old;

import java.util.Collection;
import java.util.Date;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="orders")
public class Order {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
	@ManyToOne
	private Customer customer;
	
	@ElementCollection  
    private Collection<Product> products;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    protected Order() {}
    
    public Order(Date date, Customer customer, Collection<Product> products) {
    	this.customer = customer;
    	this.date = date;
    	this.products = products;
    }

    
	public Long getId() {
		return id;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Collection<Product> getProducts() {
		return products;
	}



	public void setProducts(Collection<Product> products) {
		this.products = products;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}

	
	

}

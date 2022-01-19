package at.ac.fhsalzburg.swd.spring;

import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import at.ac.fhsalzburg.swd.spring.dao.Customer;
import at.ac.fhsalzburg.swd.spring.dao.Media;
import at.ac.fhsalzburg.swd.spring.dao.PersonalData;
import at.ac.fhsalzburg.swd.spring.dao.Rental;
import at.ac.fhsalzburg.swd.spring.dto.MediaDTO;
import at.ac.fhsalzburg.swd.spring.dto.PersonalDataDTO;
import at.ac.fhsalzburg.swd.spring.dto.RentalDTO;
import at.ac.fhsalzburg.swd.spring.enums.mediaCategory;
import at.ac.fhsalzburg.swd.spring.enums.mediaType;
import at.ac.fhsalzburg.swd.spring.services.CustomerServiceInterface;
import at.ac.fhsalzburg.swd.spring.services.IPersonalDataService;

@Controller // marks the class as a web controller, capable of handling the HTTP requests.
			// Spring will look at the methods of the class marked with the @Controller
			// annotation and establish the routing table to know which methods serve which
			// endpoints.
public class MyController {

	@Autowired // To wire the application parts together, use @Autowired on the fields,
				// constructors, or methods in a component. Spring's dependency injection
				// mechanism wires appropriate beans into the class members marked with
				// @Autowired.
	private ApplicationContext context;

	@Resource(name = "sessionBean") // The @Resource annotation is part of the JSR-250 annotation collection and is
									// packaged with Jakarta EE. This annotation has the following execution paths,
									// listed by Match by Name, Match by Type, Match by Qualifier. These execution
									// paths are applicable to both setter and field injection.
									// https://www.baeldung.com/spring-annotations-resource-inject-autowire
	TestBean sessionBean;

	@Autowired
	CustomerServiceInterface customerService;

	@Autowired
	IPersonalDataService personalDataService;

	@Autowired
	mediaManagement mediaManagement;

	@Autowired
	TestBean singletonBean;

	@RequestMapping("/") // The @RequestMapping(method = RequestMethod.GET, value = "/path") annotation
							// specifies a method in the controller that should be responsible for serving
							// the HTTP request to the given path. Spring will work the implementation
							// details of how it's done. You simply specify the path value on the annotation
							// and Spring will route the requests into the correct action methods:
							// https://springframework.guru/spring-requestmapping-annotation/#:~:text=%40RequestMapping%20is%20one%20of%20the,map%20Spring%20MVC%20controller%20methods.
	public String index(Model model, HttpSession session) {

		if (session == null) {
			model.addAttribute("message", "no session");
		} else {
			Integer count = (Integer) session.getAttribute("count");
			if (count == null) {
				count = new Integer(0);
			}
			count++;
			session.setAttribute("count", count);
		}

		model.addAttribute("message", customerService.doSomething());

		model.addAttribute("halloNachricht", "welchem to SWD lab");

		model.addAttribute("customers", customerService.getAll());

		model.addAttribute("pD", personalDataService.getAll());
		model.addAttribute("media", mediaManagement.mediaService.getAll());
		model.addAttribute("rentals", mediaManagement.rentalService.getAll());
		model.addAttribute("rentalsOld", mediaManagement.rentalService.getAllOld());
		model.addAttribute("payments", mediaManagement.paymentService.getAll());

		model.addAttribute("beanSingleton", singletonBean.getHashCode());

		TestBean prototypeBean = context.getBean("prototypeBean", TestBean.class);
		model.addAttribute("beanPrototype", prototypeBean.getHashCode());

		model.addAttribute("beanSession", sessionBean.getHashCode());

		return "index";
	}

	@RequestMapping(value = { "/addCustomer" }, method = RequestMethod.POST)
	public String addCustomer(Model model, //
			@ModelAttribute("customerForm") CustomerForm customerForm) { // The @ModelAttribute is an annotation that
																			// binds a method parameter or method return
																			// value to a named model attribute and then
																			// exposes it to a web view:
																			// https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation
		String firstName = customerForm.getFirstName();
		String lastName = customerForm.getLastName();
		String eMail = customerForm.getEMail();
		String tel = customerForm.getTel();

		customerService.addCustomer(firstName, lastName, eMail, tel);

		return "redirect:/";
	}

	@RequestMapping(value = { "/addCustomer" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {
		CustomerForm customerForm = new CustomerForm();
		model.addAttribute("customerForm", customerForm);

		model.addAttribute("message", customerService.doSomething());

		return "addCustomer";
	}

	// Mappings for REST-Service

	@GetMapping("/customers") // @GetMapping annotation maps HTTP GET requests onto specific handler methods.
								// It is a composed annotation that acts as a shortcut for
								// @RequestMapping(method = RequestMethod.GET).
	public @ResponseBody List<Customer> allUsers() {

		return (List<Customer>) customerService.getAll();
	}

	@RequestMapping(value = { "/customers/{id}" }, method = RequestMethod.GET)
	public @ResponseBody Customer getCustomer(@PathVariable long id) {
		Customer customer = customerService.getById(id);

		return customer;
	}

	@RequestMapping(value = { "/customers/{id}" }, method = RequestMethod.PUT)
	public String setCustomer(@RequestBody Customer customer) {

		customerService.addCustomer(customer);

		return "redirect:/customers";
	}

	@DeleteMapping("/customers/{id}") // @DeleteMapping annotation maps HTTP DELETE requests onto specific handler
										// methods. It is a composed annotation that acts as a shortcut for
										// @RequestMapping(method = RequestMethod.DELETE).
	public String delete(@PathVariable String id) {
		Long customerid = Long.parseLong(id);
		customerService.deleteById(customerid);
		return "redirect:/customers";
	}

	//
	//
	// OWN CODE
	//
	//
	@PostMapping(value = { "/addPersonalData" })
	public String addPersonalData(Model model, //
			@ModelAttribute("personalDataForm") PersonalDataDTO pDForm) { // The @ModelAttribute is an annotation that
																			// binds a method parameter or method return
																			// value to a named model attribute and then
																			// exposes it to a web view:
																			// https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation
		String firstName = pDForm.getName();
		String lastName = pDForm.getLastName();
		String eMail = pDForm.geteMail();
		String adress = pDForm.getAdress();
		Date birthday = pDForm.getBirthday();
		personalDataService.addData(firstName, lastName, adress, birthday, eMail, pDForm.getIsStudent());
		return "redirect:/";
	}

	@GetMapping(value = { "/addPersonalData" })
	public String showAddPersonalDataPage(Model model) {
		PersonalDataDTO personalDataForm = new PersonalDataDTO();
		personalDataForm.setIsStudent(false);
		model.addAttribute("personalDataForm", personalDataForm);
		return "addPersonalData";
	}

	@PostMapping(value = { "/addMedia" })
	public String addMedia(Model model,
			@ModelAttribute("mediaDTO") MediaDTO mForm) {
		String Name = mForm.getName();
		String Author = mForm.getAuthor();
		int length = mForm.getLength();
		int shelf = mForm.getShelfNr();

		switch (mForm.getType()) {
			case simpleBook:
				mediaManagement.mediaService.addBook(Name, Author, mForm.getiSBNfSK(), length, mForm.getCategory(),
						false, shelf);
				break;
			case specializedBook:
				mediaManagement.mediaService.addBook(Name, Author, mForm.getiSBNfSK(), length, mForm.getCategory(),
						true, shelf);
				break;
			case movie:
				mediaManagement.mediaService.addMovie(Name, Author, mForm.getiSBNfSK(), length, mForm.getCategory(),
						false, shelf);
				break;
			case movieAdult:
				mediaManagement.mediaService.addMovie(Name, Author, mForm.getiSBNfSK(), length, mForm.getCategory(),
						true, shelf);
				break;
			case cd:
				mediaManagement.mediaService.addCD(Name, Author, length, mForm.getCategory(), shelf);
				break;
			default:
				break;
		}

		return "redirect:/";
	}

	@GetMapping(value = { "/addMedia" })
	public String showAddMediaPage(Model model) {
		MediaDTO mDTO = new MediaDTO();
		model.addAttribute("mediaDTO", mDTO);

		return "addMedia";
	}

	@GetMapping(value = { "/listAllMedia" })
	public String showAllMedia(Model model) {
		model.addAttribute("media", mediaManagement.mediaService.getAll());
		return "listMedia";
	}

	@GetMapping(value = { "/findMedia" })
	public String showFindMediaPage(Model model) {
		MediaDTO mDTO = new MediaDTO();
		model.addAttribute("mediaDTO", mDTO);

		return "findMedia";
	}

	@PostMapping(value = { "/findMedia" })
	public String findMedia(
			@ModelAttribute("mediaDTO") MediaDTO mForm) {
		String Name = mForm.getName();
		String Author = mForm.getAuthor();
		String isbnFsk = mForm.getiSBNfSK();
		mediaCategory category = mForm.getCategory();
		String result = "redirect:/listAllMediaBy?";
		if (!Name.isEmpty()) {
			result = result + "name=" + Name + "&";
		}
		if (!Author.isEmpty()) {
			result = result + "author=" + Author + "&";

		}
		if (!isbnFsk.isEmpty()) {
			result = result + "isbn=" + isbnFsk + "&";

		}
		if (category != mediaCategory.noCategory) {
			result = result + "category=" + category.toString() + "&";
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	@GetMapping(value = { "/listAllMedia/{name}" })
	public String showAllMedia(Model model, @PathVariable String name) {
		model.addAttribute("media", mediaManagement.mediaService.getMedia(name));
		return "listMedia";
	}

	@GetMapping("/listAllMediaBy")
	public String showAllMedia(Model model, @RequestParam(required = false) String name,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) String isbnfsk, @RequestParam(required = false) String category) {
		List<Media> res = new ArrayList<>();
		List<Media> result;
		int temp = 0;
		if (name != null) {
			res.addAll(mediaManagement.mediaService.getMedia(name.replace("+", " ")));
			temp++;
		}
		if (author != null) {
			res.addAll(mediaManagement.mediaService.getMediaByAuthor(author.replace("+", " ")));
			temp++;
		}
		if (isbnfsk != null) {
			res.addAll(mediaManagement.mediaService.getMediaByISBN(isbnfsk.replace("+", " ")));
			temp++;
		}
		if (category != null) {
			res.addAll(mediaManagement.mediaService
					.getMediaByCategory(mediaCategory.valueOf(category.replace("+", " "))));
			temp++;
		}
		if (temp > 1) {
			//
			result = res.stream()
					.filter(e -> Collections.frequency(res, e) > 1)
					.distinct()
					.collect(Collectors.toList());
		} else {
			result = res;
		}

		model.addAttribute("media", result);
		return "listMedia";
	}

	@RequestMapping(value = { "/listMediaWithID/{id}" }, method = RequestMethod.GET)
	public String showAllMedia(Model model, @PathVariable UUID id) {
		model.addAttribute("media", mediaManagement.mediaService.getMedia(id));
		return "listMedia";
	}

	@RequestMapping(value = { "/addRental" }, method = RequestMethod.POST)
	public String addRental(Model model,
			@ModelAttribute("rentalDTO") RentalDTO rDTO) {
		UUID mID = rDTO.getMediaID();
		UUID pID = rDTO.getPersonID();
		try {
			mediaManagement.rentMedia(mID, pID);
		} catch (Exception e) {
			return "redirect:/ageException?pID="+pID;
		}
		return "redirect:/";
	}
	@GetMapping("/ageException")
	public String showAgeExeptionPage(Model model, @RequestParam(required = false) String pID) {

		model.addAttribute("person", mediaManagement.personalDataService.gPersonalData(UUID.fromString(pID)));
		return "ageExept";
	}
	@GetMapping(value = { "/addRental" })
	public String showAddRentalPage(Model model) {
		RentalDTO rDTO = new RentalDTO();
		model.addAttribute("rentalDTO", rDTO);
		model.addAttribute("persons", mediaManagement.personalDataService.getAll());
		model.addAttribute("media", mediaManagement.mediaService.getAllNotRented());

		return "addRental";
	}

	@GetMapping(value = { "/findRentals" })
	public String showFindRentalPage(Model model) {
		RentalDTO rDTO = new RentalDTO();
		model.addAttribute("rentalDTO", rDTO);
		model.addAttribute("persons", mediaManagement.personalDataService.getAll());
		model.addAttribute("media", mediaManagement.mediaService.getAll());

		return "findRentals";
	}

	@PostMapping(value = { "/findRentals" })
	public String findRentals(
			@ModelAttribute("rentalDTO") RentalDTO rDTO) {
		UUID pID = rDTO.getPersonID();
		return "redirect:/listAllRentalsBy?pID=" + pID.toString();
	}

	@GetMapping("/listAllRentalsBy")
	public String showAllRentals(Model model, @RequestParam(required = false) String pID) {
		List<Rental> result = mediaManagement.rentalService.getRentalsPerPerson(UUID.fromString(pID));
		Map<UUID, Media> media = ((List<Media>) mediaManagement.mediaService.getAll()).stream()
				.collect(Collectors.toMap(Media::getId, Function.identity()));
		model.addAttribute("rental", result);
		model.addAttribute("mediaMap", media);
		return "listRentals";
	}

	@GetMapping(value = { "/returnMedia" })
	public String showReturnMediaPage(Model model) {
		RentalDTO rDTO = new RentalDTO();
		model.addAttribute("rentalDTO", rDTO);
		model.addAttribute("rentals", mediaManagement.rentalService.getAll());
		model.addAttribute("media", mediaManagement.mediaService.getAll());
		model.addAttribute("persons", mediaManagement.personalDataService.getAll());

		return "returnRentalWithRentalID";
	}

	@PostMapping(value = { "/returnMediaWithRentalID" })
	public String returnMedia(
			@ModelAttribute("rentalDTO") RentalDTO rDTO) {
		UUID rID = rDTO.getRentalID();
		mediaManagement.returnMedia(rID);
		return "redirect:/";
	}
}

package jot.projects.spring.test.springapp.services;

import java.util.Date;

import jot.projects.spring.springapp.data.domain.Address;
import jot.projects.spring.springapp.data.domain.Address.AddressType;
import jot.projects.spring.springapp.data.domain.Customer;
import jot.projects.spring.springapp.data.jpa.DbHibernateConfig;
import jot.projects.spring.springapp.data.reference.State;
import jot.projects.spring.springapp.services.AddressService;
import jot.projects.spring.springapp.services.CustomerService;
import jot.projects.spring.springapp.services.ReferenceService;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * A simple test class to unit test the <code>ServiceManager</code>.
 * 
 * @author jsicree
 * 
 */
@RunWith(JUnit4.class)
public class AddressServiceTest {

	protected final static Logger log = LoggerFactory.getLogger(AddressServiceTest.class); 

	protected static AddressService addressService;
	protected static CustomerService customerService;
	protected static ReferenceService referenceService;
	
	@BeforeClass
	public static void setup() {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(DbHibernateConfig.class);		
		referenceService = (ReferenceService) context
				.getBean("referenceService");		

		customerService = (CustomerService) context
				.getBean("customerService");		

		addressService = (AddressService) context
				.getBean("addressService");		
		
		addressService.removeAllAddresses();
		customerService.removeAllCustomers();
	}

	@Ignore
	@Test
	public void createAddress() {

		log.info(">> Entering createAddress.");

		State s = referenceService.getStateByCode("NJ");
		org.junit.Assert.assertNotNull("State was null.", s);
		org.junit.Assert.assertNotNull("State Id was null.", s.getId());
		log.info("State found: " + s);

//		Address address = addressService.createAddress("568 Bogert Rd.", null, "River Edge", s, "07661", customer, AddressType.HOME);
		Address address = addressService.createAddress("568 Bogert Rd.", null, "River Edge", s, "07661", AddressType.HOME);
		org.junit.Assert.assertNotNull("Address was null.", address);
		org.junit.Assert.assertNotNull("Address Id was null.", address.getId());
		log.info("Address created: " + address);

		log.info("<< Leaving createAddress.");
	}

	@Test
	public void createAddressForCustomer() {

		log.info(">> Entering createAddressForCustomer.");

		State s = referenceService.getStateByCode("AK");
		org.junit.Assert.assertNotNull("State was null.", s);
		org.junit.Assert.assertNotNull("State Id was null.", s.getId());
		log.info("State found: " + s);

		Customer customer = customerService.createCustomer("John", "Doe", "jdoe", new Date());		
		Address businessAddress = addressService.createAddress("10 Main St.", null, "Nome", s, "87656", customer, AddressType.BUSINESS);
		org.junit.Assert.assertNotNull("Address was null.", businessAddress);
		org.junit.Assert.assertNotNull("Address Id was null.", businessAddress.getId());
		log.info("Address created: " + businessAddress);
		Address homeAddress = addressService.createAddress("568 Bogert Rd.", null, "River Edge", s, "07661", customer, AddressType.HOME);
		org.junit.Assert.assertNotNull("Address was null.", homeAddress);
		org.junit.Assert.assertNotNull("Address Id was null.", homeAddress.getId());
		log.info("Address created: " + homeAddress);
		customer = customerService.getCustomerById(customer.getId());
		log.info("Customer has " + customer.getAddresses().size() + " addresses");
		log.info("<< Leaving createAddressForCustomer.");
	}
	
	@AfterClass
	public static void after() {
//		addressService.removeAllAddresses();
//		customerService.removeAllCustomers();
	}

}

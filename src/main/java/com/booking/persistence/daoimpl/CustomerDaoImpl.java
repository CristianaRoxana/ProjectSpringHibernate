package com.booking.persistence.daoimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.booking.persistence.dao.CustomerDao;
import com.booking.persistence.model.Admin;
import com.booking.persistence.model.Agent;
import com.booking.persistence.model.Customer;
import com.booking.persistence.model.Reservation;

public class CustomerDaoImpl implements CustomerDao{
	private static final Log logger = LogFactory.getLog(CustomerDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	static Session sessionObj;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	
	public void addCustomer(Customer customer) {
		sessionObj =this.sessionFactory.openSession();
		sessionObj.beginTransaction();
		sessionObj.persist(customer);
		sessionObj.getTransaction().commit();
		logger.info("Customer saved successfully, Customer Details="+customer);
	}

	
	public void updateCustomer(Customer customer) {
		//sessionObj =this.sessionFactory.openSession();
		sessionObj.update(customer);
		logger.info(" Customer updated successfully, Customer  Details="+customer);
	}

	@SuppressWarnings("unchecked")
	
	public  List<Customer> listCustomers() {
		List<Customer>customerList=null;
		sessionObj=this.sessionFactory.openSession();
		sessionObj.beginTransaction();
		customerList=(List<Customer>)sessionObj.createCriteria(Customer.class).list();
		return customerList;

	}

	
	public Customer getCustomerById(int customerID) {
		sessionObj =this.sessionFactory.openSession();		
		Customer customer = (Customer) sessionObj.load(Customer.class, new Integer(customerID));
		logger.info(" Customer  loaded successfully,Customer  details="+customer);
		return customer;
		
	}

	
	public void removeCustomer(int customerID) {
		sessionObj =this.sessionFactory.getCurrentSession();
		 try {sessionObj.beginTransaction();
			Customer customer = (Customer) sessionObj.load(Customer.class, new Integer(customerID));
			
				sessionObj.delete(customer);
			
			sessionObj.getTransaction().commit();
			//logger.info("Admin deleted successfully, admin details="+admin);
		}
		
	    catch (HibernateException e) {
	        e.printStackTrace();
	        sessionObj.getTransaction().rollback();
	    }

}
	/*public List<Reservation>getReservation(Reservation reservation){
		List<Reservation> reservationList=new ArrayList<Reservation>();
		final Query q=sessionFactory.getCurrentSession().createQuery("SELECT CustomerID FROM customer");
		
		return null;
	}*/
	/*public List<Customer> getReservationCustomer(final Reservation reservation) {
	    final List<Customer> customerlist = new ArrayList<Customer>();

	    String select = "SELECT  CustomerID,ReservationID FROM customer JOIN reservation  ON CustomerID=ReservationID";
	    Query query = sessionObj.createQuery(select);
	    query.setParameter("ReservationID", reservation.getReservationId());

	    try {
	    	customerlist .addAll((List<Customer>) query.list());
	    } catch (Exception ex) {
	      System.out.printf("Exception in getReservationCustomer: %s \n", ex.getMessage());
	    }

	    return customerlist;
	  }*/
	public void updateCR(int reservationID, int customerID) {
		sessionObj =this.sessionFactory.openSession();
		sessionObj.beginTransaction();
		Customer customer = (Customer) sessionObj.load(Customer.class, new Integer(customerID));
		Reservation reservation= (Reservation) sessionObj.load(Reservation.class, new Integer(reservationID));
		Set<Reservation>rList= customer.getReservations();
		rList.add(reservation);
		customer.setReservations(rList);
		sessionObj.getTransaction().commit();
		//sessionObj.getTransaction().rollback();
	}
}

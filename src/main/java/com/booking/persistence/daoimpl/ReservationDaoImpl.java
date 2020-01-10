package com.booking.persistence.daoimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.booking.persistence.dao.ReservationDao;
import com.booking.persistence.model.Admin;
import com.booking.persistence.model.Customer;
import com.booking.persistence.model.Reservation;

public class ReservationDaoImpl implements ReservationDao{
	private static final Log logger = LogFactory.getLog(ReservationDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	static Session sessionObj;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	
	public void addReservation(Reservation reservation) {
		sessionObj =this.sessionFactory.openSession();
		sessionObj.beginTransaction();
		sessionObj.persist(reservation);
		sessionObj.getTransaction().commit();
		logger.info("Reservation saved successfully,Reservation Details="+reservation);
	}

	
	public void updateReservation(Reservation reservation) {
		sessionObj =this.sessionFactory.openSession();
		sessionObj.update(reservation);
		logger.info(" Reservation updated successfully,  Reservation  Details="+reservation);
	}

	@SuppressWarnings("unchecked")
	
	public  List<Reservation> listReservations() {
		List<Reservation> reservationsList=null;
		sessionObj=this.sessionFactory.openSession();
		sessionObj.beginTransaction();
		 reservationsList =(List<Reservation>) sessionObj.createCriteria(Reservation.class).list();
		
		return reservationsList;
	}

	
	public Reservation getReservationById(int reservationID) {
		sessionObj =this.sessionFactory.openSession();		
		Reservation reservation = (Reservation) sessionObj.load(Reservation.class, new Integer(reservationID));
		logger.info(" Reservation  loaded successfully,Reservation  details="+reservation);
		return reservation;
	}

	
	public void removeReservation(int reservationID) {
		sessionObj =this.sessionFactory.getCurrentSession();
		 try {sessionObj.beginTransaction();
		Reservation reservation= (Reservation) sessionObj.load(Reservation.class, new Integer(reservationID));
		
			sessionObj.delete(reservation);
		
		sessionObj.getTransaction().commit();
		//logger.info("Admin deleted successfully, admin details="+admin);
	}
	
   catch (HibernateException e) {
       e.printStackTrace();
       sessionObj.getTransaction().rollback();
   }
		
}
	 /*public void updateRC(int reservationID, int customerID) {
			sessionObj =this.sessionFactory.openSession();
			sessionObj.beginTransaction();
			Customer customer = (Customer) sessionObj.load(Customer.class, new Integer(customerID));
			Reservation reservation= (Reservation) sessionObj.load(Reservation.class, new Integer(reservationID));
			Set<Customer>cList= new HashSet<Customer>();
			cList.add(customer);
			reservation.setCustomers(cList);
			sessionObj.getTransaction().commit();
			//sessionObj.getTransaction().rollback();
		}*/
}

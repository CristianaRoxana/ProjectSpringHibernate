package com.booking;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.booking.persistence.dao.AdminDao;
import com.booking.persistence.dao.AgentDao;
import com.booking.persistence.dao.CustomerDao;
import com.booking.persistence.dao.ReservationDao;
import com.booking.persistence.daoimpl.AdminDaoImpl;
import com.booking.persistence.model.Admin;
import com.booking.persistence.model.Agent;
import com.booking.persistence.model.Customer;
import com.booking.persistence.model.Reservation;
import com.booking.persistence.util.HibernateUtil;


public class AppMain {
	public static void main(String[] args) {

	/*	System.out.println("Hibernate one to many, many to many ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Agent agent = new Agent();
		agent.setNameAgent("agent");
		agent.setDetailsAgent("agentDetails");
		agent.setIdAdmin(1);
		session.save(agent);

		Admin admin = new Admin();
		admin.setEmailAdmin("admin@yahoo.com");
		admin.setPasswordAdmin("pass");
		agent.getAdmins().add(admin);
		session.save(admin);
		*/
		ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("spring.xml");
	 	AdminDao admDao= context.getBean(AdminDao.class);
	
		AgentDao agDao= context.getBean(AgentDao.class);

		Admin admin = new Admin();
		Agent agent = new Agent();
		Agent agent1 = new Agent();
		/*admin.setEmailAdmin("admin3@yahoo.com");
		admin.setPasswordAdmin("pass");
		admDao.addAdmin(admin);
		
		/*Admin i=admDao.getAdminById(admin.getIdAdmin());
		System.out.println("by ID::::::"+i);*/
		
	/*	List<Admin> adminiList = admDao.listAdmins();
		for(Admin a : adminiList){
			System.out.println(" Admin  List:"+a);
		}
		//admDao.removeAdmin(10);
		//admDao.removeAdmin(11);
		//admDao.removeAdmin(12);
		//admDao.removeAdmin(3);
		agent.setNameAgent("agent");
		agent.setDetailsAgent("agentDetails");
		agent.setNameAgent("numeAgent");
		
		agDao.addAgent(agent);
		
	//-------------------------	agDao.updateAgent(agent);
		
		List<Agent> agentiList = agDao.listAgents();
		for(Agent a : agentiList){
			System.out.println(" Agent  List:"+a.toString());
		}
		
	//	agDao.removeAgent(10);
		//agDao.removeAgent(11);
		//agDao.updateA(1);
		/*context.close();
		*/
		//****************      ONE TO MANY      ***********************
		admin.setEmailAdmin("admin@yahoo.com");
		admin.setPasswordAdmin("pass");
		admDao.addAdmin(admin);
		
		agent.setNameAgent("agent");
		agent.setDetailsAgent("agentDetails");
		agent.setNameAgent("numeAgent");
		
		agent1.setNameAgent("agent1");
		agent1.setDetailsAgent("agentDetails1");
		agent1.setNameAgent("numeAgent1");
		
		agent.setAdmin(admin);
		agent1.setAdmin(admin);
		agDao.addAgent(agent);
		agDao.addAgent(agent1);
		
		List<Admin> adminiList = admDao.listAdmins();
		for(Admin a : adminiList){
			System.out.println(" Admin  List:"+a);
		}

		List<Agent> agentiList = agDao.listAgents();
		for(Agent a : agentiList){
			System.out.println(" Agent  List:"+a);
		}
		
		//****************      MANY TO MANY      ***********************
		CustomerDao cDao= context.getBean(CustomerDao.class);
		ReservationDao rDao= context.getBean(ReservationDao.class);
		
		Customer customer = new Customer();
		Reservation reservation = new Reservation();
		
		customer.setCustomerEmail("customeremail@yahoo.com");
		customer.setCustomerFirstName("customerFirstName");
		customer.setCustomerLastName("customerLastName");
		customer.setCustomerPhone("09281983");
		cDao.addCustomer(customer);

		Reservation reserv1 = new Reservation(10, "10/12/2010", "10/01/2011", "Budapesta", "primatarezervare");
		Reservation reserv2 = new Reservation(12, "11/12/2010", "11/01/2011", "Romania", "douatarezervare");
		
		Set<Reservation> reservations = new HashSet<Reservation>();
		reservations.add(reserv1);
		reservations.add(reserv2);
		
		Customer customer1=new Customer("customerFirstName"," customerLastName", "098879778", "customeremail@yahoo.com");
				
		Set<Customer> customers = new HashSet<Customer>();
		customers.add(customer1);
		
		cDao.addCustomer(customer1);
		//cDao.addCustomer(customer1);
		rDao.addReservation(reserv1);
		
		
		
		customer.setReservations(reservations);
		reservation.setCustomers(customers);
		
		/*List<Reservation> rList = rDao.listReservations();
		for(Reservation r : rList){
			System.out.println(" Reserv List:"+r);
		}
		List<Customer> cList = cDao.listCustomers();
		for(Customer c : cList){
			System.out.println(" Customer List:"+c);
		}*/
		cDao.updateCustomer(customer);
		//final List<Customer> customerList = cDao.getReservationCustomer(reserv1);
		//cDao.removeCustomer(2);
		cDao.updateCR(1,2);
		//cDao.updateCR(4,2);
		//rDao.updateRC(1,1);
		context.close();
		
		System.out.println("Done");
		
	}
}

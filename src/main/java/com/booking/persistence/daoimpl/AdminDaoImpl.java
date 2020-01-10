package com.booking.persistence.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.booking.persistence.dao.AdminDao;
import com.booking.persistence.model.Admin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AdminDaoImpl implements AdminDao{
	private static final Log logger = LogFactory.getLog(AdminDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	static Session sessionObj;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public void addAdmin(Admin admin) {
		sessionObj =this.sessionFactory.openSession();
		sessionObj.beginTransaction();
		sessionObj.persist(admin);
		sessionObj.getTransaction().commit();
		logger.info("Admin saved successfully, Admin Details="+admin);
	}

	public void updateAdmin(Admin admin) {
		sessionObj =this.sessionFactory.openSession();
		sessionObj.update(admin);
		logger.info(" Admin  updated successfully,  Admin  Details="+admin);
	}

	@SuppressWarnings("unchecked")

	public  List<Admin> listAdmins() {
		List<Admin>adminList=null;
		sessionObj=this.sessionFactory.openSession();
		sessionObj.beginTransaction();
		adminList=(List<Admin>)sessionObj.createCriteria(Admin.class).list();
		return adminList;
	}

	
	public Admin getAdminById(int adminID) {
		sessionObj =this.sessionFactory.openSession();
		Admin admin = (Admin) sessionObj.load(Admin.class, new Integer(adminID));
		logger.info(" Admin  loaded successfully, Admin  details="+admin);
		return admin;
	}

	
	public void removeAdmin(int adminID) {
		sessionObj =this.sessionFactory.getCurrentSession();
		 try {sessionObj.beginTransaction();
		Admin admin = (Admin) sessionObj.load(Admin.class, new Integer(adminID));
		
			sessionObj.delete(admin);
		
		sessionObj.getTransaction().commit();
		//logger.info("Admin deleted successfully, admin details="+admin);
	}
	
    catch (HibernateException e) {
        e.printStackTrace();
        sessionObj.getTransaction().rollback();
    }
}
}
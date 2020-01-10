package com.booking.persistence.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.booking.persistence.dao.AgentDao;
import com.booking.persistence.model.Admin;
import com.booking.persistence.model.Agent;
import com.booking.persistence.model.Customer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AgentDaoImpl implements AgentDao {
	private static final Log logger = LogFactory.getLog(AgentDaoImpl.class);
	@Autowired
	static Session sessionObj;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void addAgent(Agent agent) {
		sessionObj =this.sessionFactory.openSession();
		sessionObj.beginTransaction();
		sessionObj.persist(agent);
		sessionObj.getTransaction().commit();
		logger.info("Agent saved successfully, Agent Details=" + agent);
	}

	public void updateAgent(Agent agent) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(agent);
		logger.info("Agent updated successfully, Agent Details=" + agent);
	}

	@SuppressWarnings("unchecked")

	public List<Agent> listAgents() {
		List<Agent>agentList=null;
		sessionObj=this.sessionFactory.openSession();
		sessionObj.beginTransaction();
		agentList=(List<Agent>)sessionObj.createCriteria(Agent.class).list();
		return agentList;
	}

	public Agent getAgentById(int agentID) {
		sessionObj=this.sessionFactory.openSession();
		Agent agent = (Agent) sessionObj.load(Agent.class, new Integer(agentID));
		logger.info("Agent loaded successfully, Agent details=" + agent);
		return agent;
	}

	public void removeAgent(int agentID) {
		sessionObj=this.sessionFactory.openSession();
		try {
			sessionObj.beginTransaction();
			Agent agent = (Agent)sessionObj.load(Agent.class, new Integer(agentID));
			
			sessionObj.delete(agent);
		}catch (HibernateException e) {
			e.printStackTrace();
			sessionObj.getTransaction().rollback();
			}
		
	}
	/*public void updateA(int AgentID) {
		sessionObj =this.sessionFactory.openSession();
		sessionObj.beginTransaction();
		Agent a = (Agent) sessionObj.load(Agent.class, new Integer(AgentID));
		int adminID=a.getIdAdmin();
		Admin admin = (Admin) sessionObj.load(Admin.class, new Integer(adminID));
		a.setIdAdmin(3);
		sessionObj.getTransaction().commit();
	}*/
}

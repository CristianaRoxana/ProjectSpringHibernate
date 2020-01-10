package com.booking.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="admin")
public class Admin implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Set<Agent> agents = new HashSet<Agent>(0);
	private int id;
	private String email;
	private String password;
    public Admin() {
    	super();
    }
    public Admin(int idAdmin,String emailAdmin,String pass, Set<Agent> agents) {
    	this.id=idAdmin;
    	this.email=emailAdmin;
    	this.agents=agents;
    	this.password=pass;
    }
    
    @Id
    @Column(name="AdminId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getIdAdmin() {
        return id;
    }
 
    public void setIdAdmin(int id) {
        this.id = id;
    }
 
    
    @Column(name="Email")
    public String getEmailAdmin() {
        return email;
    }
 
    public void setEmailAdmin(String email) {
        this.email = email;
    }
 
    
    @Column(name="Password")
    public String getPasswordAdmin() {
        return password;
    }
 
    public void setPasswordAdmin(String pass) {
        this.password = pass;
    }
    
   
  @OneToMany(fetch=FetchType.LAZY, mappedBy="admin")
  public Set<Agent> getAgents() {
		return this.agents;
	}

	public void setAgents(Set<Agent> agents) {
		this.agents = agents;
	}
  
    @Override
    public String toString() {
        return "Admins Details= Id: " + this.id + ", Email: " + this.email + ", Password: " + this.password;
    }
}


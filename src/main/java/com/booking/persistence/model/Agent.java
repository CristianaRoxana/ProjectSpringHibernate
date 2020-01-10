package com.booking.persistence.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "agent")

public class Agent implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int idAgent;
	private String agentDetails;
	private String agentName;
	private Admin admin;
	
	public Agent() {
		super();
	}
	public Agent(Admin admin, int idAgent, String agentDetails,String agentName) {
		this.admin=admin;
		this.agentDetails=agentDetails;
		this.agentName=agentName;
		this.idAgent=idAgent;
	}

	@Id
	@Column(name = "AgentId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(int idAgent) {
		this.idAgent = idAgent;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AdminId", nullable = false)
	public Admin getAdmin() {
		return this.admin;
	}
	public void  setAdmin(Admin admin) {
		 this.admin=admin;
	}
	@Column(name = "AgentName")

	public String getNameAgent() {
		return agentName;
	}

	public void setNameAgent(String nameAgent) {
		this.agentName = nameAgent;
	}
	@Column(name = "AgentDetails")

	public String getDetailsAgent() {
		return agentDetails;
	}

	public void setDetailsAgent(String agentDetails) {
		this.agentDetails = agentDetails;
	}
	@Override
	public String toString() {
		return "Agent [idAgent=" + idAgent + ", agentDetails=" + agentDetails + ", agentName=" + agentName + ", admin="
				+ admin + "]";
	}
}
package com.booking.persistence.dao;

import java.util.List;
import com.booking.persistence.model.Agent;

public interface AgentDao {
	public void addAgent(Agent agent);
	public void updateAgent(Agent agent);
	public List<Agent> listAgents();
	public Agent getAgentById(int agentID);
	public void removeAgent(int agentID);
	//public void updateA(int AgentID);
}

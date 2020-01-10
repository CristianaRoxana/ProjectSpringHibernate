package com.booking.persistence.dao;

import java.util.List;


import com.booking.persistence.model.Admin;

public interface AdminDao {
	public void addAdmin(Admin admin);
	public void updateAdmin(Admin admin);
	public List<Admin> listAdmins();
	public Admin getAdminById(int adminID);
	public void removeAdmin(int adminID);
}

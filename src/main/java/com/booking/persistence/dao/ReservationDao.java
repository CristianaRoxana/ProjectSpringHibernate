package com.booking.persistence.dao;

import java.util.List;
import com.booking.persistence.model.Reservation;

public interface ReservationDao {
	public void addReservation(Reservation reservation);
	public void updateReservation(Reservation reservation);
	public List<Reservation> listReservations();
	public Reservation getReservationById(int reservationID);
	public void removeReservation(int reservationID);
	//public void updateRC(int reservationID, int customerID) ;
}

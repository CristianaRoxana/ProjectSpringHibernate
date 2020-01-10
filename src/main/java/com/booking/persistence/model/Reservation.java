package com.booking.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "reservation", catalog = "internship")
public class Reservation implements Serializable {
	
	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", reservationPrice=" + reservationPrice
				+ ", reservationDateStart=" + reservationDateStart + ", reservationDateEnd=" + reservationDateEnd
				+ ", reservationLocation=" + reservationLocation + ", reservationDescription=" + reservationDescription
				+ ", customers=" + customers + "]";
	}

	private int reservationId;
	private int reservationPrice;
	private String reservationDateStart;
	private String reservationDateEnd;
	private String reservationLocation;
	private String reservationDescription;
	private Set<Customer> customers= new HashSet<Customer>(0);
	
	public Reservation() {
	}
	public Reservation(int reservationPrice,String reservationDateStart,String reservationDateEnd, String reservationLocation,String reservationDescription) {
		this.reservationPrice = reservationPrice;
		this.reservationDateStart = reservationDateStart;
		this.reservationDateEnd = reservationDateEnd;
		this.reservationLocation = reservationLocation;
		this.reservationDescription = reservationDescription;
		
	}
	public Reservation(int reservationPrice,String reservationDateStart,String reservationDateEnd, String reservationLocation,String reservationDescription,Set<Customer>customers) {
		this.reservationPrice = reservationPrice;
		this.reservationDateStart = reservationDateStart;
		this.reservationDateEnd = reservationDateEnd;
		this.reservationLocation = reservationLocation;
		this.reservationDescription = reservationDescription;
		this.customers=customers;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ReservationID", unique = true, nullable = false)
	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	@Column(name = "ReservationPrice", nullable = false)
	public int getReservationPrice() {
		return reservationPrice;
	}

	public void setReservationPrice(int reservationPrice) {
		this.reservationPrice = reservationPrice;
	}
	@Column(name = "ReservationDateStart")
	public String getReservationDateStart() {
		return reservationDateStart;
	}

	public void setReservationDateStart(String reservationDateStart) {
		this.reservationDateStart = reservationDateStart;
	}
	@Column(name = "ReservationDateEnd")
	public String getReservationDateEnd() {
		return reservationDateEnd;
	}

	public void setReservationDateEnd(String reservationDateEnd) {
		this.reservationDateEnd = reservationDateEnd;
	}
	@Column(name = "ReservationLocation")
	public String getReservationLocation() {
		return reservationLocation;
	}

	public void setReservationLocation(String reservationLocation) {
		this.reservationLocation = reservationLocation;
	}
	@Column(name = "ReservationDescription")
	public String getReservationDescription() {
		return reservationDescription;
	}

	public void setReservationDescription(String reservationDescription) {
		this.reservationDescription = reservationDescription;
	}
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "reservations")
	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}


	
}

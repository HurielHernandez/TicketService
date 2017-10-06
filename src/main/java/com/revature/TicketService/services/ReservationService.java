package com.revature.TicketService.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.TicketService.models.SeatHold;

public class ReservationService
{
	private static ReservationService instance = null;

	private List<SeatHold>reservations = new ArrayList<SeatHold>();

	private ReservationService(){}

	public static ReservationService getInstance()
	{
		if (instance == null)
		{
			instance = new ReservationService();
		}
		return instance;
	}
	
	
	public List<SeatHold> getReservations()
	{
		return reservations;
	}

	public void setReservations(List<SeatHold> reservations)
	{
		this.reservations = reservations;
	}
	
	public void addReservation(SeatHold seatHold) 
	{
		this.reservations.add(seatHold);
	}

	@Override
	public String toString()
	{
		return "Reservations [reservations=" + reservations + "]";
	}

	

}

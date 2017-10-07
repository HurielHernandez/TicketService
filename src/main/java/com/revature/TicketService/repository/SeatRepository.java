package com.revature.TicketService.repository;

import java.util.List;

import com.revature.TicketService.models.Seat;
import com.revature.TicketService.models.SeatHold;


 /**
 * Seat Repository interface that can be used to replace mock Repository to access functional database.
 * 
 * Seat Repository assumes a data store exists with the entries of available seats for the current venue.
 */
public interface SeatRepository
{
	public List<Seat> findAll();

	public List<Seat> findSeats(int numberOfSeats);
	
	public void add(Seat seat);
	
	public void remove(Seat seat);
}

package com.revature.TicketService.repository;

import java.util.List;

import com.revature.TicketService.models.Seat;
import com.revature.TicketService.models.SeatHold;

public interface SeatRepository
{
	public List<Seat> findAll();

	List<Seat> findSeats(int numberOfSeats);
	
	public void add(Seat seat);
	
	void remove(Seat seat);
}

package com.revature.TicketService.services;

import com.revature.TicketService.models.SeatHold;

public interface TicketService
{
	int numSeatsAvailable();
	
	SeatHold findAndHoldSeats(int numSeats, String customerEmail);
	
	String reserveSeats(int seatHoldId, String customerEmail);
}

package com.revature.TicketService.repository;

import java.util.Date;
import java.util.List;

import com.revature.TicketService.models.SeatHold;

/**
* SeatHold Repository interface that can be used to replace mock Repository to access functional database.
* 
* SeatHold Repository assumes a data store exists with the entries of Seats held temporary for the current venue.
*/
public interface SeatHoldRespository
{
	public List<SeatHold> all();
	
	public List<SeatHold> findByReservedOnLessThan(Date expirationTime);
	
	public SeatHold findBySeatHoldIdAndEmail(int seatHoldId, String customerEmail);
	
	public void add(SeatHold seatHold);
	
	public void remove(SeatHold seatHold);
}

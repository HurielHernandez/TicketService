package com.revature.TicketService.repository;

import java.util.Date;
import java.util.List;

import com.revature.TicketService.models.SeatHold;

public interface SeatHoldRespository
{
	public List<SeatHold> all();
	
	public List<SeatHold> findByReservedOnGreaterThan(Date expirationDate);
	
	public SeatHold findBySeatHoldIdAndEmail(int seatHoldId, String customerEmail);
	
	public void add(SeatHold seatHold);
	
	public void remove(SeatHold seatHold);
}

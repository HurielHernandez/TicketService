package com.revature.TicketService.repository;

import java.util.Date;
import java.util.List;

import com.revature.TicketService.models.SeatHold;

public interface SeatHoldRespository
{
	public void addSeatHold(SeatHold seatHold);
	
	public List<SeatHold> all();
	
	public List<SeatHold> getExpiredSeatHold(Date currentDate);
	
	public SeatHold removeSeatHold(int seatHoldId, String customerEmail);
}

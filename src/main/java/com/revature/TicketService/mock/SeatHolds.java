package com.revature.TicketService.mock;

import java.util.ArrayList;
import java.util.List;

import com.revature.TicketService.models.SeatHold;

public class SeatHolds
{

	private static SeatHolds instance = null;
	
	private List<SeatHold>seatHolds = new ArrayList<SeatHold>();
	
	private SeatHolds(){}

	public static SeatHolds getInstance()
	{
		if (instance == null)
		{
			instance = new SeatHolds();
		}
		return instance;
	}

	public List<SeatHold> getSeatHolds()
	{
		return seatHolds;
	}

	public void setSeatHolds(List<SeatHold> seatHolds)
	{
		this.seatHolds = seatHolds;
	}
	
	public void addSeatHold(SeatHold seatHold) throws Exception
	{
		if(this.seatHolds.contains(seatHold))
			throw new Exception("Seat Hold already added.");
		
		this.seatHolds.add(seatHold);
	}
	
	public SeatHold removeSeatHold(int seatHoldId, String customerEmail) throws Exception
	{
		 try {
		      SeatHold seatHold = this.seatHolds.stream()
		    		  					.filter(h -> h.getCustomerEmail().equals(customerEmail) && h.getSeatHoldId() == seatHoldId)
							    		.findFirst()
							    		.get();
		      
		      this.seatHolds.remove(seatHold);
		      return seatHold;
		 }
		 catch(NullPointerException e)
		 {
			 throw new Exception("SeatHold not found Exception");
		 }
	}
}

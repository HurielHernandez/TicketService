package com.revature.TicketService.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.TicketService.models.SeatHold;

public class TemporaryHoldService
{
	private static TemporaryHoldService instance = null;
	
	private List<SeatHold>temporaryHolds = new ArrayList<SeatHold>();
	
	private TemporaryHoldService(){}

	public static TemporaryHoldService getInstance()
	{
		if (instance == null)
		{
			instance = new TemporaryHoldService();
		}
		return instance;
	}

	public List<SeatHold> getTemporaryHolds()
	{
		return temporaryHolds;
	}

	public void setTemporaryHolds(List<SeatHold> temporaryHolds)
	{
		this.temporaryHolds = temporaryHolds;
	}
	
	public void addTemporarySeatHold(SeatHold seatHold) throws Exception
	{
		if(this.temporaryHolds.contains(seatHold))
			throw new Exception("Seat Hold already added.");
		
		this.temporaryHolds.add(seatHold);
	}
	
	public SeatHold removeTemporaryHold(int seatHoldId, String customerEmail) throws Exception
	{
		
		 try {
		      SeatHold seatHold = this.temporaryHolds.stream()
		    		  					.filter(h -> h.getCustomerEmail().equals(customerEmail) && h.getSeatHoldId() == seatHoldId)
							    		.findFirst()
							    		.get();
		      
		      this.temporaryHolds.remove(seatHold);
		      return seatHold;
		 }
		 catch(NullPointerException e)
		 {
			 throw new Exception("SeatHold not found Exception");
		 }
	      
	}

	@Override
	public String toString()
	{
		return "TemporaryHolds [temporaryHolds=" + temporaryHolds + "]";
	}
	
	
	
}

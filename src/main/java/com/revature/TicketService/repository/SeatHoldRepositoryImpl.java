package com.revature.TicketService.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.TicketService.mock.SeatHolds;
import com.revature.TicketService.models.SeatHold;

public class SeatHoldRepositoryImpl implements SeatHoldRespository
{

	@Override
	public List<SeatHold> all()
	{
		return SeatHolds.getInstance().getSeatHolds();
	}
	
	@Override
	public List<SeatHold> findByReservedOnGreaterThan(Date expirationTime)
	{
		try {
			return  SeatHolds.getInstance().getSeatHolds().stream()
				.filter((SeatHold s) -> s.getRerservedOn().compareTo(expirationTime) > 0)
				.collect(Collectors.toList());
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public void add(SeatHold seatHold)
	{
		try{
			SeatHolds.getInstance().addSeatHold(seatHold);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public SeatHold findBySeatHoldIdAndEmail(int seatHoldId, String customerEmail)
	{
		try {
		      SeatHold seatHold = SeatHolds.getInstance().getSeatHolds().stream()
		    		  					.filter(h -> h.getCustomerEmail().equals(customerEmail) && h.getSeatHoldId() == seatHoldId)
							    		.findFirst()
							    		.get();
		      
		     
		      return seatHold;
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		
	}

	@Override
	public void remove(SeatHold seatHold)
	{
		 SeatHolds.getInstance().getSeatHolds().remove(seatHold);
	}

}

package com.revature.TicketService.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.TicketService.mock.SeatHolds;
import com.revature.TicketService.models.SeatHold;

public class SeatHoldRepositoryImpl implements SeatHoldRespository
{

	@Override
	public void addSeatHold(SeatHold seatHold)
	{
		try{
			SeatHolds.getInstance().addSeatHold(seatHold);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<SeatHold> all()
	{
		return SeatHolds.getInstance().getSeatHolds();
	}

	@Override
	public List<SeatHold> getExpiredSeatHold(Date experiationTime)
	{
		try {
		return  SeatHolds.getInstance().getSeatHolds().stream()
			.filter((SeatHold s) -> s.getRerservedOn().compareTo(experiationTime) > 0)
			.collect(Collectors.toList());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public SeatHold removeSeatHold(int seatHoldId, String customerEmail)
	{
			try {
		      SeatHold seatHold = SeatHolds.getInstance().getSeatHolds().stream()
		    		  					.filter(h -> h.getCustomerEmail().equals(customerEmail) && h.getSeatHoldId() == seatHoldId)
							    		.findFirst()
							    		.get();
		      
		      SeatHolds.getInstance().getSeatHolds().remove(seatHold);
		      return seatHold;
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		    
		
	}
}

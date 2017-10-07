package com.revature.TicketService.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.revature.TicketService.App;
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
				.filter((SeatHold seatHold) -> seatHold.getRerservedOn().compareTo(expirationTime) > 0)
				.collect(Collectors.toList());
			}catch(Exception e) {
				App.logger.error(e);
				return null;
			}
	}

	@Override
	public void add(SeatHold seatHold)
	{
		try{
			SeatHolds.getInstance().addSeatHold(seatHold);
		} catch (Exception e){
			App.logger.error(e);
		}
	}
	
	@Override
	public SeatHold findBySeatHoldIdAndEmail(int seatHoldId, String customerEmail)
	{
		try {
		      SeatHold seatHold = SeatHolds.getInstance().getSeatHolds().stream()
		    		  					.filter(seathold -> seathold.getCustomerEmail().equals(customerEmail) && seathold.getSeatHoldId() == seatHoldId)
							    		.findFirst()
							    		.get();
		      return seatHold;
			}catch(Exception e)
			{
				App.logger.error(e);
				return null;
			}
	}

	@Override
	public void remove(SeatHold seatHold)
	{
		 SeatHolds.getInstance().getSeatHolds().remove(seatHold);
	}
}

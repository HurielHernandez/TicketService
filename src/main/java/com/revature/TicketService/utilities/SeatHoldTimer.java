package com.revature.TicketService.utilities;

import java.util.List;
import java.util.Calendar;
import java.util.Date;

import com.revature.TicketService.mock.Seats;
import com.revature.TicketService.models.SeatHold;
import com.revature.TicketService.repository.SeatHoldRepositoryImpl;
import com.revature.TicketService.repository.SeatHoldRespository;

public class SeatHoldTimer
{
	public final static int SEAT_HOLD_TIMEOUT_IN_SECONDS = 1800; //30 min
	
	static SeatHoldRespository seatHoldRepository = new SeatHoldRepositoryImpl();
	
	public static void findAndReleaseExpiredSeatHolds()
	{
		if(seatHoldRepository.all().size() == 0)
			return;
		
		Calendar now = Calendar.getInstance();
	    now.add(Calendar.SECOND, SEAT_HOLD_TIMEOUT_IN_SECONDS);
	    Date expirationTime = now.getTime();
		
		//If reservedOn is greater than expirationTime remove
	   List<SeatHold> expired = seatHoldRepository.getExpiredSeatHold(expirationTime);
	   
	   SeatHoldRepositoryImpl seatHoldRepository = new SeatHoldRepositoryImpl();
	   
	   final int totalSeatsToRelease = (int) expired.stream()
			   										.map(s -> s.getNumberOfSeatsReserved())
			   										.count();
	   
	   expired.forEach(e -> seatHoldRepository.removeSeatHold(e.getSeatHoldId(), e.getCustomerEmail()));
	   
//	   Seats.getInstance().ad
	   
	}
}

package com.revature.TicketService.utilities;

import java.util.List;
import java.util.Calendar;
import java.util.Date;

import com.revature.TicketService.mock.Seats;
import com.revature.TicketService.models.SeatHold;
import com.revature.TicketService.repository.SeatHoldRepositoryImpl;
import com.revature.TicketService.repository.SeatHoldRespository;
import com.revature.TicketService.repository.SeatRepository;
import com.revature.TicketService.repository.SeatRepositoryImpl;

public class SeatHoldTimer
{
	public final static int SEAT_HOLD_TIMEOUT_IN_SECONDS = 1800; //30 min
	
	static SeatHoldRespository seatHoldRepository = new SeatHoldRepositoryImpl();
	static SeatRepository seatRepository = new SeatRepositoryImpl();
	
	public static void findAndReleaseExpiredSeatHolds()
	{
		if(seatHoldRepository.all().size() == 0)
			return;
		
		Calendar now = Calendar.getInstance();
	    now.add(Calendar.SECOND, SEAT_HOLD_TIMEOUT_IN_SECONDS);
	    Date expirationTime = now.getTime();
		
		//If reservedOn is greater than expirationTime remove
	   List<SeatHold> expired = seatHoldRepository.findByReservedOnGreaterThan(expirationTime);
	   
	   SeatHoldRepositoryImpl seatHoldRepository = new SeatHoldRepositoryImpl();
	   
	   expired.forEach(e -> e.getSeats().forEach(s -> seatRepository.add(s)));
	   expired.forEach(e -> seatHoldRepository.remove(e));
	   
	}
}

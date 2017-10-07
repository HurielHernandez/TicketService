package com.revature.TicketService.utilities;

import java.util.List;
import java.util.Calendar;
import java.util.Date;

import com.revature.TicketService.models.SeatHold;
import com.revature.TicketService.repository.SeatHoldRepositoryImpl;
import com.revature.TicketService.repository.SeatHoldRespository;
import com.revature.TicketService.repository.SeatRepository;
import com.revature.TicketService.repository.SeatRepositoryImpl;

/**
 * Utility class that is used to find and remove expired SeatHolds.
 */
public class SeatHoldTimer
{
	/*
	* Time in seconds that a SeatHold can be held before it expires.
	* Time in seconds defaults to 1800 seconds or 30 minutes.
	*/
	public final static int SEAT_HOLD_TIMEOUT_IN_SECONDS = 1800;
	
	static SeatHoldRespository seatHoldRepository = new SeatHoldRepositoryImpl();
	static SeatRepository seatRepository = new SeatRepositoryImpl();
	
	/**
	* Find and release expired SeatHolds held in the SeatHoldRepository.
	*
	* Calculates expiration time by getting current time and adding SEAT_HOLD_TIMEOUT_IN_SECONDS
	* to the current time.  The SEAT_HOLD_TIMEOUT_IN_SECONDS represents the time in seconds that 
	* each SeatHold is allowed to be held before being released back.  
	*/
	public static void findAndReleaseExpiredSeatHolds()
	{
		if(seatHoldRepository.all().size() == 0)
			return;
		
	   /*
	   * Calculate expiration time by subtracting SEAT_HOLD_TIMEOUT_IN_SECONDS from current time.
	   */
	   Calendar now = Calendar.getInstance();
	   now.add(Calendar.SECOND, -SEAT_HOLD_TIMEOUT_IN_SECONDS);
	   Date expirationTime = now.getTime();
	   
	   /*
 	   * Find and retrieve expired SeatHolds.
 	   */
	   List<SeatHold> expired = seatHoldRepository.findByReservedOnLessThan(expirationTime);
	   
	   /*
	   * Add individuals seats from SeatHold back to SeatRepository.
	   */
	   expired.forEach(expiredSeatHold -> expiredSeatHold.getSeats().forEach(seat -> seatRepository.add(seat)));
	  
	   /*
	   * Remove expired SeatHolds from SeatHoldRepository.
	   */
	   expired.forEach(expiredSeatHold -> seatHoldRepository.remove(expiredSeatHold));
	}
}

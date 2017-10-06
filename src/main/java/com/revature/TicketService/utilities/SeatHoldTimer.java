package com.revature.TicketService.utilities;

import java.util.Calendar;
import java.util.Date;

import com.revature.TicketService.models.SeatHold;
import com.revature.TicketService.models.Seats;
import com.revature.TicketService.services.TemporaryHoldService;

public class SeatHoldTimer
{
	public static void findAndRemoveExpiredSeatHolds()
	{
		if(TemporaryHoldService.getInstance().getTemporaryHolds().size() == 0)
			return;
		
		Date experiationTime;
		
		Calendar now = Calendar.getInstance();
		
	    now.add(Calendar.SECOND, Seats.getInstance().SEAT_HOLD_TIMEOUT_IN_SECONDS);
	    experiationTime = now.getTime();
		
		//If reservedOn is greater than expirationTime remove
		TemporaryHoldService.getInstance().getTemporaryHolds()
			.removeIf((SeatHold s) -> s.getRerservedOn().compareTo(experiationTime) > 0);
			
	}
}

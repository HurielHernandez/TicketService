package com.revature.TicketService.services;

import com.revature.TicketService.models.SeatHold;
import com.revature.TicketService.models.Seats;
import com.revature.TicketService.utilities.SeatHoldTimer;

public class TicketServiceImpl implements TicketService
{
	public int numSeatsAvailable()
	{
		return Seats.getInstance().getAvailableSeats();
	}

	public SeatHold findAndHoldSeats(int numberOfSeatsToReserve, String customerEmail)
	{
		SeatHoldTimer.findAndRemoveExpiredSeatHolds();
		
		try{
			return new SeatHold( numberOfSeatsToReserve, customerEmail);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	public String reserveSeats(int seatHoldId, String customerEmail)
	{
		SeatHoldTimer.findAndRemoveExpiredSeatHolds();
		
		try {
			SeatHold seatHold = TemporaryHoldService.getInstance().removeTemporaryHold(seatHoldId, customerEmail);
			ReservationService.getInstance().addReservation(seatHold);
			
			return String.format("%s", seatHold.getSeatHoldId());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

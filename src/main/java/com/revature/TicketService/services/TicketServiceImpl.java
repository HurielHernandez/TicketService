package com.revature.TicketService.services;

import com.revature.TicketService.models.SeatHold;
import com.revature.TicketService.repository.SeatHoldRepositoryImpl;
import com.revature.TicketService.repository.SeatHoldRespository;
import com.revature.TicketService.repository.SeatRepository;
import com.revature.TicketService.repository.SeatRepositoryImpl;
import com.revature.TicketService.utilities.SeatHoldTimer;

public class TicketServiceImpl implements TicketService
{
	public int numSeatsAvailable()
	{
		SeatRepository seatRepository = new SeatRepositoryImpl();
		
		return seatRepository.getAvailableSeats();
	}

	public SeatHold findAndHoldSeats(int numberOfSeatsToReserve, String customerEmail)
	{
		SeatRepository seatRepository = new SeatRepositoryImpl();
		SeatHoldRespository seatHoldRepository = new SeatHoldRepositoryImpl();
		
		SeatHoldTimer.findAndReleaseExpiredSeatHolds();
		
		try{
			int seatsReserved = seatRepository.findAndRemove(numberOfSeatsToReserve);
			SeatHold seatHold = new SeatHold(seatsReserved, customerEmail);
			seatHoldRepository.addSeatHold(seatHold);	
			System.out.println(seatHoldRepository.all());
			return seatHold;
			
		} catch (Exception e){
			e.printStackTrace();
			return null;	
		}
	}

	public String reserveSeats(int seatHoldId, String customerEmail)
	{
		SeatHoldRespository seatHoldRepository = new SeatHoldRepositoryImpl();

		SeatHoldTimer.findAndReleaseExpiredSeatHolds();
		
		try {
			SeatHold seatHold = seatHoldRepository.removeSeatHold(seatHoldId, customerEmail);
//			ReservationService.getInstance().addReservation(seatHold);
			return String.format("%s", seatHold.getSeatHoldId());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

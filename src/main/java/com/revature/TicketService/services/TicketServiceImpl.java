package com.revature.TicketService.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.revature.TicketService.mock.Seats;
import com.revature.TicketService.models.Seat;
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
		List<Seat> seats = seatRepository.findAll();
		if ( seats == null )
			return 0;
		
		return seats.size();
	}

	public SeatHold findAndHoldSeats(int numberOfSeatsToReserve, String customerEmail)
	{
		SeatRepository seatRepository = new SeatRepositoryImpl();
		SeatHoldRespository seatHoldRepository = new SeatHoldRepositoryImpl();
		
		SeatHoldTimer.findAndReleaseExpiredSeatHolds();
		
		try{
			List<Seat> seatsToHold = seatRepository.findSeats(numberOfSeatsToReserve);
			
			if(seatsToHold.size() < numberOfSeatsToReserve)
				throw new Exception("Not enough Seats Exception");
			
			seatsToHold.stream().forEach(s -> seatRepository.remove(s));
			
			SeatHold seatHold = new SeatHold(seatsToHold, customerEmail);
			seatHoldRepository.add(seatHold);	
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
			SeatHold seatHold = seatHoldRepository.findBySeatHoldIdAndEmail(seatHoldId, customerEmail);
			seatHoldRepository.remove(seatHold);
			
			//TODO : Add Reservation Repository
			return String.format("%s", seatHold.getSeatHoldId());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

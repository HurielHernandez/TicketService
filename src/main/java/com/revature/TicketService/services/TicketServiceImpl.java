package com.revature.TicketService.services;

import java.util.List;
import org.apache.log4j.Logger;

import com.revature.TicketService.models.Seat;
import com.revature.TicketService.models.SeatHold;
import com.revature.TicketService.repository.SeatHoldRepositoryImpl;
import com.revature.TicketService.repository.SeatHoldRespository;
import com.revature.TicketService.repository.SeatRepository;
import com.revature.TicketService.repository.SeatRepositoryImpl;
import com.revature.TicketService.utilities.SeatHoldTimer;

public class TicketServiceImpl implements TicketService
{
	private final static Logger logger = Logger.getLogger(TicketServiceImpl.class.getName());
	
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
			
			seatsToHold.stream().forEach(seat -> seatRepository.remove(seat));
			
			SeatHold seatHold = new SeatHold(seatsToHold, customerEmail);
			seatHoldRepository.add(seatHold);	
			return seatHold;
			
		} catch (Exception e){
			logger.error(e);
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
			logger.error(e);
			return null;
		}
	}
}

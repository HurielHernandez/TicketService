package com.revature.TicketService.repository;

import com.revature.TicketService.mock.Seats;

public class SeatRepositoryImpl implements SeatRepository
{

	@Override
	public int getAvailableSeats()
	{
		return Seats.getInstance().getAvailableSeats();
	}

	@Override
	public int findAndRemove(int numberOfSeatsToReserve) throws Exception
	{
		if (Seats.getInstance().getAvailableSeats() < numberOfSeatsToReserve)
			throw new Exception("Not enough seats found Exception");
		
		Seats.getInstance().reserveSeats(numberOfSeatsToReserve);
		return numberOfSeatsToReserve;
		
	}

}

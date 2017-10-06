package com.revature.TicketService.repository;

public interface SeatRepository
{
	public int getAvailableSeats();

	public int findAndRemove(int numberOfSeatsToReserve) throws Exception;
}

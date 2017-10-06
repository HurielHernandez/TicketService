package com.revature.TicketService.repository;

import java.util.List;
import java.util.stream.Collectors;

import com.revature.TicketService.mock.Seats;
import com.revature.TicketService.models.Seat;

public class SeatRepositoryImpl implements SeatRepository
{
	@Override
	public List<Seat> findAll()
	{
		return Seats.getInstance().getAvailableSeats();
	}
	
	@Override
	public List<Seat> findSeats(int numberOfSeats)
	{
		return Seats.getInstance().getAvailableSeats().stream()
				  .limit(numberOfSeats)
				  .collect(Collectors.toList());
	}
	
	@Override
	public void add(Seat seat)
	{
		Seats.getInstance().add(seat);
	}

	@Override
	public void remove(Seat seat)
	{
		Seats.getInstance().remove(seat);
	}

}

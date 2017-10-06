package com.revature.TicketService.mock;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import com.revature.TicketService.models.Seat;

public class Seats
{
	private final int TOTAL_SEATS = 10;

	private static Seats instance = null;
	
	private PriorityQueue<Seat> seats = new PriorityQueue<Seat>();

	private Seats()
	{
		//Add mock seats
		for(int i  = 0; i < TOTAL_SEATS; i++)
		{
			seats.add(new Seat(i+1));
		}
	}

	public static Seats getInstance()
	{
		if (instance == null)
		{
			instance = new Seats();
		}
		return instance;
	}

	public List<Seat> getAvailableSeats()
	{
		if(this.seats.isEmpty())
			return null;
		
		return this.seats.stream().collect(Collectors.toList());
	}
	
	public void setSeats(int numberOfSeats)
	{
		seats = new PriorityQueue<Seat>();
		for(int i  = 0; i < numberOfSeats; i++)
		{
			seats.add(new Seat(i+1));
		}
	}
	
	public void add(Seat seat)
	{
		seats.add(seat);
	}
	
	public void remove(Seat seat)
	{
		seats.remove(seat);
	}
}
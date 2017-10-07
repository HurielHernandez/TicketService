package com.revature.TicketService.mock;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import com.revature.TicketService.models.Seat;


/**
* Seats is a Singleton class that mocks the data that a SeatRepository can contain.  It is used to instantiate,
* add and remove Seats.  Seats uses a priority queue in order to always have to closest seats to stage
* available first when getting called by the database. 
*/
public class Seats
{
	private final int TOTAL_SEATS = 10_000;

	private static Seats instance = null;
	
	private PriorityQueue<Seat> seats = new PriorityQueue<Seat>();

	private Seats()
	{
		//Add mock seats based on the TOTAL_SEATS
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
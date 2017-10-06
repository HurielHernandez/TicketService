package com.revature.TicketService.mock;

public class Seats
{
	private final int TOTAL_SEATS = 10;

	private static Seats instance = null;
	
	private int availableSeats = TOTAL_SEATS;

	private Seats(){}

	public static Seats getInstance()
	{
		if (instance == null)
		{
			instance = new Seats();
		}
		return instance;
	}

	public int getAvailableSeats()
	{
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats)
	{
		this.availableSeats = availableSeats;
	}

	public void reserveSeats(int numberOfSeatsToReserve) throws Exception 
	{
		if(this.availableSeats < numberOfSeatsToReserve)
			throw new Exception("Not enough seats available Exception");
			
		this.availableSeats -= numberOfSeatsToReserve;
	}
}
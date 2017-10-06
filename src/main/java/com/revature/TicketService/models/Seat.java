package com.revature.TicketService.models;

public class Seat
{
	public int seatNumber;
	
	public Seat(){};
	
	public Seat(int seatNumber)
	{
		super();
		this.seatNumber = seatNumber;
	}

	public int getSeatNumber()
	{
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber)
	{
		this.seatNumber = seatNumber;
	}
}

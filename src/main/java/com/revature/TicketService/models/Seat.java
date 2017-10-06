package com.revature.TicketService.models;

public class Seat implements Comparable<Seat>
{
	public int seatNumber;

	public Seat(){};

	public Seat(int seatNumber)
	{
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

	@Override
	public String toString()
	{
		return "Seat [seatNumber=" + seatNumber + "]";
	}

	@Override
	public int compareTo(Seat o)
	{
		  if(this.seatNumber == o.seatNumber)
			  return 0;
		  
		  return this.seatNumber < o.seatNumber ? -1 : 1;
	}

	
}

package com.revature.TicketService.models;

/**
* Seat model used to represent a Seat that a customer can reserve.  Seat model only contains an integer 
* id that is used to identify a Seat.  More properties can be added in extend functionality such as Seat 
* price.
*/
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

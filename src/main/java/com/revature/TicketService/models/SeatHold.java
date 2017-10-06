package com.revature.TicketService.models;

import java.util.Date;
import java.util.List;

public class SeatHold
{
	private int seatHoldId;
	private List<Seat> seats;
	private String customerEmail;
	private Date rerservedOn;
	
	public SeatHold(List<Seat> seats, String customerEmail) throws Exception
	{
		this.seatHoldId = this.hashCode();
		this.customerEmail = customerEmail;
		this.seats = seats;
		this.rerservedOn = new Date();
	}

	public int getSeatHoldId()
	{
		return seatHoldId;
	}

	public void setSeatHoldId(int seatHoldId)
	{
		this.seatHoldId = seatHoldId;
	}

	public int getNumberOfSeatsReserved()
	{
		return this.seats.size();
	}

	public String getCustomerEmail()
	{
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail)
	{
		this.customerEmail = customerEmail;
	}

	public Date getRerservedOn()
	{
		return rerservedOn;
	}

	public void setRerservedOn(Date rerservedOn)
	{
		this.rerservedOn = rerservedOn;
	}
	
	public List<Seat> getSeats()
	{
		return seats;
	}

	public void setSeats(List<Seat> seats)
	{
		this.seats = seats;
	}

	@Override
	public String toString()
	{
		return "SeatHold [seatHoldId=" + seatHoldId + ", seats=" + seats + ", customerEmail=" + customerEmail
				+ ", rerservedOn=" + rerservedOn + "]";
	}
}

package com.revature.TicketService.models;

import java.util.Date;

public class SeatHold
{
	private int seatHoldId;
	private int numberOfSeatsReserved;
	private String customerEmail;
	private Date rerservedOn;

	public SeatHold(int numberOfSeatsReserved, String customerEmail) throws Exception
	{
		this.numberOfSeatsReserved = numberOfSeatsReserved;
		this.customerEmail = customerEmail;
		this.seatHoldId = this.hashCode();
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
		return numberOfSeatsReserved;
	}

	public void setNumberOfSeatsReserved(int numberOfSeatsReserved)
	{
		this.numberOfSeatsReserved = numberOfSeatsReserved;
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

	@Override
	public String toString()
	{
		return "SeatHold [seatHoldId=" + seatHoldId + ", numberOfSeatsReserved=" + numberOfSeatsReserved
				+ ", customerEmail=" + customerEmail + ", rerservedOn=" + rerservedOn + "]";
	}

}

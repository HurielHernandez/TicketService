package com.revature.TicketService.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.TicketService.models.SeatHold;
import com.revature.TicketService.models.Seats;

public class TicketServiceTest
{
	
	@Before
	public void setSeats()
	{
		Seats.getInstance().setAvailableSeats(10);
		TemporaryHoldService.getInstance().setTemporaryHolds(new ArrayList<SeatHold>());
	}
	
	@After
	public void resetSeats()
	{
		Seats.getInstance().setAvailableSeats(10);
		TemporaryHoldService.getInstance().setTemporaryHolds(new ArrayList<SeatHold>());
	}
	
	
	@Test
	public void returnsStartingNumberOfAvailableSeats()
	{
		int expected = 10;
		TicketService test = new TicketServiceImpl();
		
		assertEquals(expected, test.numSeatsAvailable());
	}
	
	@Test
	public void returns1LessSeatWhenSeatGetPlacedInSeatHold()
	{
		int expected = 9;
		int numberOfSeatsToRemove = 1;
		String testEmail ="testEmail@test.com";
		TicketService test = new TicketServiceImpl();
		
		test.findAndHoldSeats(numberOfSeatsToRemove, testEmail);
		
		assertEquals(expected, test.numSeatsAvailable());
	}
	
	@Test 
	public void returnExceptionIfNotEnoughSeatsAvailableToHold()
	{
		int numberOfSeatsToRemove = 11;
		String testEmail ="testEmail@test.com";
		TicketService test = new TicketServiceImpl();
		
		try {
			test.findAndHoldSeats(numberOfSeatsToRemove, testEmail);
		}catch(Exception e)
		{
			assert(e.getMessage().equals("Not enough seats available Exception"));
		}
		
	}
	
	@Test
	public void returnConfirmationNumberWhenSeatHoldCommited() throws Exception
	{
		String testEmail ="testEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		String expected = seatHoldId + "";
		
		//Create SeatHold and add to TemporaryHoldService
		SeatHold seatHold = new SeatHold(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);
		TemporaryHoldService.getInstance().getTemporaryHolds().add(seatHold);

		//Create TicketService and get confirmation number
		TicketService test = new TicketServiceImpl();
		String confimationNumber = test.reserveSeats(seatHoldId, testEmail);
	
		assertEquals(expected, confimationNumber);
	}
	
	@Test
	public void shouldReturnNullConfirmationNumberWhenReservingWithIncorrectEmail() throws Exception
	{
		String testEmail ="testEmail@test.com";
		String wrongEmail ="wrongEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		
		//Create SeatHold and add to TemporaryHoldService
		SeatHold seatHold = new SeatHold(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);
		TemporaryHoldService.getInstance().getTemporaryHolds().add(seatHold);

		//Create TicketService and get confirmation number
		TicketService test = new TicketServiceImpl();
		String confimationNumber = test.reserveSeats(seatHoldId, wrongEmail);
	
		assertNull(confimationNumber);
	}
	
	@Test
	public void shouldReturnNullConfirmationNumberWhenReservingWithIncorrectConfirmationNumber() throws Exception
	{
		String testEmail ="testEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		int incorrectConfimationNumber = 542222;
		
		//Create SeatHold and add to TemporaryHoldService
		SeatHold seatHold = new SeatHold(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);
		TemporaryHoldService.getInstance().getTemporaryHolds().add(seatHold);

		//Create TicketService and get confirmation number
		TicketService test = new TicketServiceImpl();
		String confimationNumber = test.reserveSeats(incorrectConfimationNumber, testEmail);
	
		assertNull(confimationNumber);
	}
	
	
	@Test
	public void shouldReturnNullConfirmationNumberWhenTryingToReserveTicketsPastExpirationTime() throws Exception
	{
		String testEmail ="testEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		
		//simulate current time is 31 Minutes from now
		Calendar mockTime = Calendar.getInstance();
		mockTime.add(Calendar.MINUTE, 31);
		Date reservedOn = mockTime.getTime();
		
		//Create SeatHold and add to TemporaryHoldService
		SeatHold seatHold = new SeatHold(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);
		seatHold.setRerservedOn(reservedOn);
		
		TemporaryHoldService.getInstance().addTemporarySeatHold(seatHold);

		//Create TicketService and get confirmation number
		TicketService test = new TicketServiceImpl();
		
		String result = test.reserveSeats(seatHoldId, testEmail);
		
		assertNull(result);

	}

}

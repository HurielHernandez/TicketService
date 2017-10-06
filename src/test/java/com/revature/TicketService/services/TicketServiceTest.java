package com.revature.TicketService.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.TicketService.mock.SeatHolds;
import com.revature.TicketService.mock.Seats;
import com.revature.TicketService.models.SeatHold;
import com.revature.TicketService.repository.SeatHoldRepositoryImpl;


public class TicketServiceTest
{
	
	@Before
	public void setSeats()
	{
		Seats.getInstance().setAvailableSeats(10);
		SeatHolds.getInstance().setSeatHolds(new ArrayList<SeatHold>());
	}
	
	@After
	public void resetSeats()
	{
		Seats.getInstance().setAvailableSeats(10);
		SeatHolds.getInstance().setSeatHolds(new ArrayList<SeatHold>());
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
		
		assertEquals( expected, test.numSeatsAvailable());
	}
	
	@Test 
	public void returnNullIfNotEnoughSeats()
	{
		int numberOfSeatsToRemove = 11;
		String testEmail ="testEmail@test.com";
		TicketService test = new TicketServiceImpl();
		
		SeatHold seatHold = test.findAndHoldSeats(numberOfSeatsToRemove, testEmail);
		
		System.out.println("not null" + SeatHolds.getInstance().getSeatHolds());
		
		assert(seatHold == null);
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
		SeatHolds.getInstance().addSeatHold(seatHold);

		//Create TicketService and get confirmation number
		TicketService test = new TicketServiceImpl();
		String confirmationNumber = test.reserveSeats(seatHoldId, testEmail);
		
		assertEquals(expected, confirmationNumber);
	}
	
	@Test
	public void shouldReturnNullConfirmationNumberWhenReservingWithIncorrectEmail() throws Exception 
	{
		SeatHoldRepositoryImpl seatHoldRepository = new SeatHoldRepositoryImpl();
		String testEmail ="testEmail@test.com";
		String wrongEmail ="wrongEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		
		//Create SeatHold and add to SeatHold
		SeatHold seatHold = new SeatHold(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);
		seatHoldRepository.addSeatHold(seatHold);
		
		System.out.println("TEMPORARY" + seatHoldRepository.all());

		//Create TicketService and get confirmation number
		TicketService test = new TicketServiceImpl();
		String confimationNumber = test.reserveSeats(seatHoldId, wrongEmail);
	
		assertNull(confimationNumber);
	}
	
	@Test
	public void shouldReturnNullConfirmationNumberWhenReservingWithIncorrectConfirmationNumber() throws Exception
	{
		SeatHoldRepositoryImpl seatHoldRepository = new SeatHoldRepositoryImpl();
		String testEmail ="testEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		int incorrectConfimationNumber = 542222;
		
		//Create SeatHold and add to SeatHold
		SeatHold seatHold = new SeatHold(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);
		seatHoldRepository.addSeatHold(seatHold);

		//Create TicketService and get confirmation number
		TicketService test = new TicketServiceImpl();
		String confimationNumber = test.reserveSeats(incorrectConfimationNumber, testEmail);
	
		assertNull(confimationNumber);
	}
	
	@Test
	public void shouldReturnConfirmationNumberWhenReservingWithCorrectConfirmationNumber() throws Exception
	{
		SeatHoldRepositoryImpl seatHoldRepository = new SeatHoldRepositoryImpl();
		String testEmail ="testEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		String expectedConfirmationNumber = seatHoldId + "";
		
		//Create SeatHold and add to SeatHold
		SeatHold seatHold = new SeatHold(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);
		seatHoldRepository.addSeatHold(seatHold);

		//Create TicketService and get confirmation number
		TicketService test = new TicketServiceImpl();
		String confimationNumber = test.reserveSeats(seatHoldId, testEmail);
	
		assertEquals(expectedConfirmationNumber, confimationNumber);
	}
	
	@Test
	public void shouldReturnConfirmationNumberWhenTryingToReserveTicketswithinExpirationTime() throws Exception
	{
		SeatHoldRepositoryImpl seatHoldRepository = new SeatHoldRepositoryImpl();            
		String testEmail ="testEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		String expectedConfirmationNumber = seatHoldId +"";
		
		//simulate current time is 31 Minutes from now
		Calendar mockTime = Calendar.getInstance();
		mockTime.add(Calendar.MINUTE, 30);
		Date reservedOn = mockTime.getTime();
		
		//Create SeatHold and add to TemporaryHoldService
		SeatHold seatHold = new SeatHold(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);
		seatHold.setRerservedOn(reservedOn);
		seatHoldRepository.addSeatHold(seatHold);

		//Create TicketService and get confirmation number
		TicketService test = new TicketServiceImpl();
		
		String result = test.reserveSeats(seatHoldId, testEmail);
		
		assertEquals(expectedConfirmationNumber, result);

	}
	
	@Test
	public void shouldReturnNullConfirmationNumberWhenTryingToReserveTicketsPastExpirationTime() throws Exception
	{
		SeatHoldRepositoryImpl seatHoldRepository = new SeatHoldRepositoryImpl();            
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
		seatHoldRepository.addSeatHold(seatHold);

		//Create TicketService and get confirmation number
		TicketService test = new TicketServiceImpl();
		
		String result = test.reserveSeats(seatHoldId, testEmail);
		
		assertNull(result);
	}
}

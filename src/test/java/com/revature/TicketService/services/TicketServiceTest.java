package com.revature.TicketService.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.TicketService.mock.SeatHolds;
import com.revature.TicketService.mock.Seats;
import com.revature.TicketService.models.Seat;
import com.revature.TicketService.models.SeatHold;
import com.revature.TicketService.repository.SeatHoldRepositoryImpl;
import com.revature.TicketService.repository.SeatRepository;
import com.revature.TicketService.repository.SeatRepositoryImpl;


public class TicketServiceTest
{
	private final static Logger logger = Logger.getLogger(TicketServiceTest.class);
	
	@Before
	public void setSeats()
	{
		Seats.getInstance().setSeats(10);
		SeatHolds.getInstance().setSeatHolds(new ArrayList<SeatHold>());
	}
	
	@After
	public void resetSeats()
	{
		Seats.getInstance().setSeats(10);
		SeatHolds.getInstance().setSeatHolds(new ArrayList<SeatHold>());
	}
	
	@Test 
	public void returnsStartingNumberOfAvailableSeats()
	{
		logger.trace("Running returnsStartingNumberAvailableSeats");
		int expected = 10;
		TicketService test = new TicketServiceImpl();
		
		assertEquals(expected, test.numSeatsAvailable());
	}
	
	@Test
	public void returns1LessSeatWhenSeatGetPlacedInSeatHold()
	{
		logger.trace("Running returnsStartingNumberAvailableSeats");
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
		logger.trace("Running returnNullIfNotEnoughSeats");
		int numberOfSeatsToRemove = 11;
		String testEmail ="testEmail@test.com";
		TicketService test = new TicketServiceImpl();
		
		SeatHold seatHold = test.findAndHoldSeats(numberOfSeatsToRemove, testEmail);
		
		assert(seatHold == null);
	}
	
	@Test
	public void shouldReturnConfirmationNumberWhenSeatHoldCommited()
	{
		logger.trace("Running shouldReturnConfirmationNumberWhenSeatHoldCommited");
		TicketService test = new TicketServiceImpl();
		String testEmail ="testEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		String expected = seatHoldId + "";
		
		// Create SeatHold and add to TemporaryHoldService
		test.findAndHoldSeats(numberOfSeatsToReserve, testEmail).setSeatHoldId(seatHoldId);

		// Get confirmation number
		String confirmationNumber = test.reserveSeats(seatHoldId, testEmail);
		
		assertEquals(expected, confirmationNumber);
	}
	
	@Test
	public void shouldReturnNullConfirmationNumberWhenReservingWithIncorrectEmail() 
	{
		logger.trace("Running shoulReturnNullConfirmationNumberWhenReservingWithIncorrectEmail");
		TicketService test = new TicketServiceImpl();
		String testEmail ="testEmail@test.com";
		String wrongEmail ="wrongEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		
		// Create SeatHold and add to SeatHold
		SeatHold seatHold = test.findAndHoldSeats(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);
		
		// Get confirmation number
		String confimationNumber = test.reserveSeats(seatHoldId, wrongEmail);
	
		assertNull(confimationNumber);
	}
	
	@Test
	public void shouldReturnNullConfirmationNumberWhenReservingWithIncorrectConfirmationNumber()
	{
		logger.trace("Running shouldReturnNullConfirmationNumberWhenReservingWithIncorrectConfirmationNumber");
		TicketService test = new TicketServiceImpl();
		String testEmail ="testEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		int incorrectConfimationNumber = 542222;
		
		// Create SeatHold and add to SeatHold
		SeatHold seatHold = test.findAndHoldSeats(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);

		// Get confirmation number
		String confimationNumber = test.reserveSeats(incorrectConfimationNumber, testEmail);
	
		assertNull(confimationNumber);
	}
	
	@Test
	public void shouldReturnConfirmationNumberWhenReservingWithCorrectConfirmationNumber()
	{
		logger.trace("Running shouldReturnConfirmationNumberWhenReservingWithCorrectConfirmationNumber");
		TicketService test = new TicketServiceImpl();
		String testEmail ="testEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		String expectedConfirmationNumber = seatHoldId + "";
		
		// Create SeatHold and add to SeatHold
		SeatHold seatHold = test.findAndHoldSeats(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);

		// Get confirmation number
		String confimationNumber = test.reserveSeats(seatHoldId, testEmail);
	
		assertEquals(expectedConfirmationNumber, confimationNumber);
	}
	
	@Test
	public void shouldReturnConfirmationNumberWhenTryingToReserveTicketswithinExpirationTime()
	{
		logger.trace("Running shouldReturnConfirmationNumberWhenTryingToReserveTicketswithinExpirationTime");
		SeatHoldRepositoryImpl seatHoldRepository = new SeatHoldRepositoryImpl();   
		SeatRepository seatRepository = new SeatRepositoryImpl();
		
		String testEmail ="testEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		String expectedConfirmationNumber = seatHoldId +"";
		List<Seat> seatsToHold = seatRepository.findSeats(numberOfSeatsToReserve);
		
		// Simulate reserved 20 Minutes ago
		Calendar mockTime = Calendar.getInstance();
		mockTime.add(Calendar.MINUTE, -20);
		Date reservedOn = mockTime.getTime();
		
		// Create SeatHold and add to TemporaryHoldService
		SeatHold seatHold = new SeatHold(seatsToHold, testEmail);
		seatHold.setSeatHoldId(seatHoldId);
		seatHold.setRerservedOn(reservedOn);
		seatHoldRepository.add(seatHold);

		// Create TicketService and get confirmation number
		TicketService test = new TicketServiceImpl();
		
		String result = test.reserveSeats(seatHoldId, testEmail);
		
		assertEquals(expectedConfirmationNumber, result);
	}
	
	@Test
	public void shouldReturnNullConfirmationNumberWhenTryingToReserveTicketsPastExpirationTime()
	{
		logger.trace("Running shouldReturnNullConfirmationNumberWhenTryingToReserveTicketsPastExpirationTime");
		TicketService test = new TicketServiceImpl();           
		String testEmail ="testEmail@test.com";
		int numberOfSeatsToReserve = 5;
		int seatHoldId = 512345;
		
		// Simulate reserved  31 Minutes ago
		Calendar mockTime = Calendar.getInstance();
		mockTime.add(Calendar.MINUTE, -31);
		Date reservedOn = mockTime.getTime();
		
		// Create SeatHold and add to TemporaryHoldService
		SeatHold seatHold = test.findAndHoldSeats(numberOfSeatsToReserve, testEmail);
		seatHold.setSeatHoldId(seatHoldId);
		seatHold.setRerservedOn(reservedOn);

		// Create TicketService and get confirmation number
		String result = test.reserveSeats(seatHoldId, testEmail);
		
		assertNull(result);
	}
}

package com.revature.TicketService;

import com.revature.TicketService.models.SeatHold;

import org.apache.log4j.Logger;

import com.revature.TicketService.mock.SeatHolds;
import com.revature.TicketService.mock.Seats;
import com.revature.TicketService.services.TicketService;
import com.revature.TicketService.services.TicketServiceImpl;
import com.revature.TicketService.utilities.SeatHoldTimer;


public class App 
{
	public final static Logger logger = Logger.getLogger(App.class);

	public static void main( String[] args )
    {
        TicketService ticketService = new TicketServiceImpl();
        
    }
}

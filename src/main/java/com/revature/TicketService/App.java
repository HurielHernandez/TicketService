package com.revature.TicketService;

import com.revature.TicketService.models.SeatHold;
import com.revature.TicketService.models.Seats;
import com.revature.TicketService.services.ReservationService;
import com.revature.TicketService.services.TemporaryHoldService;
import com.revature.TicketService.services.TicketService;
import com.revature.TicketService.services.TicketServiceImpl;
import com.revature.TicketService.utilities.SeatHoldTimer;


public class App 
{
	public static void main( String[] args )
    {
        
        System.out.println(Seats.getInstance().getAvailableSeats());
        
        TicketService ticketService = new TicketServiceImpl();
        
        SeatHold seatHold = ticketService.findAndHoldSeats(1, "huriel.hdz@gmail.com");
        
        
        try
		{
			TemporaryHoldService.getInstance().addTemporarySeatHold(seatHold);
		} catch (Exception e)
		{
			e.printStackTrace();
			seatHold = null;
		}
        
        
        System.out.println(TemporaryHoldService.getInstance());
//      
//        try {
//        	System.out.println(ticketService.reserveSeats(seatHold.getSeatHoldId(), "huriel.hdz@gmail.com"));
//        }catch(Exception e)
//        {
//        	e.printStackTrace();
//        }
//        
//        System.out.println(ReservationService.getInstance());
//		
//        System.out.println(Seats.getInstance().getAvailableSeats());
        
 
    }
	
}

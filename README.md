# TicketService
Implement a simplet ticket service that facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance venue.

## Assumptions

Ticket Service is to be incorporated as a service for an enterprise level application so this implementation uses mock repositories to manipulate mock data.  The repositories can be replaced by simply changing the the mock repository implementations.

Selecting best seats for a customer is assumed to be the closest seats to the stage.  The application will select seats by pulling the first seats available from a queue. 

## To Get started

Download Repository
```
$ git clone https://github.com/HurielHernandez/TicketService.git
```
Download dependencies and build jar file
```
$ mvn clean install
```
Run Tests
```
$ mvn test
```
Output should contain
```
Running com.revature.TicketService.services.TicketServiceTest
2017-10-08 15:10:21 TRACE TicketServiceTest:70 - Running returnNullIfNotEnoughSeats
2017-10-08 15:10:21 ERROR TicketServiceImpl:48 - java.lang.Exception: Not enough Seats Exception
2017-10-08 15:10:21 TRACE TicketServiceTest:122 - Running shouldReturnNullConfirmationNumberWhenReservingWithIncorrectConfirmationNumber
2017-10-08 15:10:21 ERROR TicketServiceImpl:70 - java.lang.Exception: SeatHold not found Exception
2017-10-08 15:10:21 TRACE TicketServiceTest:56 - Running returnsStartingNumberAvailableSeats
2017-10-08 15:10:21 TRACE TicketServiceTest:194 - Running shouldReturnNullConfirmationNumberWhenTryingToReserveTicketsPastExpirationTime
2017-10-08 15:10:21 ERROR TicketServiceImpl:70 - java.lang.Exception: SeatHold not found Exception
2017-10-08 15:10:21 TRACE TicketServiceTest:102 - Running shoulReturnNullConfirmationNumberWhenReservingWithIncorrectEmail
2017-10-08 15:10:21 ERROR TicketServiceImpl:70 - java.lang.Exception: SeatHold not found Exception
2017-10-08 15:10:21 TRACE TicketServiceTest:162 - Running shouldReturnConfirmationNumberWhenTryingToReserveTicketswithinExpirationTime
2017-10-08 15:10:21 TRACE TicketServiceTest:46 - Running returnsStartingNumberAvailableSeats
2017-10-08 15:10:21 TRACE TicketServiceTest:83 - Running shouldReturnConfirmationNumberWhenSeatHoldCommited
2017-10-08 15:10:21 TRACE TicketServiceTest:142 - Running shouldReturnConfirmationNumberWhenReservingWithCorrectConfirmationNumber
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.155 sec - in com.revature.TicketService.services.TicketServiceTest
```

Run Application as a jar
```
$ java -jar target/TicketService-0.0.1-Snapshot-jar-with-dependencies.jar
```
Produces no output

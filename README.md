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

Run Application
```
$ java -jar target/TicketService-0.0.1-Snapshot-jar-with-dependencies.jar
```

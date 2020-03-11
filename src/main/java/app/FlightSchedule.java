package app;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlightSchedule implements Serializable {
  final int id;
  final String Destination;
  final LocalDateTime flightTime;
  final int Seats;




  private static final long serialVersionUID = 1L;

  public FlightSchedule(int id, String Destination, LocalDateTime flightTime, int Seats) {
    this.id = id;
    this.Destination = Destination;
    this.flightTime = flightTime;
    this.Seats = Seats;
  }

  public static FlightSchedule parse(String line) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String[] chunks = line.split(";");
    return new FlightSchedule(
        Integer.parseInt(chunks[0]),
        chunks[1],
        LocalDateTime.parse(chunks[2], format),
        Integer.parseInt(chunks[3])
    );
  }

  public String represent() {
    return String.format("%d:%s:%s:%d", id, Destination, flightTime, Seats);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FlightSchedule schedule = (FlightSchedule) o;

    if (id != schedule.id) return false;
    if (Seats != schedule.Seats) return false;
    return Destination != null ? Destination.equals(schedule.Destination) : schedule.Destination == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (Destination != null ? Destination.hashCode() : 0);
    result = 31 * result + Seats;
    return result;
  }

  @Override
  public String toString() {
    return String.format("FlightNumber = %d | City = '%s' | FlightTime = %s | AvailableSeats = %d ", id, Destination, flightTime, Seats);
  }
}

package app;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DAOFlightsFileText implements DAO<FlightSchedule> {

  private File file;

  public DAOFlightsFileText(String filename) {
    this.file = new File(filename);
  }

  @Override
  public Collection<FlightSchedule> getAll() {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      return br.lines().map(FlightSchedule::parse).collect(Collectors.toList());
    } catch (IOException ex) {
      return new ArrayList<>();
    }
  }

  @Override
  public Optional<FlightSchedule> get(int id) {
    return getAll().stream().filter(s -> s.id == id).findFirst();
  }

  @Override
  public Collection<FlightSchedule> getAllBy(Predicate<FlightSchedule> p) {
    return getAll().stream().filter(p).collect(Collectors.toList());
  }

  @Override
  public void create(FlightSchedule flight) {
    Collection<FlightSchedule> flights = getAll();
    flights.add(flight);
    write(flights);
  }

  @Override
  public void delete(int id) {
    Collection<FlightSchedule> flights = getAllBy(s -> s.id != id);
    write(flights);
  }

  private void write(Collection<FlightSchedule> flights) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
      for (FlightSchedule s: flights) {
        bw.write(s.represent());
        bw.write("\n");
      }
    } catch (IOException ex) {
      throw new RuntimeException("DAO:write:IOException", ex);
    }
  }

}

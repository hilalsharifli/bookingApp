package app;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DAOStudentMemory implements DAO<FlightSchedule> {

  private final Map<Integer, FlightSchedule> storage = new HashMap<>();

  @Override
  public Optional<FlightSchedule> get(int id) {
    try {
      return Optional.of(storage.get(id));
    } catch (NullPointerException ex) {
      return Optional.empty();
    }
  }

  @Override
  public Collection<FlightSchedule> getAll() {
    return storage.values();
  }

  @Override
  public Collection<FlightSchedule> getAllBy(Predicate<FlightSchedule> p) {
    return getAll().stream().filter(p).collect(Collectors.toList());
  }

  @Override
  public void create(FlightSchedule data) {
    storage.put(data.id, data);
  }

  @Override
  public void delete(int id) {
    storage.remove(id);
  }
}

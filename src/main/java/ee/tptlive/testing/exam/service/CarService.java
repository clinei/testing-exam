package ee.tptlive.testing.exam.service;

import ee.tptlive.testing.exam.dao.CarDao;
import ee.tptlive.testing.exam.model.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@ApplicationScoped
public class CarService {

  @Inject
  private CarDao carDao;

  public Optional<Car> getById(int id) {
    return carDao.findAll().stream()
        .filter(car -> car.getId() == id)
        .findFirst();
  }

  public List<Car> getAll() {
    return carDao.findAll();
  }

  public List<Car> getAllFilteredByBrandAndAvailability(String brand, Boolean available) {
    Stream<Car> cars = carDao.findAll().stream();

    if (brand != null && !brand.trim().isEmpty()) {
      cars = cars.filter(car -> car.getBrand().toLowerCase().contains(brand.toLowerCase()));
    }

    if (available != null) {
      cars = cars.filter(car -> car.isAvailable() == available);
    }

    return cars.collect(toList());
  }

}

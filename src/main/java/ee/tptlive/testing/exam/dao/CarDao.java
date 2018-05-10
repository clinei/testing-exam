package ee.tptlive.testing.exam.dao;

import ee.tptlive.testing.exam.model.Car;
import ee.tptlive.testing.exam.model.CarEngineType;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@ApplicationScoped
public class CarDao {

  private List<Car> cars = asList(
      new Car(1, "BMW", "320i", 2010, CarEngineType.PETROL, true),
      new Car(2, "BMW", "X5", 2017, CarEngineType.DIESEL, true),
      new Car(3, "Lexus", "NX200", 2015, CarEngineType.HYBRYD, false),
      new Car(4, "Toyota", "Prius", 2010, CarEngineType.ELECTRIC, true),
      new Car(5, "Toyota", "Avensis", 1995, CarEngineType.PETROL, true),
      new Car(6, "Audi", "RS3", 2018, CarEngineType.PETROL, false),
      new Car(7, "Mitsubishi", "Eclipse", 1999, CarEngineType.DIESEL, true),
      new Car(8, "Subaru", "XV", 2014, CarEngineType.PETROL, true),
      new Car(9, "Subaru", "Ascent", 2019, CarEngineType.PETROL, false),
      new Car(10, "Lada", "Vesta", 2001, CarEngineType.DIESEL, false),
      new Car(11, "Ford", "Mondeo", 2010, CarEngineType.HYBRYD, true),
      new Car(12, "Hyundai", "Solaris", 2009, CarEngineType.ELECTRIC, true),
      new Car(13, "Chevrolet", "RAM 1500", 2008, CarEngineType.DIESEL, false),
      new Car(14, "AUDI", "A8", 2015, CarEngineType.PETROL, true)
  );

  public List<Car> findAll() {
    return new ArrayList<>(cars);
  }

}

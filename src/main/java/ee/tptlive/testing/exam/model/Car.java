package ee.tptlive.testing.exam.model;

import java.util.Objects;

public class Car {

  private int id;
  private String brand;
  private String model;
  private int year;
  private CarEngineType engineType;
  private boolean isAvailable;

  public Car(int id, String brand, String model, int year, CarEngineType engineType, boolean isAvailable) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.engineType = engineType;
    this.isAvailable = isAvailable;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public CarEngineType getEngineType() {
    return engineType;
  }

  public void setEngineType(CarEngineType engineType) {
    this.engineType = engineType;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Car car = (Car) o;
    return id == car.id &&
        year == car.year &&
        isAvailable == car.isAvailable &&
        Objects.equals(brand, car.brand) &&
        Objects.equals(model, car.model) &&
        engineType == car.engineType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, brand, model, year, engineType, isAvailable);
  }

  @Override
  public String toString() {
    return "Car{" +
        "id=" + id +
        ", brand='" + brand + '\'' +
        ", model='" + model + '\'' +
        ", year=" + year +
        ", engineType=" + engineType +
        ", isAvailable=" + isAvailable +
        '}';
  }

}

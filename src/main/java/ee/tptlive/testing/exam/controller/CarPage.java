package ee.tptlive.testing.exam.controller;

import ee.tptlive.testing.exam.model.Car;
import ee.tptlive.testing.exam.service.CarService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ManagedBean
@ViewScoped
public class CarPage {

  @Inject
  private CarService carService;

  private int selectedId;
  private Car selectedCar;

  public List<Car> getCars() {
    return carService.getAll();
  }

  public void loadCar() {
    selectedCar = carService.getById(selectedId)
        .orElseThrow(NotFoundException::new);
  }

  public Car getSelectedCar() {
    return selectedCar;
  }

  public int getSelectedId() {
    return selectedId;
  }

  public void setSelectedId(int selectedId) {
    this.selectedId = selectedId;
  }

}

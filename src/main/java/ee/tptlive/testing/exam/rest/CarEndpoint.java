package ee.tptlive.testing.exam.rest;

import ee.tptlive.testing.exam.model.Car;
import ee.tptlive.testing.exam.service.CarService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
@Path("cars")
public class CarEndpoint {

  @Inject
  private CarService carService;

  @GET
  @Produces(APPLICATION_JSON)
  public List<Car> getAll(@QueryParam("brand") String brand, @QueryParam("available") Boolean available) {
    return carService.getAllFilteredByBrandAndAvailability(brand, available);
  }

  @GET
  @Path("{id}")
  @Produces(APPLICATION_JSON)
  public Car getById(@PathParam("id") String idParam) {
    try {
      int id = Integer.parseInt(idParam);
      return carService.getById(id).orElseThrow(NotFoundException::new);
    } catch (NumberFormatException e) {
      throw new BadRequestException(e);
    }
  }

}

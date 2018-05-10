package ee.tptlive.testing.exam.rest;

import ee.tptlive.testing.exam.model.Car;
import ee.tptlive.testing.exam.model.CarEngineType;
import ee.tptlive.testing.exam.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static java.util.Collections.emptyList;

@RunWith(MockitoJUnitRunner.class)
public class CarEndpointTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarEndpoint carEndpoint;

    @Test
    public void getAll_ReturnsEmptyList_IfServiceReturnsEmptyList() {
        // given
        List<Car> expectedResult = emptyList();

        String brand = "Lada";
        Boolean availability = true;

        when(carService.getAllFilteredByBrandAndAvailability(brand, availability)).thenReturn(emptyList());

        // when
        List<Car> actualResult = carEndpoint.getAll(brand, availability);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAll_ReturnsCars_IfServiceReturnsCars() {
        // given
        List<Car> expectedResult = asList(
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

        String brand = "Lada";
        Boolean availability = true;

        when(carService.getAllFilteredByBrandAndAvailability(brand, availability)).thenReturn(asList(
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
        ));

        // when
        List<Car> actualResult = carEndpoint.getAll(brand, availability);

        assertEquals(expectedResult, actualResult);
    }
}
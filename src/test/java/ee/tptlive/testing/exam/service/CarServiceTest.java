package ee.tptlive.testing.exam.service;

import ee.tptlive.testing.exam.dao.CarDao;
import ee.tptlive.testing.exam.model.Car;
import ee.tptlive.testing.exam.model.CarEngineType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static java.util.Arrays.asList;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    private CarDao carDao;

    @InjectMocks
    private CarService carService;

    @Test
    public void getAll_ReturnsEmptyList_IfNothingFound() {
        // given
        when(carDao.findAll()).thenReturn(emptyList());

        // when
        List<Car> actualResult = carService.getAll();

        // then
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void getAll_ReturnsAllFromDao_IfDaoFindsSomething() {
        // given
        List<Car> expectedResult = asList(
                new Car(13, "Chevrolet", "RAM 1500", 2008, CarEngineType.DIESEL, false),
                new Car(14, "AUDI", "A8", 2015, CarEngineType.PETROL, true)
        );

        when(carDao.findAll()).thenReturn(asList(
                new Car(13, "Chevrolet", "RAM 1500", 2008, CarEngineType.DIESEL, false),
                new Car(14, "AUDI", "A8", 2015, CarEngineType.PETROL, true)
        ));

        // when
        List<Car> actualResult = carService.getAll();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllFilteredByBrandAndAvailability_ReturnsEmptyList_IfNothingFoundAndNoFilters() {
        // given
        List <Car> expectedResult = emptyList();

        when(carDao.findAll()).thenReturn(emptyList());

        String brand = null;
        Boolean availability = null;

        // when
        List<Car> actualResult = carService.getAllFilteredByBrandAndAvailability(brand, availability);

        // then
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void getAllFilteredByBrandAndAvailability_ReturnsEmptyList_IfNothingFoundAndHasFilters() {
        // given
        List <Car> expectedResult = emptyList();

        when(carDao.findAll()).thenReturn(emptyList());

        String brand = "z";
        Boolean availability = true;

        // when
        List<Car> actualResult = carService.getAllFilteredByBrandAndAvailability(brand, availability);

        // then
        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void getAllFilteredByBrandAndAvailability_ReturnsEmptyList_IfFoundAndEverythingIsFilteredOut() {
        // given
        List<Car> expectedResult = emptyList();

        when(carDao.findAll()).thenReturn(asList(
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

        String brand = "no_car_should_have_this_as_a_brand";
        Boolean availability = false;

        // when
        List<Car> actualResult = carService.getAllFilteredByBrandAndAvailability(brand, availability);

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllFilteredByBrandAndAvailability_ReturnsAllMatchingEntries_IfFoundAndFilteringByBrand() {
        // given
        List<Car> expectedResult = asList(
                new Car(8, "Subaru", "XV", 2014, CarEngineType.PETROL, true),
                new Car(9, "Subaru", "Ascent", 2019, CarEngineType.PETROL, false)
        );

        when(carDao.findAll()).thenReturn(asList(
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

        String brand = "Subaru";
        Boolean availability = null;

        // when
        List<Car> actualResult = carService.getAllFilteredByBrandAndAvailability(brand, availability);

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllFilteredByBrandAndAvailability_ReturnsAllMatchingEntries_IfFoundAndFilteringByBrandWithCaseInsensitiveMatch() {
        // given
        List<Car> expectedResult = asList(
                new Car(8, "Subaru", "XV", 2014, CarEngineType.PETROL, true),
                new Car(9, "Subaru", "Ascent", 2019, CarEngineType.PETROL, false)
        );

        when(carDao.findAll()).thenReturn(asList(
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

        String brand = "suBARu";
        Boolean availability = null;

        // when
        List<Car> actualResult = carService.getAllFilteredByBrandAndAvailability(brand, availability);

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllFilteredByBrandAndAvailability_ReturnsAllMatchingEntries_IfFoundAndFilteringByBrandWithPartialMatch() {
        // given
        List<Car> expectedResult = asList(
                new Car(4, "Toyota", "Prius", 2010, CarEngineType.ELECTRIC, true),
                new Car(5, "Toyota", "Avensis", 1995, CarEngineType.PETROL, true),
                new Car(4, "Daytona", "Prius", 2010, CarEngineType.ELECTRIC, false),
                new Car(5, "Daytona", "Avensis", 1995, CarEngineType.PETROL, false)
        );

        when(carDao.findAll()).thenReturn(asList(
                new Car(1, "BMW", "320i", 2010, CarEngineType.PETROL, true),
                new Car(2, "BMW", "X5", 2017, CarEngineType.DIESEL, true),
                new Car(3, "Lexus", "NX200", 2015, CarEngineType.HYBRYD, false),
                new Car(4, "Toyota", "Prius", 2010, CarEngineType.ELECTRIC, true),
                new Car(5, "Toyota", "Avensis", 1995, CarEngineType.PETROL, true),
                new Car(4, "Daytona", "Prius", 2010, CarEngineType.ELECTRIC, false),
                new Car(5, "Daytona", "Avensis", 1995, CarEngineType.PETROL, false),
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

        String brand = "to";
        Boolean availability = null;

        // when
        List<Car> actualResult = carService.getAllFilteredByBrandAndAvailability(brand, availability);

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllFilteredByBrandAndAvailability_ReturnsAllAvailable_IfFoundAndFilteringWithAvailabilityTrue() {
        // given
        List<Car> expectedResult = asList(
                new Car(1, "BMW", "320i", 2010, CarEngineType.PETROL, true),
                new Car(2, "BMW", "X5", 2017, CarEngineType.DIESEL, true),
                new Car(4, "Toyota", "Prius", 2010, CarEngineType.ELECTRIC, true),
                new Car(5, "Toyota", "Avensis", 1995, CarEngineType.PETROL, true),
                new Car(7, "Mitsubishi", "Eclipse", 1999, CarEngineType.DIESEL, true),
                new Car(8, "Subaru", "XV", 2014, CarEngineType.PETROL, true),
                new Car(11, "Ford", "Mondeo", 2010, CarEngineType.HYBRYD, true),
                new Car(12, "Hyundai", "Solaris", 2009, CarEngineType.ELECTRIC, true),
                new Car(14, "AUDI", "A8", 2015, CarEngineType.PETROL, true)
        );

        when(carDao.findAll()).thenReturn(asList(
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

        String brand = null;
        Boolean availability = true;

        // when
        List<Car> actualResult = carService.getAllFilteredByBrandAndAvailability(brand, availability);

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllFilteredByBrandAndAvailability_ReturnsAllNotAvailable_IfFoundAndFilteringWithAvailabilityFalse() {
        // given
        List<Car> expectedResult = asList(
                new Car(3, "Lexus", "NX200", 2015, CarEngineType.HYBRYD, false),
                new Car(6, "Audi", "RS3", 2018, CarEngineType.PETROL, false),
                new Car(9, "Subaru", "Ascent", 2019, CarEngineType.PETROL, false),
                new Car(10, "Lada", "Vesta", 2001, CarEngineType.DIESEL, false),
                new Car(13, "Chevrolet", "RAM 1500", 2008, CarEngineType.DIESEL, false)
        );

        when(carDao.findAll()).thenReturn(asList(
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

        String brand = null;
        Boolean availability = false;

        // when
        List<Car> actualResult = carService.getAllFilteredByBrandAndAvailability(brand, availability);

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllFilteredByBrandAndAvailability_ReturnsOnlyBothMatching_IfFoundAndFilteringByBrandAndAvailabilityTrue() {
        // given
        List<Car> expectedResult = asList(
                new Car(4, "Toyota", "Prius", 2010, CarEngineType.ELECTRIC, true),
                new Car(5, "Toyota", "Avensis", 1995, CarEngineType.PETROL, true)
        );

        when(carDao.findAll()).thenReturn(asList(
                new Car(1, "BMW", "320i", 2010, CarEngineType.PETROL, true),
                new Car(2, "BMW", "X5", 2017, CarEngineType.DIESEL, true),
                new Car(3, "Lexus", "NX200", 2015, CarEngineType.HYBRYD, false),
                new Car(4, "Toyota", "Prius", 2010, CarEngineType.ELECTRIC, true),
                new Car(5, "Toyota", "Avensis", 1995, CarEngineType.PETROL, true),
                new Car(4, "Daytona", "Prius", 2010, CarEngineType.ELECTRIC, false),
                new Car(5, "Daytona", "Avensis", 1995, CarEngineType.PETROL, false),
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

        String brand = "to";
        Boolean availability = true;

        // when
        List<Car> actualResult = carService.getAllFilteredByBrandAndAvailability(brand, availability);

        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllFilteredByBrandAndAvailability_ReturnsOnlyBothMatching_IfFoundAndFilteringByBrandAndAvailabilityFalse() {
        // given
        List<Car> expectedResult = asList(
                new Car(4, "Daytona", "Prius", 2010, CarEngineType.ELECTRIC, false),
                new Car(5, "Daytona", "Avensis", 1995, CarEngineType.PETROL, false)
        );

        when(carDao.findAll()).thenReturn(asList(
                new Car(1, "BMW", "320i", 2010, CarEngineType.PETROL, true),
                new Car(2, "BMW", "X5", 2017, CarEngineType.DIESEL, true),
                new Car(3, "Lexus", "NX200", 2015, CarEngineType.HYBRYD, false),
                new Car(4, "Toyota", "Prius", 2010, CarEngineType.ELECTRIC, true),
                new Car(5, "Toyota", "Avensis", 1995, CarEngineType.PETROL, true),
                new Car(4, "Daytona", "Prius", 2010, CarEngineType.ELECTRIC, false),
                new Car(5, "Daytona", "Avensis", 1995, CarEngineType.PETROL, false),
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

        String brand = "to";
        Boolean availability = false;

        // when
        List<Car> actualResult = carService.getAllFilteredByBrandAndAvailability(brand, availability);

        // then
        assertEquals(expectedResult, actualResult);
    }
}
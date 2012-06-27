package pe.com.abaris.gazoo.services;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import pe.com.abaris.gazoo.model.Vehicle;

/**
 * Class: VehicleServiceRegistrationTest
 * tests the functionality of the the VehicleServiceImpl class.
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractVehicleServiceTest {
    
    protected static final String VEHICLE_OWNER = "testowner";

    protected static final String VEHICLE_NAME = "validName";
    
    protected static final int VEHICLE_INITIAL_MILEAGE = 5000;

    protected static final String VEHICLE_MAKE = "Toyoba";
    
    protected static final String VEHICLE_MODEL = "Laris";

    protected static final String VEHICLE_COLOR = "blue";

    protected static final String ANOTHER_VEHICLE_NAME = "anotherName";

    protected static final String ANOTHER_VEHICLE_MODEL = "Trio";

    protected static final String ANOTHER_VEHICLE_MAKE = "Revault";

    /**
     * Variable: vehicleService
     * is the target of the test
     */
    @Spy
    private final VehicleServiceImpl vehicleService = new VehicleServiceImpl();

    private final EntityValidator<Vehicle> vehicleValidator = new VehicleValidator();

    @Before
    public void _setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vehicleService.setVehicleValidator(vehicleValidator);
    }

    public VehicleServiceImpl getVehicleService() {
        return vehicleService;
    }

}

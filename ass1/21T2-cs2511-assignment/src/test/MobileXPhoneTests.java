package test;

        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.TestInstance;
        import org.junit.jupiter.api.TestInstance.Lifecycle;

        import java.security.interfaces.RSAKey;
        import java.time.LocalTime;

        import test.test_helpers.DummyConnection;
        import test.test_helpers.ResponseHelper;
        import test.test_helpers.TestHelper;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class MobileXPhoneTests {
    @Test
    public void testEasy() {
        // Example created for testing SpaceXSatellite
        // Creates 1 satellite and 5 devices
        // Activates 3 of the devices twice and then schedules connections

        String beginningWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectDevice("MobileXPhone", "C", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(8, 0) }})
                .expectDevice("MobileXPhone", "D", 90, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(10, 0) }})
                .expectDevice("MobileXPhone", "E", 110, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(12, 0) }})
                .expectSatellite("NasaSatellite", "Satellite1", 10000, 70, 85, new String[] {"C", "D", "E"})
                .expectSatellite("SpaceXSatellite", "Satellite2", 10000, 90, 55.5, new String[] {"C", "D", "E"})
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "Satellite1", 10000, 82.24, 85, new String[] {"C", "D", "E"})
                .expectSatellite("SpaceXSatellite", "Satellite2", 10000, 97.99, 55.5,
                        new String[] {"C", "D", "E"},
                        new DummyConnection[] {
                                new DummyConnection("C", LocalTime.of(0, 0), LocalTime.of(8, 1), 480),
                                new DummyConnection("D", LocalTime.of(2, 0), LocalTime.of(10, 1), 480),
                                new DummyConnection("E", LocalTime.of(4, 0), LocalTime.of(12, 1), 480),
                        })
                .expectDevice("MobileXPhone", "C", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(8, 0) }})
                .expectDevice("MobileXPhone", "D", 90, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(10, 0) }})
                .expectDevice("MobileXPhone", "E", 110, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(12, 0) }})
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("MobileXPhone", "C", 70)
                .createDevice("MobileXPhone", "D", 90)
                .createDevice("MobileXPhone", "E", 110)
                .createSatellite("NasaSatellite", "Satellite1", 10000, 70)
                .createSatellite("SpaceXSatellite", "Satellite2", 10000, 90)
                .scheduleDeviceActivation("C", LocalTime.of(0, 0), 480)
                .scheduleDeviceActivation("D", LocalTime.of(2, 0), 480)
                .scheduleDeviceActivation("E", LocalTime.of(4, 0), 480)
                .showWorldState(beginningWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

}
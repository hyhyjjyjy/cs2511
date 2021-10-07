package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.security.interfaces.RSAKey;
import java.time.LocalTime;

import test.test_helpers.DummyConnection;
import test.test_helpers.ResponseHelper;
import test.test_helpers.TestHelper;

@TestInstance(value = Lifecycle.PER_CLASS)
public class SpaceXSatelliteTests {
    @Test
    public void testEasy() {
        // Example created for testing SpaceXSatellite
        // Creates 1 satellite and 5 devices
        // Activates 3 of the devices twice and then schedules connections

        String beginningWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectDevice("HandheldDevice", "A", 30)
                .expectDevice("HandheldDevice", "B", 50)
                .expectDevice("HandheldDevice", "C", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(2, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(8, 0)} })
                .expectDevice("HandheldDevice", "D", 90, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(4, 0) },
                                { LocalTime.of(7, 0), LocalTime.of(9, 0)} })
                .expectDevice("HandheldDevice", "E", 110, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(6, 0) },
                                { LocalTime.of(8, 0), LocalTime.of(10, 0)} })
                .expectSatellite("SpaceXSatellite", "Satellite1", 10000, 70, 55.5, new String[] {"A", "B", "C", "D", "E"})
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("SpaceXSatellite", "Satellite1", 10000, 77.99, 55.5,
                        new String[] { "A", "B", "C", "D", "E" },
                        new DummyConnection[] {
                                new DummyConnection("C", LocalTime.of(0, 0), LocalTime.of(2, 1), 120),
                                new DummyConnection("D", LocalTime.of(2, 0), LocalTime.of(4, 1), 120),
                                new DummyConnection("E", LocalTime.of(4, 0), LocalTime.of(6, 1), 120),
                                new DummyConnection("C", LocalTime.of(6, 0), LocalTime.of(8, 1), 120),
                                new DummyConnection("D", LocalTime.of(7, 0), LocalTime.of(9, 1), 120),
                                new DummyConnection("E", LocalTime.of(8, 0), LocalTime.of(10, 1), 120),
                        })
                .expectDevice("HandheldDevice", "A", 30)
                .expectDevice("HandheldDevice", "B", 50)
                .expectDevice("HandheldDevice", "C", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(2, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(8, 0)} })
                .expectDevice("HandheldDevice", "D", 90, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(4, 0) },
                                { LocalTime.of(7, 0), LocalTime.of(9, 0)} })
                .expectDevice("HandheldDevice", "E", 110, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(6, 0) },
                                { LocalTime.of(8, 0), LocalTime.of(10, 0)} })
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("HandheldDevice", "A", 30)
                .createDevice("HandheldDevice", "B", 50)
                .createDevice("HandheldDevice", "C", 70)
                .createDevice("HandheldDevice", "D", 90)
                .createDevice("HandheldDevice", "E", 110)
                .createSatellite("SpaceXSatellite", "Satellite1", 10000, 70)
                .scheduleDeviceActivation("C", LocalTime.of(0, 0), 120)
                .scheduleDeviceActivation("D", LocalTime.of(2, 0), 120)
                .scheduleDeviceActivation("E", LocalTime.of(4, 0), 120)
                .scheduleDeviceActivation("C", LocalTime.of(6, 0), 120)
                .scheduleDeviceActivation("D", LocalTime.of(7, 0), 120)
                .scheduleDeviceActivation("E", LocalTime.of(8, 0), 120)
                .showWorldState(beginningWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

}
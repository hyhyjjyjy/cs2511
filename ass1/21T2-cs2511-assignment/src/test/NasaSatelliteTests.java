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
public class NasaSatelliteTests {
    @Test
    public void testExceedingMixed() {
        // Example created for testing SpaceXSatellite
        // Creates 1 satellite and 5 devices
        // Activates 5 of the devices twice and then schedules connections

        String beginningWorldState = new ResponseHelper(LocalTime.of(0, 0))

                .expectDevice("LaptopDevice", "A", 36, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(10, 0) } })
                .expectDevice("LaptopDevice", "A1", 340, false,
                        new LocalTime[][] { { LocalTime.of(1, 0), LocalTime.of(10, 0) } })
                .expectDevice("LaptopDevice", "A2", 350, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(10, 0) } })
                .expectDevice("LaptopDevice", "A3", 0, false,
                        new LocalTime[][] { { LocalTime.of(3, 0), LocalTime.of(10, 0) } })
                .expectDevice("LaptopDevice", "A4", 10, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(10, 0) } })
                .expectDevice("LaptopDevice", "A5", 20, false,
                        new LocalTime[][] { { LocalTime.of(5, 0), LocalTime.of(10, 0) }})

                .expectDevice("HandheldDevice", "B1", 31, false,
                        new LocalTime[][] { { LocalTime.of(8, 0), LocalTime.of(16, 0) }})
                .expectDevice("HandheldDevice", "B2", 32, false,
                        new LocalTime[][] { { LocalTime.of(9, 0), LocalTime.of(16, 0) }})
                .expectDevice("HandheldDevice", "B3", 33, false,
                        new LocalTime[][] { { LocalTime.of(10, 0), LocalTime.of(16, 0) }})
                .expectDevice("HandheldDevice", "B4", 34, false,
                        new LocalTime[][] { { LocalTime.of(11, 0), LocalTime.of(16, 0) }})
                .expectDevice("HandheldDevice", "B5", 35, false,
                        new LocalTime[][] { { LocalTime.of(12, 0), LocalTime.of(16, 0) }})

                .expectSatellite("NasaSatellite", "Satellite1", 10000, 0, 85, new String[] {"A", "A1", "A2", "A3", "A4", "A5",
                        "B1", "B2", "B3", "B4", "B5"})
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("NasaSatellite", "Satellite1", 10000, 12.24, 85,
                        new String[] {"A", "A1", "A2", "A3", "A4", "A5", "B1", "B2", "B3", "B4", "B5"},
                        new DummyConnection[] {
                                new DummyConnection("A", LocalTime.of(0, 0), LocalTime.of(10, 1), 590),
                                new DummyConnection("A1", LocalTime.of(1, 0), LocalTime.of(8, 0), 410),
                                new DummyConnection("A2", LocalTime.of(2, 0), LocalTime.of(9, 0), 410),
                                new DummyConnection("A3", LocalTime.of(3, 0), LocalTime.of(10, 0), 410),
                                new DummyConnection("A4", LocalTime.of(4, 0), LocalTime.of(10, 1), 350),
                                new DummyConnection("A5", LocalTime.of(5, 0), LocalTime.of(10, 1), 290),
                                new DummyConnection("B1", LocalTime.of(8, 0), LocalTime.of(16, 1), 470),
                                new DummyConnection("B2", LocalTime.of(9, 0), LocalTime.of(16, 1), 410),
                                new DummyConnection("B3", LocalTime.of(10, 0), LocalTime.of(16, 1), 350),
                                new DummyConnection("B4", LocalTime.of(11, 0), LocalTime.of(16, 1), 290),
                                new DummyConnection("B5", LocalTime.of(12, 0), LocalTime.of(16, 1), 230),
                        })
                .expectDevice("LaptopDevice", "A", 36, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(10, 0) } })
                .expectDevice("LaptopDevice", "A1", 340, false,
                        new LocalTime[][] { { LocalTime.of(1, 0), LocalTime.of(10, 0) } })
                .expectDevice("LaptopDevice", "A2", 350, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(10, 0) } })
                .expectDevice("LaptopDevice", "A3", 0, false,
                        new LocalTime[][] { { LocalTime.of(3, 0), LocalTime.of(10, 0) } })
                .expectDevice("LaptopDevice", "A4", 10, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(10, 0) } })
                .expectDevice("LaptopDevice", "A5", 20, false,
                        new LocalTime[][] { { LocalTime.of(5, 0), LocalTime.of(10, 0) }})
                .expectDevice("HandheldDevice", "B1", 31, false,
                        new LocalTime[][] { { LocalTime.of(8, 0), LocalTime.of(16, 0) }})
                .expectDevice("HandheldDevice", "B2", 32, false,
                        new LocalTime[][] { { LocalTime.of(9, 0), LocalTime.of(16, 0) }})
                .expectDevice("HandheldDevice", "B3", 33, false,
                        new LocalTime[][] { { LocalTime.of(10, 0), LocalTime.of(16, 0) }})
                .expectDevice("HandheldDevice", "B4", 34, false,
                        new LocalTime[][] { { LocalTime.of(11, 0), LocalTime.of(16, 0) }})
                .expectDevice("HandheldDevice", "B5", 35, false,
                        new LocalTime[][] { { LocalTime.of(12, 0), LocalTime.of(16, 0) }})
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("LaptopDevice", "A", 36)
                .createDevice("LaptopDevice", "A1", 340)
                .createDevice("LaptopDevice", "A2", 350)
                .createDevice("LaptopDevice", "A3", 0)
                .createDevice("LaptopDevice", "A4", 10)
                .createDevice("LaptopDevice", "A5", 20)
                .createDevice("HandheldDevice", "B1", 31)
                .createDevice("HandheldDevice", "B2", 32)
                .createDevice("HandheldDevice", "B3", 33)
                .createDevice("HandheldDevice", "B4", 34)
                .createDevice("HandheldDevice", "B5", 35)
                .createSatellite("NasaSatellite", "Satellite1", 10000, 0)
                .scheduleDeviceActivation("A", LocalTime.of(0, 0), 600)
                .scheduleDeviceActivation("A1", LocalTime.of(1, 0), 540)
                .scheduleDeviceActivation("A2", LocalTime.of(2, 0), 480)
                .scheduleDeviceActivation("A3", LocalTime.of(3, 0), 420)
                .scheduleDeviceActivation("A4", LocalTime.of(4, 0), 360)
                .scheduleDeviceActivation("A5", LocalTime.of(5, 0), 300)
                .scheduleDeviceActivation("B1", LocalTime.of(8, 0), 480)
                .scheduleDeviceActivation("B2", LocalTime.of(9, 0), 420)
                .scheduleDeviceActivation("B3", LocalTime.of(10, 0), 360)
                .scheduleDeviceActivation("B4", LocalTime.of(11, 0), 300)
                .scheduleDeviceActivation("B5", LocalTime.of(12, 0), 240)
                .showWorldState(beginningWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }
}
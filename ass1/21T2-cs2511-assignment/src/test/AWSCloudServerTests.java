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
public class AWSCloudServerTests {
    @Test
    public void testExceedingMixed() {
        // Example created for testing SpaceXSatellite
        // Creates 1 satellite and 5 devices
        // Activates 5 of the devices twice and then schedules connections

        String beginningWorldState = new ResponseHelper(LocalTime.of(0, 0))

                .expectDevice("AWSCloudServer", "1AWS", 51, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "A1", 52, false,
                        new LocalTime[][] { { LocalTime.of(1, 0), LocalTime.of(7, 40) } })
                .expectDevice("DesktopDevice", "A2", 53, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(8, 40) } })
                .expectDevice("DesktopDevice", "A3", 54, false,
                        new LocalTime[][] { { LocalTime.of(3, 0), LocalTime.of(9, 40) } })
                .expectDevice("DesktopDevice", "A4", 55, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(10, 40) } })
                .expectDevice("DesktopDevice", "A5", 56, false,
                        new LocalTime[][] { { LocalTime.of(5, 0), LocalTime.of(11, 40) }})
                .expectDevice("DesktopDevice", "A6", 35, false,
                        new LocalTime[][] { { LocalTime.of(6, 0), LocalTime.of(12, 40) }})
                .expectSatellite("NasaSatellite", "SatelliteA", 10000, 40, 85, new String[] {"1AWS", "A1", "A2",
                        "A3", "A4", "A5", "A6"})
                .expectSatellite("SovietSatellite", "SatelliteB", 12000, 50, 100, new String[] {"1AWS", "A1", "A2",
                        "A3", "A4", "A5", "A6"})
                .expectSatellite("SovietSatellite", "SatelliteC", 12000, 60, 100, new String[] {"1AWS", "A1", "A2",
                        "A3", "A4", "A5", "A6"})
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectDevice("AWSCloudServer", "1AWS", 51, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(6, 40) } })
                .expectDevice("DesktopDevice", "A1", 52, false,
                        new LocalTime[][] { { LocalTime.of(1, 0), LocalTime.of(7, 40) } })
                .expectDevice("DesktopDevice", "A2", 53, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(8, 40) } })
                .expectDevice("DesktopDevice", "A3", 54, false,
                        new LocalTime[][] { { LocalTime.of(3, 0), LocalTime.of(9, 40) } })
                .expectDevice("DesktopDevice", "A4", 55, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(10, 40) } })
                .expectDevice("DesktopDevice", "A5", 56, false,
                        new LocalTime[][] { { LocalTime.of(5, 0), LocalTime.of(11, 40) }})
                .expectDevice("DesktopDevice", "A6", 35, false,
                        new LocalTime[][] { { LocalTime.of(6, 0), LocalTime.of(12, 40) }})
                .expectSatellite("NasaSatellite", "SatelliteA", 10000, 52.24, 85,
                        new String[] {"1AWS", "A1", "A2", "A3", "A4", "A5", "A6"},
                        new DummyConnection[] {
                                new DummyConnection("1AWS", LocalTime.of(0, 0), LocalTime.of(6, 0), 350),
                                new DummyConnection("A1", LocalTime.of(1, 0), LocalTime.of(7, 41), 390),
                                new DummyConnection("A2", LocalTime.of(2, 0), LocalTime.of(8, 41), 390),
                                new DummyConnection("A3", LocalTime.of(3, 0), LocalTime.of(9, 41), 390),
                                new DummyConnection("A4", LocalTime.of(4, 0), LocalTime.of(10, 41), 390),
                                new DummyConnection("A5", LocalTime.of(5, 0), LocalTime.of(11, 41), 390),
                                new DummyConnection("A6", LocalTime.of(6, 0), LocalTime.of(12, 41), 390),
                        })
                .expectSatellite("SovietSatellite", "SatelliteB", 12000, 62, 100,
                        new String[] {"1AWS", "A1", "A2", "A3", "A4", "A5", "A6"},
                        new DummyConnection[] {
                                new DummyConnection("1AWS", LocalTime.of(0, 0), LocalTime.of(6, 0), 350),
                                new DummyConnection("1AWS", LocalTime.of(6, 1), LocalTime.of(6, 41), 29),
                        })
                .expectSatellite("SovietSatellite", "SatelliteC", 12000, 72, 100,
                        new String[] {"1AWS", "A1", "A2", "A3", "A4", "A5", "A6"},
                        new DummyConnection[] {
                                new DummyConnection("1AWS", LocalTime.of(6, 1), LocalTime.of(6, 41), 29),
                        })
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("AWSCloudServer", "1AWS", 51)
                .createDevice("DesktopDevice", "A1", 52)
                .createDevice("DesktopDevice", "A2", 53)
                .createDevice("DesktopDevice", "A3", 54)
                .createDevice("DesktopDevice", "A4", 55)
                .createDevice("DesktopDevice", "A5", 56)
                .createDevice("DesktopDevice", "A6", 35)
                .createSatellite("NasaSatellite", "SatelliteA", 10000, 40)
                .createSatellite("SovietSatellite", "SatelliteB", 12000, 50)
                .createSatellite("SovietSatellite", "SatelliteC", 12000, 60)
                .scheduleDeviceActivation("1AWS", LocalTime.of(0, 0), 400)
                .scheduleDeviceActivation("A1", LocalTime.of(1, 0), 400)
                .scheduleDeviceActivation("A2", LocalTime.of(2, 0), 400)
                .scheduleDeviceActivation("A3", LocalTime.of(3, 0), 400)
                .scheduleDeviceActivation("A4", LocalTime.of(4, 0), 400)
                .scheduleDeviceActivation("A5", LocalTime.of(5, 0), 400)
                .scheduleDeviceActivation("A6", LocalTime.of(6, 0), 400)
                .showWorldState(beginningWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }
}
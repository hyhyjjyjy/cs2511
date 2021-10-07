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
public class BlueOriginSatelliteTests {
    @Test
    public void testExceedingLaptop() {
        // Example created for testing SpaceXSatellite
        // Creates 1 satellite and 5 devices
        // Activates 5 of the devices twice and then schedules connections

        String beginningWorldState = new ResponseHelper(LocalTime.of(0, 0))
                .expectDevice("LaptopDevice", "A", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(2, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(8, 0)} })
                .expectDevice("LaptopDevice", "B", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(2, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(8, 0)} })
                .expectDevice("LaptopDevice", "C", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(2, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(8, 0)} })
                .expectDevice("LaptopDevice", "D", 90, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(4, 0) },
                                { LocalTime.of(7, 0), LocalTime.of(9, 0)} })
                .expectDevice("LaptopDevice", "E", 110, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(6, 0) },
                                { LocalTime.of(8, 0), LocalTime.of(10, 0)} })
                .expectSatellite("BlueOriginSatellite", "Satellite1", 10000, 70, 141.66, new String[] {"A", "B", "C", "D", "E"})
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "Satellite1", 10000, 90.40, 141.66,
                        new String[] { "A", "B", "C", "D", "E" },
                        new DummyConnection[] {
                                new DummyConnection("A", LocalTime.of(0, 0), LocalTime.of(2, 1), 118),
                                new DummyConnection("B", LocalTime.of(0, 0), LocalTime.of(2, 1), 118),
                                new DummyConnection("C", LocalTime.of(0, 0), LocalTime.of(2, 1), 118),
                                new DummyConnection("D", LocalTime.of(2, 0), LocalTime.of(4, 1), 118),
                                new DummyConnection("E", LocalTime.of(4, 0), LocalTime.of(6, 1), 118),

                                new DummyConnection("A", LocalTime.of(6, 0), LocalTime.of(8, 1), 118),
                                new DummyConnection("B", LocalTime.of(6, 0), LocalTime.of(8, 1), 118),
                                new DummyConnection("C", LocalTime.of(6, 0), LocalTime.of(8, 1), 118),
                                new DummyConnection("D", LocalTime.of(7, 0), LocalTime.of(9, 1), 118),
                                new DummyConnection("E", LocalTime.of(8, 0), LocalTime.of(10, 1), 118),
                        })
                .expectDevice("LaptopDevice", "A", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(2, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(8, 0)} })
                .expectDevice("LaptopDevice", "B", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(2, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(8, 0)} })
                .expectDevice("LaptopDevice", "C", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(2, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(8, 0)} })
                .expectDevice("LaptopDevice", "D", 90, false,
                        new LocalTime[][] { { LocalTime.of(2, 0), LocalTime.of(4, 0) },
                                { LocalTime.of(7, 0), LocalTime.of(9, 0)} })
                .expectDevice("LaptopDevice", "E", 110, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(6, 0) },
                                { LocalTime.of(8, 0), LocalTime.of(10, 0)} })
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("LaptopDevice", "A", 30)
                .createDevice("LaptopDevice", "B", 50)
                .createDevice("LaptopDevice", "C", 70)
                .createDevice("LaptopDevice", "D", 90)
                .createDevice("LaptopDevice", "E", 110)
                .createSatellite("BlueOriginSatellite", "Satellite1", 10000, 70)
                .scheduleDeviceActivation("A", LocalTime.of(0, 0), 120)
                .scheduleDeviceActivation("B", LocalTime.of(0, 0), 120)
                .scheduleDeviceActivation("C", LocalTime.of(0, 0), 120)
                .scheduleDeviceActivation("D", LocalTime.of(2, 0), 120)
                .scheduleDeviceActivation("E", LocalTime.of(4, 0), 120)
                .scheduleDeviceActivation("A", LocalTime.of(6, 0), 120)
                .scheduleDeviceActivation("B", LocalTime.of(6, 0), 120)
                .scheduleDeviceActivation("C", LocalTime.of(6, 0), 120)
                .scheduleDeviceActivation("D", LocalTime.of(7, 0), 120)
                .scheduleDeviceActivation("E", LocalTime.of(8, 0), 120)
                .showWorldState(beginningWorldState)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

    @Test
    public void testExceedingDesktop() {
        // Example created for testing SpaceXSatellite
        // Creates 1 satellite and 5 devices
        // Activates 5 of the devices twice and then schedules connections

        String beginningWorldState2 = new ResponseHelper(LocalTime.of(0, 0))
                .expectDevice("DesktopDevice", "A", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(11, 0)} })
                .expectDevice("DesktopDevice", "A1", 34, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(11, 0)} })
                .expectDevice("DesktopDevice", "A2", 38, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(11, 0)} })
                .expectDevice("DesktopDevice", "B", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(11, 0)} })
                .expectDevice("DesktopDevice", "C", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(11, 0)} })
                .expectDevice("DesktopDevice", "D", 90, false,
                        new LocalTime[][] { { LocalTime.of(1, 0), LocalTime.of(6, 0) },
                                { LocalTime.of(7, 0), LocalTime.of(12, 0)} })
                .expectDevice("DesktopDevice", "E", 110, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(9, 0) },
                                { LocalTime.of(10, 0), LocalTime.of(15, 0)} })
                .expectSatellite("BlueOriginSatellite", "Satellite1", 10000, 70, 141.66, new String[] {"A", "A1", "A2", "B", "C", "D", "E"})
                .toString();

        // then simulates for a full day (1440 mins)
        String afterADay = new ResponseHelper(LocalTime.of(0, 0))
                .expectSatellite("BlueOriginSatellite", "Satellite1", 10000, 90.40, 141.66,
                        new String[] { "A", "A1", "A2", "B", "C", "D", "E" },
                        new DummyConnection[] {
                                new DummyConnection("A", LocalTime.of(0, 0), LocalTime.of(5, 1), 295),
                                new DummyConnection("A1", LocalTime.of(0, 0), LocalTime.of(5, 1), 295),
                                new DummyConnection("D", LocalTime.of(5, 1), LocalTime.of(6, 1), 54),
                                new DummyConnection("E", LocalTime.of(5, 1), LocalTime.of(9, 1), 234),
                                new DummyConnection("A", LocalTime.of(6, 1), LocalTime.of(11, 1), 294),
                                new DummyConnection("A1", LocalTime.of(9, 1), LocalTime.of(11, 1), 114),
                                new DummyConnection("D", LocalTime.of(11, 1), LocalTime.of(12, 1), 54),
                                new DummyConnection("E", LocalTime.of(11, 1), LocalTime.of(15, 1), 234),
                        })
                .expectDevice("DesktopDevice", "A", 30, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(11, 0)} })
                .expectDevice("DesktopDevice", "A1", 34, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(11, 0)} })
                .expectDevice("DesktopDevice", "A2", 38, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(11, 0)} })
                .expectDevice("DesktopDevice", "B", 50, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(11, 0)} })
                .expectDevice("DesktopDevice", "C", 70, false,
                        new LocalTime[][] { { LocalTime.of(0, 0), LocalTime.of(5, 0) },
                                { LocalTime.of(6, 0), LocalTime.of(11, 0)} })
                .expectDevice("DesktopDevice", "D", 90, false,
                        new LocalTime[][] { { LocalTime.of(1, 0), LocalTime.of(6, 0) },
                                { LocalTime.of(7, 0), LocalTime.of(12, 0)} })
                .expectDevice("DesktopDevice", "E", 110, false,
                        new LocalTime[][] { { LocalTime.of(4, 0), LocalTime.of(9, 0) },
                                { LocalTime.of(10, 0), LocalTime.of(15, 0)} })
                .toString();

        TestHelper plan = new TestHelper()
                .createDevice("DesktopDevice", "A", 30)
                .createDevice("DesktopDevice", "A1", 34)
                .createDevice("DesktopDevice", "A2", 38)
                .createDevice("DesktopDevice", "B", 50)
                .createDevice("DesktopDevice", "C", 70)
                .createDevice("DesktopDevice", "D", 90)
                .createDevice("DesktopDevice", "E", 110)
                .createSatellite("BlueOriginSatellite", "Satellite1", 10000, 70)
                .scheduleDeviceActivation("A", LocalTime.of(0, 0), 300)
                .scheduleDeviceActivation("A1", LocalTime.of(0, 0), 300)
                .scheduleDeviceActivation("A2", LocalTime.of(0, 0), 300)
                .scheduleDeviceActivation("B", LocalTime.of(0, 0), 300)
                .scheduleDeviceActivation("C", LocalTime.of(0, 0), 300)
                .scheduleDeviceActivation("D", LocalTime.of(1, 0), 300)
                .scheduleDeviceActivation("E", LocalTime.of(4, 0), 300)

                .scheduleDeviceActivation("A", LocalTime.of(6, 0), 300)
                .scheduleDeviceActivation("A1", LocalTime.of(6, 0), 300)
                .scheduleDeviceActivation("A2", LocalTime.of(6, 0), 300)
                .scheduleDeviceActivation("B", LocalTime.of(6, 0), 300)
                .scheduleDeviceActivation("C", LocalTime.of(6, 0), 300)
                .scheduleDeviceActivation("D", LocalTime.of(7, 0), 300)
                .scheduleDeviceActivation("E", LocalTime.of(10, 0), 300)
                .showWorldState(beginningWorldState2)
                .simulate(1440)
                .showWorldState(afterADay);
        plan.executeTestPlan();
    }

}
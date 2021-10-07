package unsw.blackout;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

public class LaptopDevice extends Device {

    public LaptopDevice(String id, boolean isConnected, double position, String type, ArrayList<ActivationPeriod> activationPeriods) {
        super(id, isConnected, position, type, activationPeriods);
    }
}

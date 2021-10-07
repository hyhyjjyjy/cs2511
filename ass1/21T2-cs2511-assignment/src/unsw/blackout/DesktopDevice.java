package unsw.blackout;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

public class DesktopDevice extends Device {
    public DesktopDevice() {
    }

    public DesktopDevice(String id, boolean isConnected, double position,
                         String type, ArrayList<ActivationPeriod> activationPeriods) {
        super(id, isConnected, position, type, activationPeriods);
    }
}

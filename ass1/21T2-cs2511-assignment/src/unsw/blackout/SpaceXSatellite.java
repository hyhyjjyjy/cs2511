package unsw.blackout;

import java.time.LocalTime;
import java.util.ArrayList;

public class SpaceXSatellite extends Satellite {

    public SpaceXSatellite(ArrayList<SuccessfulConnection> connections, double height, String id, double position, String type, double velocity) {
        super(connections, height, id, position, type, velocity);
    }

    @Override
    public boolean acceptDevice(Device device) {
        return device.getType().compareTo("HandheldDevice") == 0
                || device.getType().compareTo("MobileXPhone") == 0;
    }

    @Override
    public int getWaitingMinutes(String type) {
        return 0;
    }

    @Override
    public void addCurrentConnection(Device device, LocalTime currentTime) {
        ArrayList<Device> currentConnections = getCurrentConnection();
        currentConnections.add(device);
        setCurrentConnection(currentConnections);
    }

    @Override
    public void removeCurrentConnection(Device device) {
        ArrayList<Device> currentConnections = getCurrentConnection();
        currentConnections.remove(device);
        setCurrentConnection(currentConnections);
    }
}
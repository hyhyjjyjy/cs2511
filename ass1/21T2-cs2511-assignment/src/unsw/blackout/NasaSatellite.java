package unsw.blackout;

import java.time.LocalTime;
import java.util.ArrayList;

public class NasaSatellite extends Satellite {
    private int totalDevice = 0;

    public NasaSatellite(ArrayList<SuccessfulConnection> connections, double height, String id, double position, String type, double velocity) {
        super(connections, height, id, position, type, velocity);
    }

    @Override
    public boolean acceptDevice(Device device) {
        if (totalDevice < 6)
            return true;
        if (totalDevice == 6) {
            if (device.getPosition() > 40 || device.getPosition() < 30)
                return false;
            for (Device connectedDevice: this.getCurrentConnection()) {
                if (connectedDevice.getPosition() > 40 || connectedDevice.getPosition() < 30)
                    return true;
            }
        }
        return false;
    }

    @Override
    public int getWaitingMinutes(String type) {
        return 10;
    }

    @Override
    public void addCurrentConnection(Device device, LocalTime currentTime) {
        ArrayList <Device> currentConnections = getCurrentConnection();
        if (totalDevice < 6) {
            currentConnections.add(device);
            setCurrentConnection(currentConnections);
            totalDevice++;
        }
        else {
            for (int i = 0; i < 6; i++) {
                Device deviceNeedRemoving = currentConnections.get(i);
                if (deviceNeedRemoving.getPosition() > 40 || deviceNeedRemoving.getPosition() < 30) {
                    deviceNeedRemoving.disconnectByForce(currentTime);
                    currentConnections.add(device);
                    totalDevice++;
                    setCurrentConnection(currentConnections);
                    return;
                }
            }
        }
    }

    @Override
    public void removeCurrentConnection(Device device) {
        ArrayList<Device> currentConnections = getCurrentConnection();
        currentConnections.remove(device);
        setCurrentConnection(currentConnections);
        totalDevice--;
    }
}

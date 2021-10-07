package unsw.blackout;

import java.time.LocalTime;
import java.util.ArrayList;

public class SovietSatellite extends Satellite {

    private int totalDevice = 0;

    @Override
    public double getVelocity() {
        if (isAntiClockwise())
            return super.getVelocity();
        return -super.getVelocity();
    }

    public SovietSatellite(ArrayList<SuccessfulConnection> connections, double height,
                           String id, double position, String type, double velocity) {
        super(connections, height, id, position, type, velocity);
        this.setAntiClockwise(true);
        if ((this.getPosition() >= 0 && this.getPosition() < 140)
                || (this.getPosition() >= 345 && this.getPosition() < 360)) {
            this.setAntiClockwise(true);
        }
        else if ((this.getPosition() > 190 && this.getPosition() < 345))
            this.setAntiClockwise(false);
    }

    @Override
    public void updatePosition(int minutes) {

        if ((this.getPosition() >= 0 && this.getPosition() < 140)
                || (this.getPosition() >= 345 && this.getPosition() < 360)) {
            this.setAntiClockwise(true);
        }
        else if ((this.getPosition() > 190 && this.getPosition() < 345))
            this.setAntiClockwise(false);

        super.updatePosition(minutes);
    }

    @Override
    public boolean acceptDevice(Device device) {
        return device.getType().compareTo("DesktopDevice") == 0
                || device.getType().compareTo("LaptopDevice") == 0
                || device.getType().compareTo("AWSCloudServer") == 0;
    }

    @Override
    public int getWaitingMinutes(String type) {
        if (type.compareTo("LaptopDevice") == 0)
            return 4;
        return 10;
    }

    @Override
    public void addCurrentConnection(Device device, LocalTime currentTime) {
        ArrayList <Device> currentConnections = getCurrentConnection();
        if (totalDevice < 9)
            totalDevice++;
        else {
            Device deviceNeedRemoving = currentConnections.get(0);
            currentConnections.remove(0);
            deviceNeedRemoving.disconnectByForce(currentTime);
        }
        currentConnections.add(device);
        setCurrentConnection(currentConnections);
    }

    @Override
    public void removeCurrentConnection(Device device) {
        ArrayList<Device> currentConnections = getCurrentConnection();
        currentConnections.remove(device);
        setCurrentConnection(currentConnections);
        totalDevice--;
    }
}
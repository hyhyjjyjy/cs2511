package unsw.blackout;

import java.time.LocalTime;
import java.util.ArrayList;

public class BlueOriginSatellite extends Satellite {
    private int laptopDevice = 0;
    private int desktopDevice = 0;
    private int totalDevice = 0;

    public BlueOriginSatellite(ArrayList<SuccessfulConnection> connections, double height, String id, double position, String type, double velocity) {
        super(connections, height, id, position, type, velocity);
    }

    @Override
    public boolean acceptDevice(Device device) {
        switch (device.getType()) {
            case "HandheldDevice":
            case "MobileXPhone":
                if (totalDevice >= 10)
                    return false;
                break;
            case "DesktopDevice":
            case  "AWSCloudServer":
                if (desktopDevice >= 2 || totalDevice >= 10)
                    return false;
                break;
            case "LaptopDevice":
                if (laptopDevice >= 5 || totalDevice >= 10)
                    return false;
                break;
        }
        return true;
    }

    public int getDesktopDevice() {
        return desktopDevice;
    }

    @Override
    public int getWaitingMinutes(String type) {
        switch (type) {
            case "HandheldDevice":
            case "MobileXPhone":
                return 1;
            case "LaptopDevice":
                return 2;
            case "DesktopDevice":
            case "AWSCloudServer":
                return 5;

        }
        return 0;
    }

    @Override
    public void addCurrentConnection(Device device, LocalTime currentTime) {
        ArrayList <Device> currentConnections = getCurrentConnection();
        currentConnections.add(device);
        setCurrentConnection(currentConnections);
        totalDevice ++;
        switch (device.getType()) {
            case "HandheldDevice":
            case  "MobileXPhone":
                break;
            case "LaptopDevice":
                laptopDevice ++;
                break;
            case "DesktopDevice":
            case "AWSCloudServer":
                desktopDevice ++;
                break;
        }
    }

    @Override
    public void removeCurrentConnection(Device device) {
        ArrayList<Device> currentConnections = getCurrentConnection();
        currentConnections.remove(device);
        setCurrentConnection(currentConnections);
        totalDevice --;
        switch (device.getType()) {
            case "HandheldDevice":
            case "MobileXPhone":
                break;
            case "LaptopDevice":
                laptopDevice --;
                break;
            case "DesktopDevice":
            case "AWSCloudServer":
                desktopDevice --;
                break;
        }

    }

}

package unsw.blackout;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Satellite {
    private final ArrayList<SuccessfulConnection> connections;
    private final SatelliteInfo satelliteInfo;
    private boolean isAntiClockwise  = true;
    private ArrayList<Device> currentConnection = new ArrayList<>();

    public Satellite(ArrayList<SuccessfulConnection> connections, double height,
                     String id, double position,
                     String type, double velocity) {
        this.satelliteInfo = new SatelliteInfo(height, position,
                velocity, velocity / height, type, id);

        this.connections = connections;
    }

    public ArrayList<SuccessfulConnection> getConnections() {
        return connections;
    }

    public ArrayList<Device> getCurrentConnection() {
        return currentConnection;
    }

    public double getHeight() { return satelliteInfo.getHeight(); }

    public double getAngularVelocity() {
        return satelliteInfo.getAngularVelocity();
    }

    public String getId() {
        return satelliteInfo.getId();
    }

    public double getPosition() {
        return satelliteInfo.getPosition();
    }

    public String getType() {
        return satelliteInfo.getType();
    }

    public double getVelocity() {
        return satelliteInfo.getVelocity();
    }

    public void setId(String id) { satelliteInfo.setId(id); }

    public void setPosition(double position) { satelliteInfo.setPosition(position); }

    public void setType(String type) { satelliteInfo.setType(type); }

    public void setCurrentConnection(ArrayList<Device> currentConnection) {
        this.currentConnection = currentConnection;
    }

    public void setAntiClockwise(boolean antiClockwise) {
        isAntiClockwise = antiClockwise;
    }

    public boolean isAntiClockwise() {
        return isAntiClockwise;
    }

    public void addConnection(SuccessfulConnection successfulConnection) {
        connections.add(successfulConnection);
        connections.sort(Comparator.comparing(SuccessfulConnection::getStartTime)
                .thenComparing(SuccessfulConnection::getDeviceId));
    }

    public void updatePosition(int minutes) {
        if (isAntiClockwise)
            this.setPosition((this.getAngularVelocity() * minutes + this.getPosition()) % 360);
        else
            this.setPosition((this.getPosition() - this.getAngularVelocity() * minutes + 360) % 360);
    }

    public abstract boolean acceptDevice(Device device);

    public abstract int getWaitingMinutes(String type);

    public abstract void addCurrentConnection(Device device, LocalTime currentTime);

    public abstract void removeCurrentConnection(Device device);

}








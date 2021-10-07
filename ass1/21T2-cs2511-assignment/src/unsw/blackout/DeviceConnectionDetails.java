package unsw.blackout;

import java.time.LocalTime;

public class DeviceConnectionDetails {
    private boolean isConnecting = false;
    private boolean isConnected = false;
    private int activeMinutes = 0;
    private Satellite currentConnectingSatellite;

    private LocalTime startTime = LocalTime.of(0,0);
    private LocalTime realStartTime = LocalTime.of(0,0);

    public DeviceConnectionDetails() {
        currentConnectingSatellite = null;
    }

    public DeviceConnectionDetails(boolean isConnecting, boolean isConnected,
                                   int activeMinutes, Satellite currentConnectingSatellite,
                                   LocalTime startTime, LocalTime realStartTime) {
        this.isConnecting = isConnecting;
        this.activeMinutes = activeMinutes;
        this.currentConnectingSatellite = currentConnectingSatellite;
        this.startTime = startTime;
        this.realStartTime = realStartTime;
    }

    public boolean isConnecting() { return isConnecting; }

    public int getActiveMinutes() { return activeMinutes; }

    public Satellite getCurrentConnectingSatellite() { return currentConnectingSatellite; }

    public LocalTime getStartTime() { return startTime; }

    public LocalTime getRealStartTime() { return realStartTime; }

    public boolean isConnected() { return isConnected; }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void setConnecting(boolean connecting) {
        isConnecting = connecting;
    }

    public void setActiveMinutes(int activeMinutes) { this.activeMinutes = activeMinutes; }

    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public void setRealStartTime(LocalTime realStartTime) { this.realStartTime = realStartTime; }

    public void setCurrentConnectingSatellite(Satellite currentConnectingSatellite) { this.currentConnectingSatellite = currentConnectingSatellite; }

    public void finishConnecting(LocalTime currentTime) {
        if (this.isConnecting()) {
            if (currentTime.isAfter(this.getRealStartTime())) {
                this.setConnected(true);
                this.setConnecting(false);
            }
        }
    }

    public void setUpConnection(LocalTime currentTime, Satellite satellite, Device device) {
        this.setConnecting(true);
        int minutes = satellite.getWaitingMinutes(device.getType());
        this.setCurrentConnectingSatellite(satellite);
        this.setRealStartTime(currentTime.plusMinutes(minutes));
        this.setStartTime(currentTime);
        satellite.addCurrentConnection(device, currentTime);
    }

}

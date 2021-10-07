package unsw.blackout;

import java.time.LocalTime;

public class SuccessfulConnection {
    private final String satelliteId;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String deviceId;
    private final int minutesActive;

    public SuccessfulConnection(String satelliteId,
                                LocalTime startTime, LocalTime endTime,
                                String deviceId, int minutesActive) {
        this.satelliteId = satelliteId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deviceId = deviceId;
        this.minutesActive = minutesActive;
    }

    public String getSatelliteId() {
        return satelliteId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getMinutesActive() {
        return minutesActive;
    }
}

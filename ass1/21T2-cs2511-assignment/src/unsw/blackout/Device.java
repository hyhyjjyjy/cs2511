package unsw.blackout;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public abstract class Device{
    private DeviceInfo deviceInfo;
    private final ArrayList<ActivationPeriod> activationPeriods;
    private final DeviceConnectionDetails connectionDetails = new DeviceConnectionDetails();

    public Device() {
        this.deviceInfo = new DeviceInfo("", 0, "");
        this.activationPeriods = new ArrayList<>();
    }

    public Device(String id, boolean isConnected, double position, String type,
                  ArrayList<ActivationPeriod> activationPeriods) {
        this.deviceInfo = new DeviceInfo(id, position, type);
        connectionDetails.setConnected(isConnected);
        this.activationPeriods = activationPeriods;
    }

    public double getPosition() {
        return deviceInfo.getPosition();
    }

    public String getId() {
        return deviceInfo.getId();
    }

    public String getType() {
        return deviceInfo.getType();
    }

    public ArrayList<ActivationPeriod> getActivationPeriods() {
        return activationPeriods;
    }

    public void setPosition(double position) { deviceInfo.setPosition(position); }

    public DeviceConnectionDetails getConnectionDetails() {
        return connectionDetails;
    }

    public boolean isConnected() {
        return connectionDetails.isConnected() || connectionDetails.isConnecting();
    }

    public void addActivationPeriods(LocalTime startTime, LocalTime endTime) {
        ActivationPeriod timePeriod = new ActivationPeriod(startTime, endTime);
        activationPeriods.add(timePeriod);
        // sort the period by startTime
        activationPeriods.sort(Comparator.comparing(ActivationPeriod::getStartTime));
    }

    public boolean isVisible(Satellite satellite) {
        return MathsHelper.satelliteIsVisibleFromDevice(satellite.getPosition(), satellite.getHeight(), this.getPosition());
    }

    public ArrayList<Satellite> helpFindPossibleSatellite(ArrayList<Satellite> satellites) {
        ArrayList<Satellite> possibleSates = new ArrayList<>();

        //Find possible satellite
        for (Satellite satellite : satellites) {
            //is visible
            if (!isVisible(satellite))
                continue;

            //satellite can accept device
            if (!satellite.acceptDevice(this)) {
                continue;
            }

            //find all min-distance satellite if needed
            possibleSates.add(satellite);
        }
        return possibleSates;
    }

    public ArrayList<Satellite> doFindAppropriateSatellite(ArrayList<Satellite> satellites, LocalTime currentTime) {
        if (!inTimePeriod(currentTime))
            return null;

        ArrayList<Satellite> possibleSates = helpFindPossibleSatellite(satellites);

        if (possibleSates.size() == 0)
            return null;
        if (possibleSates.size() == 1)
            return possibleSates;

        Satellite minAngleSatellite = null;
        double minAngle = 360;
        for (Satellite satellite : possibleSates) {
            if (satellite.getPosition() < minAngle) {
                minAngle = satellite.getPosition();
                minAngleSatellite = satellite;
            }
        }
        possibleSates.clear();
        possibleSates.add(minAngleSatellite);
        return possibleSates;
    }

    public ArrayList<Satellite> findAppropriateSatellite(ArrayList<Satellite> satellites, LocalTime currentTime) {
        //is not connected
        if (connectionDetails.isConnected() || connectionDetails.isConnecting())
            return null;

        return doFindAppropriateSatellite(satellites, currentTime);
    }

    public boolean inTimePeriod(LocalTime currentTime) {
        if (activationPeriods.isEmpty())
            return false;
        for (ActivationPeriod activationPeriod: activationPeriods) {
            if (activationPeriod.getEndTime().plusMinutes(1).isAfter(currentTime)
                    && !activationPeriod.getStartTime().isAfter(currentTime))
                return true;
        }
        return false;
    }

    public int inWhichTimePeriod(LocalTime currentTime) {
        int i = 0;
        for (ActivationPeriod activationPeriod: activationPeriods) {
            if ( !currentTime.isAfter(activationPeriod.getEndTime().plusMinutes(1))
                    && !activationPeriod.getStartTime().isAfter(currentTime))
                return i;
            i++;
        }
        return i - 1;
    }

    public void updateActivePeriod(LocalTime currentTime) {
        if (connectionDetails.isConnected())
            connectionDetails.setActiveMinutes(
                    (int) Duration.between(connectionDetails.getRealStartTime(), currentTime).toMinutes());
    }

    public void setUpConnection(ArrayList<Satellite> appropriateSatellites, LocalTime currentTime) {
        Satellite satellite = appropriateSatellites.get(0);
        if (connectionDetails.isConnected() || connectionDetails.isConnecting())
            return;
        connectionDetails.setUpConnection(currentTime, satellite, this);
    }

    public void finishConnecting(LocalTime currentTime) {
        connectionDetails.finishConnecting(currentTime);
    }

    public void disconnect(LocalTime currentTime, ArrayList<Satellite> satellites) {
        int index = inWhichTimePeriod(currentTime);
        int minPlus = 0;

        // check if it is out of activation time or it's not visible
        if(connectionDetails.isConnected()) {
            if (!activationPeriods.get(index).getEndTime().plusMinutes(1).isAfter(currentTime))
                minPlus = 0;
            else if (isVisible(getConnectionDetails().getCurrentConnectingSatellite()))
                return;
        }
        else
            return;

        dropConnection(connectionDetails, currentTime.plusMinutes(minPlus), 1);
    }

    public void dropConnection(DeviceConnectionDetails deviceConnectionDetails, LocalTime endTime, int minuteMinus) {
        deviceConnectionDetails.setConnected(false);
        Satellite currentConnectingSatellite = deviceConnectionDetails.getCurrentConnectingSatellite();
        deviceConnectionDetails.setActiveMinutes(
                (int) Duration.between(deviceConnectionDetails.getRealStartTime(), endTime.minusMinutes(minuteMinus)).toMinutes());

        SuccessfulConnection newConnection = new SuccessfulConnection(currentConnectingSatellite.getId() ,
                deviceConnectionDetails.getStartTime(),
                endTime, getId(), deviceConnectionDetails.getActiveMinutes());
        currentConnectingSatellite.addConnection(newConnection);
        currentConnectingSatellite.removeCurrentConnection(this);
        deviceConnectionDetails.setActiveMinutes(0);
    }

    public void disconnectByForce(LocalTime currentTime) {
        dropConnection(connectionDetails, currentTime, 0);
    }

}
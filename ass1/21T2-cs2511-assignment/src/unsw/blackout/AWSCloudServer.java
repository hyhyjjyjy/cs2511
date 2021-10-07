package unsw.blackout;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class AWSCloudServer extends DesktopDevice  {

    private final DeviceConnectionDetails secondConnectionDetails = new DeviceConnectionDetails();

    public DeviceConnectionDetails getSecondConnectionDetails() {
        return secondConnectionDetails;
    }

    public AWSCloudServer(String id, boolean isConnected, double position,
                          String type, ArrayList<ActivationPeriod> activationPeriods) {
        super(id, isConnected, position, type, activationPeriods);
    }

    @Override
    public void updateActivePeriod(LocalTime currentTime) {
        if (getConnectionDetails().isConnected())
            getConnectionDetails().setActiveMinutes(
                    (int) Duration.between(getConnectionDetails().getRealStartTime(), currentTime).toMinutes());
        if (getSecondConnectionDetails().isConnected())
            getSecondConnectionDetails().setActiveMinutes(
                    (int) Duration.between(getSecondConnectionDetails().getRealStartTime(), currentTime).toMinutes());
    }

    @Override
    public ArrayList<Satellite> doFindAppropriateSatellite(ArrayList<Satellite> satellites, LocalTime currentTime) {
        //in time period
        if (!inTimePeriod(currentTime))
            return null;

        ArrayList<Satellite> possibleSates = helpFindPossibleSatellite(satellites);

        if (possibleSates.size() <= 1)
            return null;

        if (possibleSates.size() == 2)
            return possibleSates;

        Satellite minAngleSatellite = null;
        double minAngle1 = 360;
        for (Satellite satellite : possibleSates) {
            if (satellite.getPosition() < minAngle1) {
                minAngle1 = satellite.getPosition();
                minAngleSatellite = satellite;
            }
        }

        ArrayList <Satellite> awsPossibleSatellite = new ArrayList<>();
        awsPossibleSatellite.add(minAngleSatellite);

        double minAngle2 = 360;
        for (Satellite satellite : possibleSates) {
            if (satellite.getPosition() < minAngle2 && satellite.getPosition() != minAngle1) {
                minAngle2 = satellite.getPosition();
                minAngleSatellite = satellite;
            }
        }
        awsPossibleSatellite.add(minAngleSatellite);
        return awsPossibleSatellite;
    }

    @Override
    public ArrayList<Satellite> findAppropriateSatellite(ArrayList<Satellite> satellites, LocalTime currentTime) {

        //is not connected
        if (getConnectionDetails().isConnected() || getSecondConnectionDetails().isConnected()
                || getConnectionDetails().isConnecting() || getSecondConnectionDetails().isConnecting())
            return null;

        return doFindAppropriateSatellite(satellites, currentTime);
    }

    @Override
    public void setUpConnection(ArrayList<Satellite> appropriateSatellites, LocalTime currentTime) {
        Satellite satellite1 = appropriateSatellites.get(0);
        Satellite satellite2 = appropriateSatellites.get(1);

        if (getConnectionDetails().isConnecting() || getConnectionDetails().isConnected()
                || getSecondConnectionDetails().isConnected() || getSecondConnectionDetails().isConnecting())
            return;

        //satellite 1
        getConnectionDetails().setUpConnection(currentTime, satellite1, this);

        //satellite 2
        getSecondConnectionDetails().setUpConnection(currentTime, satellite2, this);

    }

    @Override
    public void finishConnecting(LocalTime currentTime) {
        getConnectionDetails().finishConnecting(currentTime);
        getSecondConnectionDetails().finishConnecting(currentTime);
    }

    @Override
    public void disconnect(LocalTime currentTime, ArrayList<Satellite> satellites) {
        int index = inWhichTimePeriod(currentTime);

        // 1.both are connected
        // 2.it's out of device's active time
        // 3.one of the satellites are out of range
        if ((getConnectionDetails().isConnected() && getSecondConnectionDetails().isConnected())) {
            if (getActivationPeriods().get(index).getEndTime().plusMinutes(1).isAfter(currentTime)) {
                boolean firstOutOf = !isVisible(getConnectionDetails().getCurrentConnectingSatellite());
                boolean secondOutOf = !isVisible(getSecondConnectionDetails().getCurrentConnectingSatellite());
                if (!firstOutOf && !secondOutOf) {
                    return;
                }
                else if ((firstOutOf || secondOutOf) && !(firstOutOf && secondOutOf)){
                    ArrayList<Satellite> appropriateSatellites = super.doFindAppropriateSatellite(satellites, currentTime);
                    if (appropriateSatellites != null) {
                        Satellite satellite = appropriateSatellites.get(0);
                        if (firstOutOf) {
                            dropConnection(getConnectionDetails(), currentTime, 1);
                            getConnectionDetails().setUpConnection(currentTime, satellite, this);
                        }
                        else {
                            dropConnection(getSecondConnectionDetails(), currentTime, 1);
                            getSecondConnectionDetails().setUpConnection(currentTime, satellite, this);
                        }
                        return;
                    }
                }
            }
        }
        else
            return;

        // put connection into satellite1
        dropConnection(getConnectionDetails(), currentTime, 1);
        // put connection into satellite2
        dropConnection(getSecondConnectionDetails(), currentTime, 1);

    }

    public void disconnectByForce(LocalTime currentTime) {
        dropConnection(getConnectionDetails(), currentTime, 0);
        dropConnection(getSecondConnectionDetails(), currentTime, 0);
    }




}

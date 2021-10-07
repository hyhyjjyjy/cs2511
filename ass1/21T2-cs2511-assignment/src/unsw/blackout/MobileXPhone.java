package unsw.blackout;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

public class MobileXPhone extends HandheldDevice{

    public MobileXPhone(String id, boolean isConnected, double position, String type, ArrayList<ActivationPeriod> activationPeriods) {
        super(id, isConnected, position, type, activationPeriods);
    }

    @Override
    public ArrayList<Satellite> findAppropriateSatellite(ArrayList<Satellite> satellites, LocalTime currentTime) {

        //is not connected
        if (isConnected() || getConnectionDetails().isConnecting())
            return null;

        //in time period
        if (!inTimePeriod(currentTime))
            return null;


        ArrayList<Satellite> possibleSpaceXSatellites = new ArrayList<>();

        //Find possible SpaceXSatellite
        for (Satellite satellite : satellites) {

            //only try to find SpaceXSatellite first
            if (satellite.getType().compareTo("SpaceXSatellite") != 0)
                continue;

            //is visible
            if (!MathsHelper.satelliteIsVisibleFromDevice(satellite.getPosition(),
                    satellite.getHeight(),
                    this.getPosition()))
                continue;

            //satellite can accept device
            if (!satellite.acceptDevice(this)) {
                continue;
            }

            //find all min-distance satellite if needed
            possibleSpaceXSatellites.add(satellite);
        }

        if (possibleSpaceXSatellites.size() != 0) {


            if (possibleSpaceXSatellites.size() == 1)
                return possibleSpaceXSatellites;

            Satellite minAngleSatellite = null;
            double minAngle = 360;
            for (Satellite satellite : possibleSpaceXSatellites) {
                if (satellite.getPosition() < minAngle) {
                    minAngle = satellite.getPosition();
                    minAngleSatellite = satellite;
                }
            }
            possibleSpaceXSatellites.clear();
            possibleSpaceXSatellites.add(minAngleSatellite);
            return possibleSpaceXSatellites;
        }

        return super.findAppropriateSatellite(satellites, currentTime);

    }
}

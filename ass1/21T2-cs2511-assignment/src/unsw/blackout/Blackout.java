package unsw.blackout;

import java.time.LocalTime;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Blackout {
    private final ArrayList<Device> devices = new ArrayList<>();
    private final ArrayList<Satellite> satellites = new ArrayList<>();
    private LocalTime currentTime;

    public Blackout() {
        this.currentTime = LocalTime.of(0,0);
    }


    public void createDevice(String id, String type, double position) {
        ArrayList<ActivationPeriod> activationPeriods = new ArrayList<>();
        Device newDevice = null;
        switch (type) {
            case "HandheldDevice":
                newDevice = new HandheldDevice(id, false, position, type, activationPeriods);
                break;
            case "LaptopDevice":
                newDevice = new LaptopDevice(id, false, position, type, activationPeriods);
                break;
            case "DesktopDevice":
                newDevice = new DesktopDevice(id, false, position, type, activationPeriods);
                break;
            case "MobileXPhone":
                newDevice = new MobileXPhone(id, false, position, type, activationPeriods);
                break;
            case  "AWSCloudServer":
                newDevice = new AWSCloudServer(id, false, position, type, activationPeriods);

        }
        this.devices.add(newDevice);
        devices.sort(Comparator.comparing(Device::getId));
    }

    public void createSatellite(String id, String type, double height, double position) {

        ArrayList<SuccessfulConnection> connections = new ArrayList<>();
        double velocity;
        switch (type) {
            case "SpaceXSatellite" :
                velocity = 3330.0 / 60;
                break;
            case "BlueOriginSatellite":
                velocity = 8500.0 / 60;
                break;
            case "NasaSatellite":
                velocity = 5100.0 / 60;
                break;
            case "SovietSatellite":
                velocity = 6000.0 / 60;
                break;
            default :
                velocity = 0;
        }

        Satellite newSatellite;
        switch (type) {
            case "SpaceXSatellite":
                newSatellite = new SpaceXSatellite(connections, height, id, position, type, velocity);
                break;
            case "BlueOriginSatellite":
                newSatellite = new BlueOriginSatellite(connections, height, id, position, type, velocity);
                break;
            case "NasaSatellite":
                newSatellite = new NasaSatellite(connections, height, id, position, type, velocity);
                break;
            default:
                newSatellite = new SovietSatellite(connections, height, id, position, type, velocity);
        }

        this.satellites.add(newSatellite);

        this.satellites.sort(Comparator.comparing(Satellite::getId));

    }

    public void scheduleDeviceActivation(String deviceId, LocalTime start, int durationInMinutes) {
        for (Device device : this.devices) {
            if (deviceId.compareTo(device.getId()) == 0) {
                LocalTime end = start.plusMinutes(durationInMinutes);
                device.addActivationPeriods(start, end);
            }
        }
    }

    public void removeSatellite(String id) {
        int size = this.satellites.size();
        for (int i = 0; i < size; i++) {
            if (id.compareTo(this.satellites.get(i).getId()) == 0) {
                this.satellites.remove(i);
                break;
            }
        }
    }

    public void removeDevice(String id) {
        int size = this.devices.size();
        for (int i = 0; i < size; i++) {
            if (id.compareTo(this.devices.get(i).getId()) == 0) {
                this.devices.remove(i);
                break;
            }
        }
    }

    public void moveDevice(String id, double newPos) {

        for (Device device : this.devices) {
            if (id.compareTo(device.getId()) == 0) {
                device.setPosition(newPos);
                break;
            }
        }
    }

    public JSONObject showWorldState() {
        JSONObject result = new JSONObject();
        JSONArray devices = new JSONArray();
        JSONArray satellites = new JSONArray();

        for (Satellite satellite : this.satellites) {
            JSONObject newSatellite = new JSONObject();
            newSatellite.put("id", satellite.getId());
            newSatellite.put("position", satellite.getPosition());
            newSatellite.put("velocity", satellite.getVelocity());

            ArrayList<String> possibleConnections = new ArrayList<>();
            JSONArray connections = new JSONArray();

            for (Device device : this.devices) {
                if (MathsHelper.satelliteIsVisibleFromDevice(satellite.getPosition(),
                        satellite.getHeight(),
                        device.getPosition())) {
                    if ("SpaceXSatellite".compareTo(satellite.getType()) == 0) {
                        if ("HandheldDevice".compareTo(device.getType()) != 0 && "MobileXPhone".compareTo(device.getType()) != 0)
                            continue;
                    } else if ("SovietSatellite".compareTo(satellite.getType()) == 0) {
                        if ("LaptopDevice".compareTo(device.getType()) != 0 &&
                                "DesktopDevice".compareTo(device.getType()) != 0 &&
                        "AWSCloudServer".compareTo(device.getType()) != 0)
                            continue;
                    }
                    possibleConnections.add(device.getId());
                }
            }
            if (satellite.getConnections().size() != 0) {
                for (SuccessfulConnection successfulConnection: satellite.getConnections()) {
                    JSONObject oneConnection = new JSONObject();
                    oneConnection.put("endTime", successfulConnection.getEndTime());
                    oneConnection.put("deviceId", successfulConnection.getDeviceId());
                    oneConnection.put("minutesActive", successfulConnection.getMinutesActive());
                    oneConnection.put("startTime", successfulConnection.getStartTime());
                    oneConnection.put("satelliteId", successfulConnection.getSatelliteId());
                    connections.put(oneConnection);
                }
            }

            //connections.sort
            newSatellite.put("possibleConnections",possibleConnections);
            newSatellite.put("type", satellite.getType());
            newSatellite.put("connections", connections);
            newSatellite.put("height", satellite.getHeight());
            satellites.put(newSatellite);
        }

        for (Device device : this.devices) {
            JSONObject newDevice = new JSONObject();
            newDevice.put("isConnected", device.isConnected());
            newDevice.put("id", device.getId());
            newDevice.put("position", device.getPosition());
            newDevice.put("activationPeriods", device.getActivationPeriods());
            newDevice.put("type", device.getType());
            devices.put(newDevice);
        }
        result.put("devices", devices);
        result.put("satellites", satellites);

        // TODO: you'll want to replace this for Task2
        result.put("currentTime", currentTime);

        return result;
    }

    public void simulate(int tickDurationInMinutes) {


        for (int i = 0; i < tickDurationInMinutes; i++) {

            for (Satellite satellite :this.satellites) {
                satellite.updatePosition(1);
            }

            //solve connections which are in process and disconnect
            for (Device device: this.devices) {
                device.finishConnecting(currentTime);
                device.disconnect(currentTime, satellites);
            }

            //set up connections
            for (Device device: this.devices) {
                // find the most appropriate satellite
                ArrayList<Satellite> appropriateSatellites = device.findAppropriateSatellite(satellites, currentTime);
                if (appropriateSatellites == null)
                    continue;

                //connect to the device
                device.setUpConnection(appropriateSatellites, currentTime);
            }




            //update active time
            for (Device device: this.devices) {
                device.updateActivePeriod(currentTime);
            }

             this.currentTime = this.currentTime.plusMinutes(1);

        }
    }
}



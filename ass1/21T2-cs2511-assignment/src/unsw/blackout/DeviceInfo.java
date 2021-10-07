package unsw.blackout;

public class DeviceInfo {
    private final String id;
    private double position;
    private final String type;

    public DeviceInfo(String id, double position, String type) {
        this.id = id;
        this.position = position;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public double getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public void setPosition(double position) {
        this.position = position;
    }
}

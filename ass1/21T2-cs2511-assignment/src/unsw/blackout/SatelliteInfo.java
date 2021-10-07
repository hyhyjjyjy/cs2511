package unsw.blackout;

public class SatelliteInfo {
    private final double height;
    private double position;
    private final double velocity;
    private final double angularVelocity;
    private String type;
    private String id;


    public SatelliteInfo(double height, double position,
                         double velocity, double angularVelocity, String type, String id) {
        this.height = height;
        this.position = position;
        this.velocity = velocity;
        this.angularVelocity = angularVelocity;
        this.type = type;
        this.id = id;
    }

    public double getHeight() {
        return height;
    }

    public double getPosition() {
        return position;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }
}

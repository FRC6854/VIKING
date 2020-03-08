package viking;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    private static Limelight instance = null;
    private NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
  
    public static enum LightMode {
      DEFAULT,
      OFF,
      BLINK,
      ON
    }

    private double cameraHeight = 0; // Unit in meters
    private double cameraAngle = 0; // Unit in degrees
    private double targetHeight = 0; // Height in meters

    /**
     * Whether the limelight has any valid targets (0 or 1)
     * @return Returns true if vision target is found
     */
    public boolean validTargets(){
      double value = limelight.getEntry("tv").getDouble(0);
  
      if(value >= 1){
        return true;
      }
  
      return false;
    }
  
    /**
     * Horizontal Offset From Crosshair To Target 
     * (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)
     * @return x value of target relative to the crosshair
     */
    public double targetX() {
      return limelight.getEntry("tx").getDouble(0);
    }
  
    /**
     * Vertical Offset From Crosshair To Target 
     * (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
     * @return y value of target relative to the crosshair
     */
    public double targetY() {
      return limelight.getEntry("ty").getDouble(0);
    }
  
    /**
     * Target Area (0% of image to 100% of image)
     * @return target area in percentage
     */
    public double targetA() {
      return limelight.getEntry("ty").getDouble(0);
    }
  
    /**
     * True active pipeline index of the camera (0 .. 9)
     * @return active pipeline currently used by the Limelight
     */
    public double getPipeline() {
      return limelight.getEntry("getpipe").getDouble(0);
    }

    /**
     * Sets the camMode to the selected value passed to the method (0 or 1)
     * @param value the value to set the camMode to
     */
    public void setDriverMode(boolean value) {
      if (value == true) {
        limelight.getEntry("camMode").setDouble(1);
      }
      else { 
        limelight.getEntry("camMode").setDouble(0);
      }
    }
  
    /**
     * Get the current camMode
     * @return 0 means normal and 1 means driver mode
     */
    public double driverMode() {
      return limelight.getEntry("camMode").getDouble(0);
    }

    /**
     * Sets limelight’s LED state
     * @param mode set the mode to either DEFAULT, OFF, BLINK, or ON
     */
    public void setLEDMode(LightMode mode) {
      switch(mode) {
        case DEFAULT:
          limelight.getEntry("ledMode").setNumber(0);
          break;
        case OFF:
          limelight.getEntry("ledMode").setNumber(1);
          break;
        case BLINK:
          limelight.getEntry("ledMode").setNumber(2);
          break;
        case ON:
          limelight.getEntry("ledMode").setNumber(3);
          break;
      }
    }
  
    /**
     * Set the vision pipeline
     */
    public void setPipeline(int pipelineID) {
      limelight.getEntry("pipeline").setNumber(pipelineID);
    }

    /**
     * Set the height of the camera in the robot
     * @param height the height of the camera in meters
     */
    public void setCameraHeight(double height) {
      cameraHeight = height;
    }

    /**
     * Set the angle of the camera where 0 is level with the ground
     * @param angle the angle of the camera in degrees
     */
    public void setCameraAngle(double angle) {
      cameraAngle = angle;
    }

    /**
     * Sets the target height for the years target
     * @param height the height of the target in meters
     */
    public void setTargetHeight(double height) {
      targetHeight = height;
    }
  
    /**
     * Uses current targetY to calculate the distance to the target
     * @return the distance to the target in inches (estimation)
     */
    public double getDistanceFromTarget() {
      return (targetHeight - cameraHeight) / Math.tan(cameraAngle - targetY());
    }
  
    /**
     * Uses the FOV and the current targetX to calculate the X angle to the target
     * @return
     */
    public double getHorzAngle() {
      double horzFOV = 59.6;
      return Math.atan(Math.tan(Math.toRadians(horzFOV))*targetX()/160);
    }

    public static Limelight getInstance () {
      if (instance == null)
        instance = new Limelight();
      return instance;
    }
  }
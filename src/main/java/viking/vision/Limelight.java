package viking.vision;

import edu.wpi.first.hal.SimBoolean;
import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.hal.SimEnum;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

	private static Limelight instance = null;
	private NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

	public static enum LightMode { DEFAULT, OFF, BLINK, ON }

	private double cameraHeight = 0; // Unit in meters
	private double cameraAngle = 0; // Unit in degrees
	private double targetHeight = 0; // Height in meters

	// simulation specific
	private SimDevice sim_device;
	private SimBoolean sim_valid_target;
	private SimEnum sim_led_mode;
	private SimDouble sim_target_x, sim_target_y, sim_target_a;
	private SimEnum sim_cammode;
	private SimDouble sim_pipeline;

	public Limelight() {
		sim_device = SimDevice.create("Limelight");
		if (sim_device != null) {
			sim_valid_target
				= sim_device.createBoolean("valid_target", SimDevice.Direction.kInput, false);
			String ledmode_str[] = new String[4];
			ledmode_str[0] = "Default";
			ledmode_str[1] = "Off";
			ledmode_str[2] = "Blink";
			ledmode_str[3] = "On";
			sim_led_mode
				= sim_device.createEnum("led_mode", SimDevice.Direction.kOutput, ledmode_str, 0);
			sim_target_x = sim_device.createDouble("target_x", SimDevice.Direction.kInput, 0.0);
			sim_target_y = sim_device.createDouble("target_y", SimDevice.Direction.kInput, 0.0);
			sim_target_a = sim_device.createDouble("target_a", SimDevice.Direction.kInput, 0.0);
			String cammode_str[] = new String[2];
			cammode_str[0] = "Vision Processor";
			cammode_str[1] = "Driver Camera";
			sim_cammode
				= sim_device.createEnum("cammode", SimDevice.Direction.kOutput, cammode_str, 0);
			sim_pipeline = sim_device.createDouble("pipeline", SimDevice.Direction.kOutput, 0);
		}
	}

	/**
	 * Whether the limelight has any valid targets (0 or 1)
	 * @return Returns true if vision target is found
	 */
	public boolean validTargets() {
		if (sim_device != null) {
			return sim_valid_target.get();
		}

		double value = limelight.getEntry("tv").getDouble(0);

		if (value >= 1) {
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
		if (sim_device != null) {
			return sim_target_x.get();
		}
		return limelight.getEntry("tx").getDouble(0);
	}

	/**
	 * Vertical Offset From Crosshair To Target
	 * (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
	 * @return y value of target relative to the crosshair
	 */
	public double targetY() {
		if (sim_device != null) {
			return sim_target_y.get();
		}
		return limelight.getEntry("ty").getDouble(0);
	}

	/**
	 * Target Area (0% of image to 100% of image)
	 * @return target area in percentage
	 */
	public double targetA() {
		if (sim_device != null) {
			return sim_target_a.get();
		}
		return limelight.getEntry("ty").getDouble(0);
	}

	/**
	 * True active pipeline index of the camera (0 .. 9)
	 * @return active pipeline currently used by the Limelight
	 */
	public double getPipeline() {
		if (sim_device != null) {
			return sim_pipeline.get();
		}
		return limelight.getEntry("getpipe").getDouble(0);
	}

	/**
	 * Sets the camMode to the selected value passed to the method (0 or 1)
	 * @param value the value to set the camMode to
	 */
	public void setDriverMode(boolean value) {
		if (sim_device != null) {
			sim_cammode.set(value ? 1 : 0);
			return;
		}

		if (value == true) {
			limelight.getEntry("camMode").setDouble(1);
		} else {
			limelight.getEntry("camMode").setDouble(0);
		}
	}

	/**
	 * Get the current camMode
	 * @return 0 means normal and 1 means driver mode
	 */
	public double driverMode() {
		if (sim_device != null) {
			return sim_cammode.get();
		}

		return limelight.getEntry("camMode").getDouble(0);
	}

	/**
	 * Sets limelight’s LED state
	 * @param mode set the mode to either DEFAULT, OFF, BLINK, or ON
	 */
	public void setLEDMode(LightMode mode) {
		int ledmode = 0;
		switch (mode) {
		case DEFAULT:
			ledmode = 0;
			break;
		case OFF:
			ledmode = 1;
			break;
		case BLINK:
			ledmode = 2;
			break;
		case ON:
			ledmode = 3;
			break;
		}

		if (sim_device != null) {
			sim_led_mode.set(ledmode);
			return;
		}

		limelight.getEntry("ledMode").setNumber(ledmode);
	}

	/**
	 * Set the vision pipeline
	 * @param pipelineID ID of vision pipeline profile
	 */
	public void setPipeline(int pipelineID) {
		if (sim_device != null) {
			sim_pipeline.set(pipelineID);
		}
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
	 * @return X angle to the target
	 */
	public double getHorzAngle() {
		double horzFOV = 59.6;
		return Math.atan(Math.tan(Math.toRadians(horzFOV)) * targetX() / 160);
	}

	public static Limelight getInstance() {
		if (instance == null) {
			instance = new Limelight();
		}
		return instance;
	}
}

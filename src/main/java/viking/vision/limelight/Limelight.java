package viking.vision.limelight;

public class Limelight {

	private LimelightComm limelight_comm = null;
	private LimelightSimulator limelight_simulator = null;

	public static enum LightMode { DEFAULT, OFF, BLINK, ON }

	private double cameraHeight = 0; // Unit in meters
	private double cameraAngle = 0; // Unit in degrees
	private double targetHeight = 0; // Height in meters

	public Limelight() {
		limelight_comm = new LimelightComm();
		limelight_simulator = new LimelightSimulator();
	}

	public Limelight(String limelight_networktable_name) {
		limelight_comm = new LimelightComm(limelight_networktable_name);
		limelight_simulator = new LimelightSimulator();
	}

	public Limelight(String limelight_networktable_name, String position) {
		limelight_comm = new LimelightComm(limelight_networktable_name);
		limelight_simulator = new LimelightSimulator(position);
	}

	/**
	 * Whether the limelight has any valid targets (0 or 1)
	 * @return Returns true if vision target is found
	 */
	public boolean validTargets() {
		if (limelight_simulator.is_simulation()) {
			return limelight_simulator.get_valid_target();
		}

		double value = limelight_comm.get_entry_double("tv");

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
		if (limelight_simulator.is_simulation()) {
			return limelight_simulator.get_target_x();
		}
		return limelight_comm.get_entry_double("tx");
	}

	/**
	 * Vertical Offset From Crosshair To Target
	 * (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
	 * @return y value of target relative to the crosshair
	 */
	public double targetY() {
		if (limelight_simulator.is_simulation()) {
			return limelight_simulator.get_target_y();
		}
		return limelight_comm.get_entry_double("ty");
	}

	/**
	 * Target Area (0% of image to 100% of image)
	 * @return target area in percentage
	 */
	public double targetA() {
		if (limelight_simulator.is_simulation()) {
			return limelight_simulator.get_target_a();
		}
		return limelight_comm.get_entry_double("ta");
	}

	/**
	 * True active pipeline index of the camera (0 .. 9)
	 * @return active pipeline currently used by the Limelight
	 */
	public int getPipeline() {
		if (limelight_simulator.is_simulation()) {
			return limelight_simulator.get_pipeline();
		}
		return (int)limelight_comm.get_entry_double("getpipe");
	}

	/**
	 * Sets the camMode to the selected value passed to the method (0 or 1)
	 * @param value the value to set the camMode to
	 */
	public void setDriverMode(boolean value) {
		if (limelight_simulator.is_simulation()) {
			limelight_simulator.set_cammode(value ? 1 : 0);
			return;
		}

		if (value == true) {
			limelight_comm.set_entry_number("camMode", 1);
		} else {
			limelight_comm.set_entry_number("camMode", 0);
		}
	}

	/**
	 * Get the current camMode
	 * @return 0 means normal and 1 means driver mode
	 */
	public int driverMode() {
		if (limelight_simulator.is_simulation()) {
			return limelight_simulator.get_cammode();
		}

		return limelight_comm.get_entry_number("camMode").intValue();
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

		if (limelight_simulator.is_simulation()) {
			limelight_simulator.set_led_mode(ledmode);
			return;
		}

		limelight_comm.set_entry_number("ledMode", ledmode);
	}

	/**
	 * Set the vision pipeline
	 * @param pipelineID ID of vision pipeline profile
	 */
	public void setPipeline(int pipelineID) {
		if (limelight_simulator.is_simulation()) {
			limelight_simulator.set_pipeline(pipelineID);
			return;
		}
		limelight_comm.set_entry_number("pipeline", pipelineID);
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
}

package viking.vision.limelight;

import edu.wpi.first.hal.SimBoolean;
import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.hal.SimEnum;

class LimelightSimulator {

	private SimDevice sim_device;
	private SimBoolean sim_valid_target;
	private SimDouble sim_target_x, sim_target_y, sim_target_a;
	private SimDouble sim_pipeline;
	private SimEnum sim_cammode;
	private SimEnum sim_led_mode;

	public LimelightSimulator() {
		sim_device = SimDevice.create("Limelight");

		if (sim_device != null) { // only construct while not in simulation
			sim_valid_target
				= sim_device.createBoolean("valid_target", SimDevice.Direction.kInput, false);

			sim_target_x = sim_device.createDouble("target_x", SimDevice.Direction.kInput, 0.0);
			sim_target_y = sim_device.createDouble("target_y", SimDevice.Direction.kInput, 0.0);
			sim_target_a = sim_device.createDouble("target_a", SimDevice.Direction.kInput, 0.0);

			sim_pipeline = sim_device.createDouble("pipeline", SimDevice.Direction.kOutput, 0);

			String cammode_str[] = new String[2];
			cammode_str[0] = "Vision Processor";
			cammode_str[1] = "Driver Camera";
			sim_cammode
				= sim_device.createEnum("cammode", SimDevice.Direction.kOutput, cammode_str, 0);

			String ledmode_str[] = new String[4];
			ledmode_str[0] = "Default";
			ledmode_str[1] = "Off";
			ledmode_str[2] = "Blink";
			ledmode_str[3] = "On";
			sim_led_mode
				= sim_device.createEnum("led_mode", SimDevice.Direction.kOutput, ledmode_str, 0);
		}
	}

	public boolean is_simulation() {
		return (sim_device != null);
	}

	public boolean get_valid_target() {
		return sim_valid_target.get();
	}

	public double get_target_x() {
		return sim_target_x.get();
	}

	public double get_target_y() {
		return sim_target_y.get();
	}

	public double get_target_a() {
		return sim_target_a.get();
	}

	public double get_pipeline() {
		return sim_pipeline.get();
	}

	public void set_pipeline(int pipeline_id) {
		sim_pipeline.set(pipeline_id);
	}

	public int get_cammode() {
		return sim_cammode.get();
	}

	public void set_cammode(int cammode) {
		sim_cammode.set(cammode);
	}

	public void set_led_mode(int ledmode) {
		sim_led_mode.set(ledmode);
	}
}

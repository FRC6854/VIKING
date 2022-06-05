package viking.vision.limelight;

import edu.wpi.first.networktables.NetworkTableInstance;

class LimelightComm {
	private String networktable_name;

	public LimelightComm() {
		networktable_name = "limelight";
	}

	public LimelightComm(String limelight_networktable_name) {
		networktable_name = limelight_networktable_name;
	}

	public double get_entry_double(String entry_name) {
		return NetworkTableInstance.getDefault()
			.getTable(networktable_name)
			.getEntry(entry_name)
			.getDouble(0);
	}

	public Number get_entry_number(String entry_name) {
		return NetworkTableInstance.getDefault()
			.getTable(networktable_name)
			.getEntry(entry_name)
			.getNumber(0);
	}

	public double[] get_entry_double_array(String entry_name) {
		return NetworkTableInstance.getDefault()
			.getTable(networktable_name)
			.getEntry(entry_name)
			.getDoubleArray(new double[0]);
	}

	public Number[] get_entry_number_array(String entry_name) {
		return NetworkTableInstance.getDefault()
			.getTable(networktable_name)
			.getEntry(entry_name)
			.getNumberArray(new Number[0]);
	}

	public void set_entry_double(String entry_name, double value) {
		NetworkTableInstance.getDefault()
			.getTable(networktable_name)
			.getEntry(entry_name)
			.setDouble(value);
	}

	public void set_entry_number(String entry_name, Number value) {
		NetworkTableInstance.getDefault()
			.getTable(networktable_name)
			.getEntry(entry_name)
			.setNumber(value);
	}

	public void set_entry_double_array(String entry_name, double[] array) {
		NetworkTableInstance.getDefault()
			.getTable(networktable_name)
			.getEntry(entry_name)
			.setDoubleArray(array);
	}

	public void set_entry_number_array(String entry_name, Number[] array) {
		NetworkTableInstance.getDefault()
			.getTable(networktable_name)
			.getEntry(entry_name)
			.getNumberArray(new Number[0]);
	}
}

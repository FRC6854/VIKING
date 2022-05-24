package viking.logging;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.StringLogEntry;
import edu.wpi.first.util.datalog.FloatLogEntry;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.util.datalog.BooleanLogEntry;

public class TelemetryLogger {
	public TelemetryLogger() {
		startDataManager();
	}

	// Log entries
	// Gyroscope
	BooleanLogEntry isMoving;
	BooleanLogEntry isRotating;

	FloatLogEntry pitch;
	FloatLogEntry roll;
	FloatLogEntry yaw;
	FloatLogEntry compassHeading; 
	FloatLogEntry linearAccelX;  
	FloatLogEntry linearAccelY; 
	FloatLogEntry linearAccelZ; 
	FloatLogEntry velocityX;  
	FloatLogEntry velocityY;
	FloatLogEntry velocityZ; 
	FloatLogEntry displacementX; 
	FloatLogEntry displacementY; 
	FloatLogEntry displacementZ;
	DoubleLogEntry angle;

	/**
	 * Starts DataLogManager and gets the managed data log from the roborio's memory
	 */
	// FUTURE: Make a method to create as many log entries as the user wish
	public void startDataManager() {
		DataLog log = DataLogManager.getLog();

		BooleanLogEntry isMoving = new BooleanLogEntry(log, "/gyro/isMoving");
		BooleanLogEntry isRotating = new BooleanLogEntry(log, "/gyro/isRotating");

		FloatLogEntry pitch = new FloatLogEntry(log, "gyro/pitch");
		FloatLogEntry roll = new FloatLogEntry(log, "gyro/roll");
		FloatLogEntry yaw = new FloatLogEntry(log, "gyro/yaw");
		FloatLogEntry compassHeading = new FloatLogEntry(log, "gyro/compassHeading"); 
		FloatLogEntry linearAccelX = new FloatLogEntry(log, "gyro/linearAccelX");  
		FloatLogEntry linearAccelY = new FloatLogEntry(log, "gyro/linearAccelY"); 
		FloatLogEntry linearAccelZ = new FloatLogEntry(log, "gyro/linearAccelZ"); 
		FloatLogEntry velocityX = new FloatLogEntry(log, "gyro/velocityX");  
		FloatLogEntry velocityY = new FloatLogEntry(log, "gyro/velocityY");
		FloatLogEntry velocityZ = new FloatLogEntry(log, "gyro/velocityZ"); 
		FloatLogEntry displacementX = new FloatLogEntry(log, "gyro/displacementX"); 
		FloatLogEntry displacementY = new FloatLogEntry(log, "gyro/displacementY"); 
		FloatLogEntry displacementZ = new FloatLogEntry(log, "gyro/displacementZ");

		DoubleLogEntry angle = new DoubleLogEntry(log, "gyro/angle");
		
	}

	/**
	 * Appends everything
	 * @param entry
	 */
	public void append(TelemetryData data) {
		isMoving.append(data.isMoving);
		isRotating.append(data.isRotating);

		pitch.append(data.pitch);
		roll.append(data.roll);
		yaw.append(data.yaw);
		compassHeading.append(data.compassHeading);
		linearAccelX.append(data.linearAccelX);
		linearAccelY.append(data.linearAccelY);
		linearAccelZ.append(data.linearAccelZ);
		velocityX.append(data.velocityX);
		velocityY.append(data.velocityY);
		velocityZ.append(data.velocityZ);
		displacementX.append(data.displacementX);
		displacementY.append(data.displacementY);
		displacementZ.append(data.displacementZ);

		angle.append(data.angle);
	}
}

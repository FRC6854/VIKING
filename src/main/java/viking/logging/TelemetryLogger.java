package viking.logging;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.FloatLogEntry;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.util.datalog.BooleanLogEntry;

/**
 * Class for setting up log entries and appending data to it
 */
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
	 * Appends everything into log entries
	 * @param entry All the data being stored in the TelemetryData class
	 */
	public void append(TelemetryData data) {
		isMoving.append(data.isMoving);      // is the robot moving?
		isRotating.append(data.isRotating); // is the robot rotating?
		pitch.append(data.pitch);          // pitch
		roll.append(data.roll);           // roll
		yaw.append(data.yaw);            // yaw

		compassHeading.append(data.compassHeading); // compass heading
		linearAccelX.append(data.linearAccelX);    // linear acceleration x
		linearAccelY.append(data.linearAccelY);   // linear acceleration y
		linearAccelZ.append(data.linearAccelZ);  // linear acceleration z
		velocityX.append(data.velocityX);       // velocity on x-axis
		velocityY.append(data.velocityY);      // velocity on y-axis
		velocityZ.append(data.velocityZ);     // velocity on z-axis

		displacementX.append(data.displacementX);   // displacement on the x-axis
		displacementY.append(data.displacementY);  // displacement on the y-axis
		displacementZ.append(data.displacementZ); // displacement on the z-axis
		angle.append(data.angle);
	}
}

package viking.controllers;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

/**
 * Class dealing with the configuration of the swerve wheel drive system
 */
public class SwerveWheelDrive {

	public enum SwerveWheelDriveType { TalonSRX, Spark, VictorSPX }

	SwerveWheelDriveType type;

	MotorController controller;

	/**
	 * Configures specified motor type for the swerve wheel drive system. 
	 * @param type Motor type
	 * @param id CAN ID
	 * @param inverted Inverts the motor 
	 */
	public SwerveWheelDrive(SwerveWheelDriveType type, int id, boolean inverted) {
		if (type == SwerveWheelDriveType.TalonSRX) {

			// Create TalonSRX object with the ID from the constructor
			WPI_TalonSRX drive = new WPI_TalonSRX(id);

			drive.configFactoryDefault();

			// Invert the motor depending on the inverted value
			drive.setInverted(inverted);

			// WPI_TalonSRX can be passed into many different WPILib objects like the
			// SpeedController
			controller = drive;

		} else if (type == SwerveWheelDriveType.Spark) {

			// Create Spark object with the ID from the constructor
			Spark drive = new Spark(id);

			// Invert the motor depending on the inverted value
			drive.setInverted(inverted);

			// Spark is a WPILib object so it can be passed into many different WPILib objects like
			// the SpeedController
			controller = drive;

		} else if (type == SwerveWheelDriveType.VictorSPX) {

			// Create VictorSPX object with the ID from the constructor
			WPI_VictorSPX drive = new WPI_VictorSPX(id);

			drive.configFactoryDefault();

			// Invert the motor depending on the inverted value
			drive.setInverted(inverted);

			// WPI_VictorSPX can be passed into many different WPILib objects like the
			// SpeedController
			controller = drive;
		}
	}

	/**
	 * Sets speed of the swerve wheel drive motor controller
	 * @param speed
	 */
	public void setSpeed(double speed) {
		controller.set(speed);
	}
}

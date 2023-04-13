package viking.math;

import edu.wpi.first.math.geometry.Rotation2d;

public class UnitConversion {

	/**
	 * Convert revolution to meters
	 * @param wheelDiameterMeters of the wheel
	 * @return meters per revolution
	 */
	public static double metersPerRevolution(double wheelDiameterMeters) {
		return wheelDiameterMeters * Math.PI;
	}

	/**
	 * Convert distance in meters to CTRE native units
	 *
	 * @param positionMeters in meters
	 * @param metersPerRevolution of the wheel
	 * @param countPerRotation of the encoder
	 * @return native unit sensor counts
	 */
	public static int distanceToNativeUnits(double positionMeters, double metersPerRevolution, double countPerRotation) {
		double wheelRotations = positionMeters / metersPerRevolution;
		int sensorCounts = (int) (wheelRotations * countPerRotation);
		return sensorCounts;
	}

	/**
	 * Convert velocity from meters per second to sensor counts per 100ms
	 *
	 * @param velocityMetersPerSecond
	 * @param metersPerRevolution of the wheel
	 * @param countPerRotation of the encoder
	 * @return native unit sensor count per 100ms
	 */
	public static int velocityToNativeUnits(double velocityMetersPerSecond, double metersPerRevolution, double countPerRotation) {
		double wheelRotationsPerSecond = velocityMetersPerSecond / metersPerRevolution;
		double motorRotationsPer100ms = wheelRotationsPerSecond / 10;
		int sensorCountsPer100ms = (int) (motorRotationsPer100ms * countPerRotation);
		return sensorCountsPer100ms;
	}

	/**
	 * Convert CTRE native units to position in meters
	 *
	 * @param sensorCounts from getSelectedSensorPosition()
	 * @param metersPerRevolution of the wheel
	 * @param countPerRotation of the encoder
	 * @return position in meters
	 */
	public static double nativeUnitsToDistanceMeters(double sensorCounts, double metersPerRevolution, double countPerRotation) {
		double motorRotations = (double) sensorCounts / countPerRotation;
		double positionMeters = motorRotations * metersPerRevolution;
		return positionMeters;
	}

	/**
	 * Convert CTRE native units to velocity in meters per second
	 *
	 * @param sensorCountsPer100ms from getSelectedSensorVelocity()
	 * @param metersPerRevolution of the wheel
	 * @param countPerRotation of the encoder
	 * @return velocity in meters per second
	 */
	public static double nativeUnitsToVelocityMetersPerSecond(double sensorCountsPer100ms, double metersPerRevolution, double countPerRotation) {
		double motorRotationsPer100ms = sensorCountsPer100ms / countPerRotation;
		double motorRotationsPerSecond = motorRotationsPer100ms * 10;
		double velocityMetersPerSecond = motorRotationsPerSecond * metersPerRevolution;
		return velocityMetersPerSecond;
	}

	/**
	 * Convert CTRE native units to velocity in meters per minute
	 *
	 * @param sensorCountsPer100ms from getSelectedSensorVelocity()
	 * @param metersPerRevolution of the wheel
	 * @param countPerRotation of the encoder
	 * @return velocity in meters per minute
	 */
	public static double nativeUnitsToVelocityMetersPerMinute(double sensorCountsPer100ms, double metersPerRevolution, double countPerRotation) {
		return nativeUnitsToVelocityMetersPerSecond(sensorCountsPer100ms, metersPerRevolution, countPerRotation) * 60;
	}

	/**
	 * Convert angle to CTRE native units (used for swerve drive)
	 * @param rotation to convert
	 * @param gearRation of the motor
	 * @param countPerRotation of the encoder
	 * @return native unit sensor counts
	 */
	public static double degreesToNativeUnits(double angle, double gearRation, double countPerRotation) {
		return angle / (360 / (gearRation * countPerRotation));
	}

	/**
	 * Convert Rotation2d to CTRE native units (used for swerve drive)
	 * @param angle to convert
	 * @param gearRation of the motor
	 * @param countPerRotation of the encoder
	 * @return native unit sensor counts
	 */
	public static double degreesToNativeUnits(Rotation2d angle, double gearRation, double countPerRotation) {
		return angle.getDegrees() / (360 / (gearRation * countPerRotation));
	}

	/**
	 * Convert CTRE native units to Rotation2d
	 * @param sensorCounts from getSelectedSensorPosition()
	 * @param gearRation of the motor
	 * @param countPerRotation of the encoder
	 * @return Rotation2d
	 */
	public static Rotation2d nativeUnitsToDegrees(double sensorCounts, double gearRation, double countPerRotation) {
		return Rotation2d.fromDegrees(sensorCounts * (360 / (gearRation * countPerRotation)));
	}

	/**
	 * Convert CTRE native units to angle in degrees
	 * @param sensorCounts from getSelectedSensorPosition()
	 * @param gearRation of the motor
	 * @param countPerRotation of the encoder
	 * @return angle in degrees
	 */
	public static double nativeUnitsToDegreesDouble(double sensorCounts, double gearRation, double countPerRotation) {
		return sensorCounts * (360 / (gearRation * countPerRotation));
	}
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package viking.math;


public class UnitConversion {
	/**
	 * Convert distance in meters to CTRE native units
	 *
	 * @param positionMeters in meters
	 * @return native unit sensor counts
	 */
	public static int distanceToNativeUnits(double positionMeters) {
		double wheelRotations = positionMeters / Constants.DRIVETRAIN_kMetersPerRevolution;
		int sensorCounts = (int) (wheelRotations * Constants.DRIVETRAIN_kCPR);
		return sensorCounts;
	}

	/**
	 * Convert velocity from meters per second to sensor counts per 100ms
	 *
	 * @param velocityMetersPerSecond
	 * @return native unit sensor count per 100ms
	 */
	public static int velocityToNativeUnits(double velocityMetersPerSecond) {
		double wheelRotationsPerSecond = velocityMetersPerSecond / Constants.DRIVETRAIN_kMetersPerRevolution;
		double motorRotationsPer100ms = wheelRotationsPerSecond / 10;
		int sensorCountsPer100ms = (int) (motorRotationsPer100ms * Constants.DRIVETRAIN_kCPR);
		return sensorCountsPer100ms;
	}

	/**
	 * Convert CTRE native units to position in meters
	 *
	 * @param sensorCounts from getSelectedSensorPosition()
	 * @return position in meters
	 */
	public static double nativeUnitsToDistanceMeters(double sensorCounts) {
		double motorRotations = (double) sensorCounts / Constants.DRIVETRAIN_kCPR;
		double positionMeters = motorRotations * Constants.DRIVETRAIN_kMetersPerRevolution;
		return positionMeters;
	}

	/**
	 * Convert CTRE native units to velocity in meters per second
	 *
	 * @param sensorCountsPer100ms from getSelectedSensorVelocity()
	 * @return velocity in meters per second
	 */
	public static double nativeUnitsToVelocityMetersPerSecond(double sensorCountsPer100ms) {
		double motorRotationsPer100ms = sensorCountsPer100ms / Constants.DRIVETRAIN_kCPR;
		double motorRotationsPerSecond = motorRotationsPer100ms * 10;
		double velocityMetersPerSecond = motorRotationsPerSecond * Constants.DRIVETRAIN_kMetersPerRevolution;
		return velocityMetersPerSecond;
	}
}

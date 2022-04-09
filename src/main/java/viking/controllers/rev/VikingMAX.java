package viking.controllers.rev;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

public class VikingMAX extends CANSparkMax {

	private SparkMaxPIDController pidController = null;
	private RelativeEncoder encoder = null;

	/**
	 * @param id the CAN ID for the Spark MAX
	 * @param inverted is the motor inverted
	 * @param kF
	 * @param kP
	 * @param kI
	 * @param kD
	 * @param velocity max velocity for smart motion
	 * @param acceleration max acceleration for smart motion
	 */
	public VikingMAX(int id, boolean inverted, double kF, double kP, double kI, double kD,
					 double velocity, double acceleration) {

		super(id, MotorType.kBrushless);

		restoreFactoryDefaults();
		setInverted(inverted);

		pidController = getPIDController();
		encoder = getEncoder();

		pidController.setOutputRange(-1, 1);

		setPIDF(kP, kI, kD, kF);
		setSmartMotion(velocity, acceleration);
		setIdleMode(IdleMode.kBrake);
	}

	/**
	 * @param id the CAN ID for the Spark MAX
	 * @param inverted is the motor inverted
	 */
	public VikingMAX(int id, boolean inverted) {

		super(id, MotorType.kBrushless);

		restoreFactoryDefaults();

		setInverted(inverted);

		pidController = getPIDController();
		encoder = getEncoder();

		pidController.setOutputRange(-1, 1);

		setIdleMode(IdleMode.kBrake);
	}

	/**
	 * Set the motor to follow another VikingMAX
	 * @param master the master controller to follow
	 */
	public void setFollower(VikingMAX master) {
		follow(master);
	}

	/**
	 * Set the Smart Motion variables
	 * @param maxVelocity the max velocity in Smart Motion control modes
	 * @param maxAcceleration the max acceleration in Smart Motion control modes
	 */
	public void setSmartMotion(double maxVelocity, double maxAcceleration) {
		pidController.setSmartMotionMaxVelocity(maxVelocity, 0);
		pidController.setSmartMotionMaxAccel(maxAcceleration, 0);
	}

	/**
	 * Set the PIDF variables
	 * @param p kP
	 * @param i kI
	 * @param d kD
	 * @param f kF
	 */
	public void setPIDF(double p, double i, double d, double f) {
		pidController.setP(p);
		pidController.setI(i);
		pidController.setD(d);
		pidController.setFF(f);
	}

	/**
	 * Set the controller to the requested value in percent output
	 * @param value
	 */
	public void percentOutput(double value) {
		set(value);
	}

	/**
	 * Set the controller to the requested ticks using Smart Motion
	 * @param ticks the number of rotations
	 */
	public void smartPositionControl(double ticks) {
		pidController.setReference(ticks, ControlType.kSmartMotion);
	}

	/**
	 * Set the controller to the requested velocity using Smart Motion
	 * @param velocity value in RPM (max RPM is 6000 by default)
	 */
	public void smartVelocityControl(double velocity) {
		pidController.setReference(velocity, ControlType.kSmartVelocity);
	}

	/**
	 * Set the controller to the requested ticks using position control
	 * @param ticks the number of rotations
	 */
	public void positionControl(double ticks) {
		pidController.setReference(ticks, ControlType.kPosition);
	}

	/**
	 * Set the controller to the requested velocity using velocity closed-loop
	 * @param velocity value in RPM (max RPM is 6000 by default)
	 */
	public void velocityControl(double velocity) {
		pidController.setReference(velocity, ControlType.kVelocity);
	}

	/**
	 * Get the current applied output to the motor. Can even be fetched during closed-loop
	 * @return the current applied output from -1 to 1
	 */
	public double getOutput() {
		return getAppliedOutput();
	}

	/**
	 * Get the current encoder position
	 * @return the current encoder position in rotations
	 */
	public double getPosition() {
		return encoder.getPosition();
	}

	/**
	 * Get the current RPM
	 * @return the current velocity in RPM
	 */
	public double getVelocity() {
		return encoder.getVelocity();
	}

	/**
	 * Get the number of counts per revolution (also known as the resolution of the encoder)
	 * @return the # of counts per revolution
	 */
	public double getCountsPerRevolution() {
		return encoder.getCountsPerRevolution();
	}

	/**
	 * Sets the current encoder position to the requested value
	 * @param value the position in rotations
	 */
	public void setEncoderPosition(double value) {
		encoder.setPosition(value);
	}

	/**
	 * Set the current encoder position to 0
	 */
	public void zeroEncoder() {
		encoder.setPosition(0);
	}
}

package viking.controllers.ctre;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


public class VikingFX extends WPI_TalonFX {

	/**
	 * Constructor for VikingFX without encoder
	 * @param id the CAN ID for the Talon FX
	 * @param inverted is the motor inverted
	 */
	public VikingFX(int id, boolean inverted) {
		super(id);
		configAllSettings(new TalonFXConfiguration());
		setNeutralMode(NeutralMode.Brake);
		setSafetyEnabled(false);
		setInverted(inverted);

	}

	/**
	 * Full constructor for VikingFX
	 * @param id the CAN ID for the Talon FX
	 * @param inverted is the motor inverted
	 * @param sensorPhase should the encoder be inverted
	 * @param kF the F variable of PIDF
	 * @param kP the P variable of PIDF
	 * @param kI the I variable of PIDF
	 * @param kD the D variable of PIDF
	 * @param velocity the max velocity for Motion Magic
	 * @param acceleration the max acceleration for Motion Magic
	 */
	public VikingFX(int id, boolean inverted, boolean sensorPhase,
					 double kF, double kP, double kI, double kD, double velocity,
					 double acceleration) {

		super(id);
		
		configAllSettings(new TalonFXConfiguration());

		// Invert Power
		setInverted(inverted);

		// Invert Encoder
		setSensorPhase(sensorPhase);

		// Set-up PIDF[0]
		selectProfileSlot(0, 0);

		config_kF(0, kF, 0);
		config_kP(0, kP, 0);
		config_kI(0, kI, 0);
		config_kD(0, kD, 0);

		// Motion Magic
		configMotionCruiseVelocity(1250);
		configMotionAcceleration(1500);

		/**
		 * --------------
		 * Motion Profile
		 * --------------
		 * In our case we must use the value 25ms for both since in our profile we use a delta time
		 * of 50ms
		 */
		configMotionProfileTrajectoryPeriod(25);
		changeMotionControlFramePeriod(25);

		// Zero Sensor
		setSelectedSensorPosition(0);
	}

	public void percentOutput(double value) {
		set(ControlMode.PercentOutput, value);
	}

	public void positionControl(double ticks) {
		set(ControlMode.Position, ticks);
	}

	public void velocityControl(int velocity) {
		set(ControlMode.Velocity, velocity);
	}

	public void motionMagic(double ticks) {
		set(ControlMode.MotionMagic, ticks);
	}

	public double getTicks() {
		return getSelectedSensorPosition();
	}

	public double getVelocity() {
		return getSelectedSensorVelocity();
	}

	public void zeroSensor() {
		setSelectedSensorPosition(0);
	}
}

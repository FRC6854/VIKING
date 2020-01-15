package viking.controllers.ctre;

import com.ctre.phoenix.motion.BufferedTrajectoryPointStream;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class VikingSRX {

    private TalonSRX motor;
    private BufferedTrajectoryPointStream bufferedStream = new BufferedTrajectoryPointStream();

    /**
     * Constructor for VikingSRX without encoder
     * @param id the CAN ID for the Talon SRX
     * @param inverted is the motor inverted
     */
    public VikingSRX(int id, boolean inverted) {
        this.motor = new TalonSRX(id);

        motor.configFactoryDefault();

        motor.setInverted(inverted);
    }

    /**
     * Full constructor for VikingSRX
     * @param id the CAN ID for the Talon SRX
     * @param inverted is the motor inverted
     * @param sensorPhase should the encoder be inverted
     * @param device the type of encoder
     * @param kF the F variable of PIDF
     * @param kP the P variable of PIDF
     * @param kI the I variable of PIDF
     * @param kD the D variable of PIDF
     * @param velocity the max velocity for Motion Magic
     * @param acceleration the max acceleration for Motion Magic
     * @param metersPerRevolution the meters traveled per revolution of the motor
     */
    public VikingSRX(int id, boolean inverted, boolean sensorPhase, 
                            FeedbackDevice device, double kF, double kP, double kI, 
                            double kD, double velocity, double acceleration) {

        this.motor = new TalonSRX(id);

        motor.configFactoryDefault();

        // Invert Power
        motor.setInverted(inverted);

        // Invert Encoder
        motor.setSensorPhase(sensorPhase);

        motor.configSelectedFeedbackSensor(device, 0, 0);

        // Set-up PIDF[0]
        motor.selectProfileSlot(0, 0);

        motor.config_kF(0, kF, 0);
        motor.config_kP(0, kP, 0);
        motor.config_kI(0, kI, 0);
        motor.config_kD(0, kD, 0);

        // Motion Magic
        motor.configMotionCruiseVelocity(1250);
        motor.configMotionAcceleration(1500);

        /*  
            --------------
            Motion Profile
            --------------
            In our case we must use the value 25ms for both since in our profile we use a delta time of 50ms
        */
        motor.configMotionProfileTrajectoryPeriod(25);
        motor.changeMotionControlFramePeriod(25);

        // Zero Sensor
        motor.setSelectedSensorPosition(0);
    }

    public void initMotionBuffer(BufferedTrajectoryPointStream buffer) {
        bufferedStream = buffer;
    }

    public void resetMotionProfile() {
        bufferedStream.Clear();
        motor.clearMotionProfileTrajectories();
    }

    public void percentOutput(double value) {
        motor.set(ControlMode.PercentOutput, value);
    }

    public void positionControl(double ticks) {
        motor.set(ControlMode.Position, ticks);
    }

    public void velocityControl(int velocity) {
        motor.set(ControlMode.Velocity, velocity);
    }

    public void motionMagic(double ticks) {
        motor.set(ControlMode.MotionMagic, ticks);
    }

    public void motionProfileStart() {
        motor.startMotionProfile(bufferedStream, 10, ControlMode.MotionProfile);
    }

    public void setNeutralMode(NeutralMode mode) {
        motor.setNeutralMode(mode);
    }

    public void zeroSensor() {
        motor.setSelectedSensorPosition(0);
    }

    public int getTicks() {
        return motor.getSelectedSensorPosition();
    }

    public int getVelocity() {
        return motor.getSelectedSensorVelocity();
    }

    public ControlMode getControlMode() {
        return motor.getControlMode();
    }

    public boolean isMotionProfileFinished() {
        return motor.isMotionProfileFinished();
    }

    public TalonSRX getTalonSRX() {
        return motor;
    }
}

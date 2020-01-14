package viking.controllers.rev;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class VikingMAX {

    private CANSparkMax motor = null;
    private CANPIDController pidController = null;
    private CANEncoder encoder = null;

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
    public VikingMAX(int id, boolean inverted,
                            double kF, double kP, double kI, 
                            double kD, double velocity, double acceleration) {

        motor = new CANSparkMax(id, MotorType.kBrushless);

        motor.restoreFactoryDefaults();

        motor.setInverted(inverted);

        pidController = motor.getPIDController();
        encoder = motor.getEncoder();

        pidController.setOutputRange(-1, 1);
        
        setPIDF(kP, kI, kD, kF);
        setSmartMotion(velocity, acceleration);
    }

    /**
     * @param id the CAN ID for the Spark MAX
     * @param inverted is the motor inverted
     */
    public VikingMAX(int id, boolean inverted) {
        motor = new CANSparkMax(id, MotorType.kBrushless);

        motor.restoreFactoryDefaults();

        motor.setInverted(inverted);

        pidController = motor.getPIDController();
        encoder = motor.getEncoder();

        pidController.setOutputRange(-1, 1);
    }

    public void setSmartMotion(double maxVelocity, double maxAcceleration) {
        pidController.setSmartMotionMaxVelocity(maxVelocity, 0);
        pidController.setSmartMotionMaxAccel(maxAcceleration, 0);
    }

    public void setPIDF(double p, double i, double d, double f) {
        pidController.setP(p);
        pidController.setI(i);
        pidController.setD(d);
        pidController.setFF(f);
    }

    public void percentOutput(double value) {
        motor.set(value);
    }

    public void smartPositionControl(double ticks) {
        pidController.setReference(ticks, ControlType.kSmartMotion);
    }

    public void smartVelocityControl(double velocity) {
        pidController.setReference(velocity, ControlType.kSmartVelocity);
    }

    public void positionControl(double ticks) {
        pidController.setReference(ticks, ControlType.kPosition);
    }

    public void velocityControl(double velocity) {
        pidController.setReference(velocity, ControlType.kVelocity);
    }

    public double getOutput() {
        return motor.getAppliedOutput();
    }

    public double getPosition() {
        return encoder.getPosition();
    }

    public double getVelocity() {
        return encoder.getVelocity();
    }

    public double getCountsPerRevolution() {
        return encoder.getCountsPerRevolution();
    }

    public void setEncoderPosition(double value) {
        encoder.setPosition(value);
    }

    public void zeroEncoder() {
        encoder.setPosition(0);
    }

    public CANSparkMax getSparkMAX() {
        return motor;
    }
}


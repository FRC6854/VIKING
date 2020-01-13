package viking.controllers.rev;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class VikingMAX {

    private CANSparkMax motor;
    private CANPIDController pidController;
    private CANEncoder encoder;
    public double kP, kI, kD, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, maxAcc, allowedErr;

    /**
     * @param id the CAN ID for the Victor SPX
     * @param inverted is the motor inverted
     */
    public VikingMAX(int id, boolean inverted, boolean encoderInvert) {
        motor = new CANSparkMax(id, MotorType.kBrushless);

        motor.restoreFactoryDefaults();

        motor.setInverted(inverted);

        pidController = motor.getPIDController();
        encoder = motor.getEncoder();

        encoder.setInverted(encoderInvert);

        // PID coefficients
        kP = 0.00005; 
        kI = 0.000006;
        kD = 0;
        kFF = 0.000156; 
        kMaxOutput = 1; 
        kMinOutput = -1;

        // Smart Motion Coefficients
        maxVel = 2000; // rpm
        maxAcc = 1500;

        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
        pidController.setFF(kFF);
        pidController.setOutputRange(kMinOutput, kMaxOutput);

        pidController.setSmartMotionMaxVelocity(maxVel, 0);
        pidController.setSmartMotionMinOutputVelocity(0, 0);
        pidController.setSmartMotionMaxAccel(maxAcc, 0);
    }

    public void setSmartMotion(double maxVelocity, double maxAcceleration) {
        pidController.setSmartMotionMaxVelocity(maxVel, 0);
        pidController.setSmartMotionMaxAccel(maxAcc, 0);
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

    public void positionControl(double ticks) {
        pidController.setReference(ticks, ControlType.kSmartMotion);
    }

    public void velocityControl(int velocity) {
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

    public CANSparkMax getSparkMAX() {
        return motor;
    }
}


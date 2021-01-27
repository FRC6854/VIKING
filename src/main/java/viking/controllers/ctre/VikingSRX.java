package viking.controllers.ctre;

import com.ctre.phoenix.motion.BufferedTrajectoryPointStream;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class VikingSRX extends WPI_TalonSRX {

    private BufferedTrajectoryPointStream bufferedStream = new BufferedTrajectoryPointStream();

    private double metersPerRevolution = 0;

    /**
     * Constructor for VikingSRX without encoder
     * @param id the CAN ID for the Talon SRX
     * @param inverted is the motor inverted
     */
    public VikingSRX(int id, boolean inverted) {
        super(id);
        configFactoryDefault();
        setNeutralMode(NeutralMode.Brake);
        setSafetyEnabled(false);
        setInverted(inverted);
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
                            double kD, double velocity, double acceleration,
                            double metersPerRevolution) {

        super(id);

        this.metersPerRevolution = metersPerRevolution;

        configFactoryDefault();

        // Invert Power
        setInverted(inverted);

        // Invert Encoder
        setSensorPhase(sensorPhase);

        configSelectedFeedbackSensor(device, 0, 0);

        // Set-up PIDF[0]
        selectProfileSlot(0, 0);

        config_kF(0, kF, 0);
        config_kP(0, kP, 0);
        config_kI(0, kI, 0);
        config_kD(0, kD, 0);

        // Motion Magic
        configMotionCruiseVelocity(1250);
        configMotionAcceleration(1500);

        /*  
            --------------
            Motion Profile
            --------------
            In our case we must use the value 25ms for both since in our profile we use a delta time of 50ms
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

    public void setNeutralMode(NeutralMode mode) {
        setNeutralMode(mode);
    }

    public double getTicks() {
        return getSelectedSensorPosition();
    }

    public double getVelocity() {
        return getSelectedSensorVelocity();
    }

    public ControlMode getControlMode() {
        return getControlMode();
    }

    public void zeroSensor() {
        setSelectedSensorPosition(0);
    }

    /*
        --------------------------------
                Motion Profiling
        --------------------------------
    */
    public void initMotionBuffer(Double[][] profile, int totalCnt) {
        TrajectoryPoint point = new TrajectoryPoint(); // temp for for loop, since unused params are initialized
                                                    // automatically, you can alloc just one

        /* Insert every point into buffer, no limit on size */
        for (int i = 0; i < totalCnt; ++i) {

            double positionRot = profile[i][0] * (1 / metersPerRevolution);
            double velocityRPM = profile[i][1] * (1 / metersPerRevolution);
            int durationMilliseconds = profile[i][2].intValue();

            /* for each point, fill our structure and pass it to API */
            point.timeDur = durationMilliseconds;
            point.position = positionRot * 4096; // Convert Revolutions to
                                                            // Units
            point.velocity = velocityRPM * 4096 / 600.0; // Convert RPM to
                                                                    // Units/100ms
            point.profileSlotSelect0 = 0; /* which set of gains would you like to use [0,3]? */
            point.profileSlotSelect1 = 0; /* auxiliary PID [0,1], leave zero */
            point.zeroPos = (i == 0); /* set this to true on the first point */
            point.isLastPoint = ((i + 1) == totalCnt); /* set this to true on the last point */
            point.arbFeedFwd = 0; /* you can add a constant offset to add to PID[0] output here */

            bufferedStream.Write(point);
        }
    }

    public void resetMotionProfile() {
        bufferedStream.Clear();
        clearMotionProfileTrajectories();
    }

    public void motionProfileStart() {
        startMotionProfile(bufferedStream, 5, ControlMode.MotionProfile);
    }

    public boolean isMotionProfileFinished() {
        return isMotionProfileFinished();
    }
}
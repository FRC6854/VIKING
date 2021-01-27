package viking.controllers.ctre;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class VikingSPX extends WPI_VictorSPX {

    /**
     * @param id the CAN ID for the Victor SPX
     * @param inverted is the motor inverted
     */
    public VikingSPX(int id, boolean inverted) {

        super(id);

        configFactoryDefault();
        setNeutralMode(NeutralMode.Brake);
        setSafetyEnabled(false);
        setInverted(inverted);
    }

    /**
     * @param id the CAN ID for the Victor SPX
     * @param master the master motor controller
     * @param inverted is the motor inverted
     */
    public VikingSPX(int id, VikingSRX master, boolean inverted) {

        super(id);

        configFactoryDefault();
        setNeutralMode(NeutralMode.Brake);
        follow(master);
        setSafetyEnabled(false);
        setInverted(inverted);
    }

    public void percentOutput(double value) {
        set(ControlMode.PercentOutput, value);
    }

    public ControlMode getControlMode() {
        return getControlMode();
    }
}


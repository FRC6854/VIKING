package viking.controllers.ctre;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class VikingSPX extends VictorSPX {

	/**
	 * @param id the CAN ID for the Victor SPX
	 * @param inverted is the motor inverted
	 */
	public VikingSPX(int id, boolean inverted) {

		super(id);

		configFactoryDefault();
		setNeutralMode(NeutralMode.Brake);
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
		setInverted(inverted);
	}

	public void percentOutput(double value) {
		set(ControlMode.PercentOutput, value);
	}
}

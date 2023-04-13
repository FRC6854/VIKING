package viking.controllers.ctre;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import viking.controllers.CANDeviceID;

/**
 * This class is a thin wrapper around the CANTalon that reduces CAN bus / CPU
 * overhead by skipping duplicate set commands. (By default the Talon flushes
 * the Tx buffer on every set call).
 */
public class LazyVikingFX extends TalonFX {
    protected double mLastSet = Double.NaN;
    protected TalonFXControlMode mLastControlMode = null;

    public LazyVikingFX(CANDeviceID id) {
        super(id.getDeviceNumber(), id.getBus());
    }

    public double getLastSet() {
        return mLastSet;
    }

    @Override
    public void set(TalonFXControlMode mode, double value) {
        if (value != mLastSet || mode != mLastControlMode) {
            mLastSet = value;
            mLastControlMode = mode;
            super.set(mode, value);
        }
    }
}

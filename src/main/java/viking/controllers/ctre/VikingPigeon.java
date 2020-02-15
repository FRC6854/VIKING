package viking.controllers.ctre;

import com.ctre.phoenix.sensors.PigeonIMU;

public class VikingPigeon {

    private PigeonIMU gyro;

    /**
     * @param controller the master VikingSRX controller
     */
    public VikingPigeon(VikingSRX controller) {
        gyro = new PigeonIMU(controller.getTalonSRX());

        gyro.configFactoryDefault();
    }

    /**
     * Get the current yaw
     * @return the current yaw from 0 to 360
     */
    public double getYaw() {
        return gyro.getAbsoluteCompassHeading();
    }

    public PigeonIMU getPigeonIMU() {
        return gyro;
    }
}


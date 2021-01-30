package viking.controllers.ctre;

import com.ctre.phoenix.sensors.PigeonIMU;

public class VikingIMU extends PigeonIMU {

    /**
     * Default constructor of the VikingIMU
     * @param id CAN ID of the Pigeon IMU
     */
    public VikingIMU(int id) {
        super(id);
    }
}

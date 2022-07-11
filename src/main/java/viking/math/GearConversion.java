package viking.math;

public class GearConversion {
    /**
     * Calculates motor rotation given wheel rotation and gear ratio 
     * @param wheelRotation
     * @param ratio
     * @return
     */
    public static double getMotorRotation(double wheelRotation, double ratio) {
        return wheelRotation * ratio;
    }

    /**
     * Calculates wheel rotation given motor rotation and gear ratio 
     * @param motorRotation
     * @param ratio
     * @return
     */
    public static double getWheelRotation(double motorRotation, double ratio) {
        return motorRotation / ratio;
    } 
}

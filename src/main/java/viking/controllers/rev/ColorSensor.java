package viking.controllers.rev;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Class to communicate with the colour sensor and fetch accurate colours being reported from it using colour matching
 */
public class ColorSensor {

	private ColorSensorV3 colorSensor = null;
	private ColorMatch colorMatcher = new ColorMatch();

	// Preset colour targets
	private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
	private final Color kGreenTarget = new Color(0.197, 0.561, 0.240);
	private final Color kRedTarget = new Color(0.561, 0.232, 0.114);
	private final Color kYellowTarget = new Color(0.361, 0.524, 0.113);

	public ColorSensor() {
		colorSensor = new ColorSensorV3(Port.kOnboard);

		colorMatcher.addColorMatch(kBlueTarget);
		colorMatcher.addColorMatch(kGreenTarget);
		colorMatcher.addColorMatch(kRedTarget);
		colorMatcher.addColorMatch(kYellowTarget);
	}

	/**
	 * Returns difference between reported colour from the sensor and the colour target
	 * @return
	 */
	public double getColorConfidence() {
		ColorMatchResult match = colorMatcher.matchClosestColor(getColorFromSensor());

		return match.confidence;
	}

	/**
	 * Matches reported RGB values from colour sensor to preset colour targets
	 * @return
	 */
	public String getColor() {
		ColorMatchResult match = colorMatcher.matchClosestColor(getColorFromSensor());

		if (match.color == kBlueTarget) {
			return "Blue";
		} else if (match.color == kRedTarget) {
			return "Red";
		} else if (match.color == kGreenTarget) {
			return "Green";
		} else if (match.color == kYellowTarget) {
			return "Yellow";
		} else {
			return null;
		}
	}

	/**
	 * Only gets RGB values reported from colour sensor
	 * @return
	 */
	public Color getColorFromSensor() {
		return colorSensor.getColor();
	}
}

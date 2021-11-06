package viking.controllers.rev;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSensor {

	private ColorSensorV3 colorSensor = null;
	private ColorMatch colorMatcher = new ColorMatch();

	private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
	private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
	private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
	private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

	public ColorSensor() {
		colorSensor = new ColorSensorV3(Port.kOnboard);

		colorMatcher.addColorMatch(kBlueTarget);
		colorMatcher.addColorMatch(kGreenTarget);
		colorMatcher.addColorMatch(kRedTarget);
		colorMatcher.addColorMatch(kYellowTarget);
	}

	public double getColorConfidence() {
		ColorMatchResult match = colorMatcher.matchClosestColor(getColorFromSensor());

		return match.confidence;
	}

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

	public Color getColorFromSensor() {
		return colorSensor.getColor();
	}
}

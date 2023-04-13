package viking.math;

/**
 * General class for commonly used mathematical methods
 */
public class General {

	/**
	 * Remaps a specified range of numbers into a different range scale (e.g. 1-50 becomes 0-1.0)
	 * @param a
	 * @param in_min
	 * @param in_max
	 * @param out_min
	 * @param out_max
	 * @return
	 */
	public static double map(double a, double in_min, double in_max, double out_min, double out_max) {
		return ((a - in_min) * (out_max - out_min) / (in_max - in_min) + out_min);
	}

	/**
	 * Finds length of a triangle given the angle and hypotenuse
	 * @param theta
	 * @param hyp
	 * @return
	 */
	public static double findx(double theta, double hyp) {
		return hyp * (Math.sin(Math.toRadians(theta)));
	}

	/**
	 * Finds height of a triangle given the angle and hypotenuse
	 * @param theta
	 * @param hyp
	 * @return
	 */
	public static double findy(double theta, double hyp) {
		return hyp * (Math.cos(Math.toRadians(theta)));
	}

	/**
	 * Computes hypotenuse from the width and height of a triangle
	 * @param x
	 * @param y
	 * @return
	 */
	public static double hyplength(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}
}

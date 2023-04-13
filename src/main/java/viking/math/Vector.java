package viking.math;

/**
 * Vector class deals with the construction of vectors and computating vector data such as vector direction and magnitude. 
 */
public class Vector {
	public double direction;
	public double magnitude;
	public double x;
	public double y;

	public Vector(Vector vec) {
		direction = vec.direction;
		magnitude = vec.magnitude;
		x = vec.x;
		y = vec.y;
	}


	public Vector(double dir_x, double mag_y, boolean cartesian) {
		if (cartesian == false) {
			direction = dir_x;
			magnitude = mag_y;
			x = General.findx(direction, magnitude);
			y = General.findy(direction, magnitude);
		} else {
			x = dir_x;
			y = mag_y;
			direction = vectorDir(x, y);
			magnitude = General.hyplength(x, y);
		}
	}

	/**
	 * Compute the direction of vector given the x and y coordinates of its tip on the cartesian plane
	 * @param x
	 * @param y
	 * @return
	 */
	private double vectorDir(double x, double y) {
		//"Normalizes" y by setting it to be within the boundaries of a unit circle
		y = (y * (1 / General.hyplength(x, y)));

		// angle of the point (0-360)
		double deg0 = Math.floor((Math.toDegrees(Math.acos(y))) * 100) / 100; // acos
		double deg1 = Math.floor((((360 - deg0) % 360)) * 100) / 100; // phase shift

		// decides angle based on x value
		if (x > 0) {
			return deg0;
		} else {
			return deg1;
		}
	}

	/**
	 * Combine two vectors into one larger vector
	 * @param vec2
	 * @return
	 */
	public Vector add(Vector vec2) {
		double Vx = x + vec2.x;
		double Vy = y + vec2.y;

		return new Vector(Vx, Vy, true);
	}

	/**
	 * Makes new vector from difference between two vectors
	 * @param vec2
	 * @return
	 */
	public Vector subtract(Vector vec2) {
		double Vx = x - vec2.x;
		double Vy = y - vec2.y;

		return new Vector(Vx, Vy, true);
	}
}

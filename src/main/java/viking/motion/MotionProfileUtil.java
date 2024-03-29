package viking.motion;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public final class MotionProfileUtil {

	/**
	 * Load a motion profile from a CSV file
	 * PATHPLANNER FORMAT = (S,v,a,X,Y,w,r)
	 * @param profileName The name of the profile to load
	 * @return The profile as a BufferedTrajectoryPointStream
	 */
	public static Trajectory loadProfile(String profileName) {
		Trajectory.State[] states = new Trajectory.State[] {};

		try (BufferedReader br = new BufferedReader(new FileReader(
				 new File(Filesystem.getDeployDirectory(), "paths/" + profileName + ".csv")))) {
			ArrayList<double[]> points = new ArrayList<>();

			String line = "";
			while ((line = br.readLine()) != null) {
				String[] pointString = line.split(",");
				double[] point = new double[pointString.length];
				for (int i = 0; i < pointString.length; i++) {
					point[i] = Double.parseDouble(pointString[i]);
				}
				points.add(point);
			}
			states = new Trajectory.State[points.size()];
			for (int i = 0; i < points.size(); i++) {
				states[i] = new Trajectory.State(
					points.get(i)[0], points.get(i)[1], points.get(i)[2],
					// Convert PathPlanner units to PathWeaver units for the simulation
					new Pose2d(points.get(i)[3], 8.203 - points.get(i)[4],
							   new Rotation2d(Units.degreesToRadians(points.get(i)[5]))),
					Units.degreesToRadians(points.get(i)[6]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Trajectory(Arrays.asList(states));
	}
}

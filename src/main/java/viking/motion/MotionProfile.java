package viking.motion;

import com.ctre.phoenix.motion.BufferedTrajectoryPointStream;
import com.ctre.phoenix.motion.TrajectoryPoint;
import edu.wpi.first.wpilibj.Filesystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MotionProfile {
    private double pulsesPerUnit;
    private double unitsPerDegree;
    private double timeStep;
    private double[][] profile;

    public MotionProfile(String profileName, double pulsesPerUnit, double unitsPerDegree, double timeStep) {
        this.pulsesPerUnit = pulsesPerUnit;
        this.unitsPerDegree = unitsPerDegree;
        this.timeStep = timeStep;
        this.profile = loadProfile(profileName);
    }

    public double[][] getProfile() {
        return profile;
    }

    public int getLength() {
        return profile.length;
    }

    public double getPositionMeters(int i) {
        if(profile[i].length > 0){
            return profile[i][0];
        }
        return 0;
    }

    public double getVelocityMeters(int i) {
        if(profile[i].length > 1){
            return profile[i][1];
        }
        return 0;
    }

    public double getAccelerationMeters(int i) {
        if(profile[i].length > 2){
            return profile[i][2];
        }
        return 0;
    }

    public double getHeadingDegrees(int i) {
        if(this.profile[i].length > 3){
            return this.profile[i][3];
        }
        return 0;
    }

    public double getAngularVelocityDegrees(int i) {
        if(this.profile[i].length > 4){
            return this.profile[i][4];
        }
        return 0;
    }

    public double getHeadingIMU(int i) {
        return getHeadingDegrees(i) * unitsPerDegree;
    }

    public double getAngularVelocityIMU(int i) {
        return getAngularVelocityDegrees(i) * unitsPerDegree;
    }

    public double getPositionEncoder(int i) {
        return getPositionMeters(i) * pulsesPerUnit;
    }

    public double getVelocityEncoder(int i) {
        return getVelocityMeters(i) * pulsesPerUnit;
    }

    public double getAccelerationEncoder(int i) {
        return getAccelerationMeters(i) * pulsesPerUnit;
    }

    /**
     * Create a BufferedTrajectoryPointStream for the motors to follow
     * @return The given profile as a BufferedTrajectoryPointStream
     */
    public BufferedTrajectoryPointStream getProfileAsCTREBuffer(double kV, double kA) {
        BufferedTrajectoryPointStream buffer = new BufferedTrajectoryPointStream();

        for (int i = 0; i < profile.length; i++) {
            TrajectoryPoint point = new TrajectoryPoint();

            double position = getPositionEncoder(i);
            double velocity = getVelocityEncoder(i);
            double acceleration = getAccelerationEncoder(i);
            double heading = getHeadingIMU(i);
            double angularVelocity = getAngularVelocityIMU(i);

            point.timeDur = (int) (timeStep * 1000);
            point.position = position;
            point.velocity = velocity;

            point.auxiliaryPos = -heading;
            point.auxiliaryVel = -angularVelocity;
            point.profileSlotSelect0 = 0;
            point.profileSlotSelect1 = 1;
            point.zeroPos = false;
            point.isLastPoint = ((i + 1) == profile.length);
            point.arbFeedFwd = (velocity * kV) + (acceleration * kA);
            point.useAuxPID = true;

            buffer.Write(point);
        }
        
        return buffer;
    }

    /**
     * Load a motion profile from a CSV file
     * Must have the output format (p,v,a,h,o)
     * @param profileName The name of the profile to load
     * @return The profile as a BufferedTrajectoryPointStream
     */
    private double[][] loadProfile(String profileName) {
        double[][] profile = new double[][]{};
        try (BufferedReader br = new BufferedReader(new FileReader(new File(Filesystem.getDeployDirectory(), "paths/" + profileName + ".csv")))) {
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
            int dataPoints = points.get(0).length;
            profile = new double[points.size()][dataPoints];
            for (int i = 0; i < points.size(); i++) {
                for(int j = 0; j < dataPoints; j++){
                    profile[i][j] = points.get(i)[j];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profile;
    }
}

package viking;

import com.ctre.phoenix.motion.BufferedTrajectoryPointStream;
import com.ctre.phoenix.motion.TrajectoryPoint;

public class MotionProfileBuffer {

    private double metersPerRevolution = 0;

    private Double[][] leftPoints = null;
    private Double[][] rightPoints = null;

    private BufferedTrajectoryPointStream leftBuffer = new BufferedTrajectoryPointStream();
    private BufferedTrajectoryPointStream rightBuffer = new BufferedTrajectoryPointStream();

    public MotionProfileBuffer(String folder, double metersPerRevolution) {
        leftPoints = CSVFileManager.pathLeft(folder);
        rightPoints = CSVFileManager.pathRight(folder);

        writeLeftBuffer(leftPoints, leftPoints.length);
        writeRightBuffer(rightPoints, rightPoints.length);
    }

    public BufferedTrajectoryPointStream getLeftBuffer() {
        return leftBuffer;
    }

    public BufferedTrajectoryPointStream getRightBuffer() {
        return rightBuffer;
    }

    private void writeLeftBuffer(Double[][] profile, int totalCnt) {
        TrajectoryPoint point = new TrajectoryPoint();

        for (int i = 0; i < totalCnt; ++i) {

            double positionRot = profile[i][0] * (1 / metersPerRevolution);
            double velocityRPM = profile[i][1] * (1 / metersPerRevolution);
            int durationMilliseconds = profile[i][2].intValue();

            point.timeDur = durationMilliseconds;
            point.position = positionRot * 4096;
            point.velocity = velocityRPM * 4096 / 600.0;
            point.profileSlotSelect0 = 0;
            point.profileSlotSelect1 = 0;
            point.zeroPos = (i == 0);
            point.isLastPoint = ((i + 1) == totalCnt);
            point.arbFeedFwd = 0;

            leftBuffer.Write(point);
        }
    }

    private void writeRightBuffer(Double[][] profile, int totalCnt) {
        TrajectoryPoint point = new TrajectoryPoint();

        for (int i = 0; i < totalCnt; ++i) {

            double positionRot = profile[i][0] * (1 / metersPerRevolution);
            double velocityRPM = profile[i][1] * (1 / metersPerRevolution);
            int durationMilliseconds = profile[i][2].intValue();

            point.timeDur = durationMilliseconds;
            point.position = positionRot * 4096;
            point.velocity = velocityRPM * 4096 / 600.0;
            point.profileSlotSelect0 = 0;
            point.profileSlotSelect1 = 0;
            point.zeroPos = (i == 0);
            point.isLastPoint = ((i + 1) == totalCnt);
            point.arbFeedFwd = 0;

            rightBuffer.Write(point);
        }
    }
}

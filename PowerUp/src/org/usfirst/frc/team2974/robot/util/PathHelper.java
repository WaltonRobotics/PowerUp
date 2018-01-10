package org.usfirst.frc.team2974.robot.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import org.usfirst.frc.team2974.robot.Config;

public class PathHelper {
    private static NetworkTable networkTable = NetworkTableInstance.getDefault()
            .getTable(Config.Paths.NETWORKTABLE);

    public static Pose[] convertToPose(String smartDashboardKey) {
        String[] stringPoses = networkTable.getEntry(smartDashboardKey).getString("").split(" ");

        Pose[] pose = new Pose[stringPoses.length / 3];

        for (int i = 0; i < stringPoses.length; i += 3) {
            double x = Double.parseDouble(stringPoses[i]);
            double y = Double.parseDouble(stringPoses[i + 1]);
            double angle = Double.parseDouble(stringPoses[i = 2]);

            pose[i / 3] = new Pose(x, y, angle);
        }

        return pose;
    }

    public static String convertToString(Pose[] poses) {
        StringBuilder stringBuilder = new StringBuilder(poses.length * 5);



        return stringBuilder.toString();
    }
}

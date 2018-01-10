package org.usfirst.frc.team2974.robot.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import org.usfirst.frc.team2974.robot.Config;
import org.usfirst.frc.team2974.robot.exception.RobotRuntimeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class PathHelper {
    private static NetworkTable networkTable = NetworkTableInstance.getDefault()
            .getTable(Config.Paths.NETWORKTABLE);

    public static Pose[] convertToPose(String string) {
//        String[] stringPoses = networkTable.getEntry(smartDashboardKey).getString("").split(" ");

        String[] stringPoses = string.trim().split(" ");

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

        for (Pose pose : poses) {
            stringBuilder.append(pose.getX());
            stringBuilder.append(" ");
            stringBuilder.append(pose.getY());
            stringBuilder.append(" ");
            stringBuilder.append(pose.getAngle());
            stringBuilder.append(" ");
        }

        return stringBuilder.toString().trim();
    }

    public static HashMap<String, Pose[]> retrievePosesFromFile(String path) {
        if (!path.endsWith(".path"))
            throw new RobotRuntimeException("Incorrect path name: " + path);

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path))) {
            HashMap<String, Pose[]> hashMap = new HashMap<>();

            bufferedReader.lines().forEach(line -> {
                int index = line.indexOf(' ');

                String name = line.substring(0, index);
                Pose[] poses = convertToPose(line.substring(index + 1));

                hashMap.put(name, poses);
            });

            return hashMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package org.usfirst.frc.team2974.robot.command.auton;

import org.usfirst.frc.team2974.robot.util.Pose;

import static org.usfirst.frc.team2974.robot.Config.Paths.PATH_DIRECTORY;

public enum GamePosition {
    DRIVE_STATION_LEFT_LEVER_RIGHT, DRIVE_STATION_LEFT_LEVER_LEFT,
    DRIVE_STATION_CENTER_LEVER_RIGHT, DRIVE_STATION_CENTER_LEVER_LEFT,
    DRIVE_STATION_RIGHT_LEVER_RIGHT, DRIVE_STATION_RIGHT_LEVER_LEFT,;

    private final Pose[] poses;
    private

    GamePosition() {
        String fileName = PATH_DIRECTORY + name() + ".path";

    }
}

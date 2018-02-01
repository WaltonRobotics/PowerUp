package org.usfirst.frc.team2974.robot.util;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.command.auton.CrossBaselineCommand;
import org.usfirst.frc.team2974.robot.command.auton.DoNothingCommand;
import org.usfirst.frc.team2974.robot.command.auton.GamePosition;

/**
 * Used solely to get the autonomous commands from GamePosition.
 */
public class AutonLoader {

    public static CommandGroup getAutonCommands(GamePosition gamePosition) {
        switch (gamePosition) {
            case DRIVE_STATION_LEFT_LEVER_RIGHT_SCALE_RIGHT:
                return null;
            case DRIVE_STATION_LEFT_LEVER_RIGHT_SCALE_LEFT:
                return null;
            case DRIVE_STATION_LEFT_LEVER_LEFT_SCALE_RIGHT:
                return null;
            case DRIVE_STATION_LEFT_LEVER_LEFT_SCALE_LEFT:
                return null;
            case DRIVE_STATION_CENTER_LEVER_RIGHT_SCALE_RIGHT:
                return null;
            case DRIVE_STATION_CENTER_LEVER_RIGHT_SCALE_LEFT:
                return null;
            case DRIVE_STATION_CENTER_LEVER_LEFT_SCALE_RIGHT:
                return null;
            case DRIVE_STATION_CENTER_LEVER_LEFT_SCALE_LEFT:
                return null;
            case DRIVE_STATION_RIGHT_LEVER_RIGHT_SCALE_RIGHT:
                return null;
            case DRIVE_STATION_RIGHT_LEVER_RIGHT_SCALE_LEFT:
                return null;
            case DRIVE_STATION_RIGHT_LEVER_LEFT_SCALE_RIGHT:
                return null;
            case DRIVE_STATION_RIGHT_LEVER_LEFT_SCALE_LEFT:
                return null;
            case DRIVE_STATION_LEFT_LEVER_RIGHT:
                return null;
            case DRIVE_STATION_LEFT_LEVER_LEFT:
                return null;
            case DRIVE_STATION_CENTER_LEVER_RIGHT:
                return null;
            case DRIVE_STATION_CENTER_LEVER_LEFT:
                return null;
            case DRIVE_STATION_RIGHT_LEVER_RIGHT:
                return null;
            case DRIVE_STATION_RIGHT_LEVER_LEFT:
                return null;
            case DRIVE_STATION_LEFT_SCALE_RIGHT:
                return null;
            case DRIVE_STATION_LEFT_SCALE_LEFT:
                return null;
            case DRIVE_STATION_CENTER_SCALE_RIGHT:
                return null;
            case DRIVE_STATION_CENTER_SCALE_LEFT:
                return null;
            case DRIVE_STATION_RIGHT_SCALE_RIGHT:
                return null;
            case DRIVE_STATION_RIGHT_SCALE_LEFT:
                return null;
            case CROSS_BASELINE_RIGHT:
                return new CrossBaselineCommand().rightSide();
            case CROSS_BASELINE_CENTER:
                return new CrossBaselineCommand().center();
            case CROSS_BASELINE_LEFT:
                return new CrossBaselineCommand().leftSide();
            default:
            case DO_NOTHING:
                return new DoNothingCommand();
        }
    }
}

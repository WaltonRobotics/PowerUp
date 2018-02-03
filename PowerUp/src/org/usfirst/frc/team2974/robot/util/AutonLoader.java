package org.usfirst.frc.team2974.robot.util;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2974.robot.command.auton.CrossBaseLineNoEncoder;
import org.usfirst.frc.team2974.robot.command.auton.CrossBaselineCommand;
import org.usfirst.frc.team2974.robot.command.auton.DoNothingCommand;
import org.usfirst.frc.team2974.robot.command.auton.DriveToScale;
import org.usfirst.frc.team2974.robot.command.auton.GamePosition;

import static org.usfirst.frc.team2974.robot.Config.Path.CROSS_BASELINE_Y;

/**
 * Used solely to get the autonomous commands from GamePosition.
 */
public class AutonLoader {

    public static CommandGroup getAutonCommands(GamePosition gamePosition) {
        switch (gamePosition) {
            case DRIVE_STATION_LEFT_LEVER_RIGHT_SCALE_RIGHT: // dont do this
                return null;
            case DRIVE_STATION_LEFT_LEVER_RIGHT_SCALE_LEFT: // scale only
                return null;
            case DRIVE_STATION_LEFT_LEVER_LEFT_SCALE_RIGHT: // switch only
                return null;
            case DRIVE_STATION_LEFT_LEVER_LEFT_SCALE_LEFT:
                return null;
            case DRIVE_STATION_CENTER_LEVER_RIGHT_SCALE_RIGHT: // never do scale from center
                return null;
            case DRIVE_STATION_CENTER_LEVER_RIGHT_SCALE_LEFT: // never do scale from center
                return null;
            case DRIVE_STATION_CENTER_LEVER_LEFT_SCALE_RIGHT: // never do scale from center
                return null;
            case DRIVE_STATION_CENTER_LEVER_LEFT_SCALE_LEFT: // never do scale from center
                return null;
            case DRIVE_STATION_RIGHT_LEVER_RIGHT_SCALE_RIGHT:
                return null;
            case DRIVE_STATION_RIGHT_LEVER_RIGHT_SCALE_LEFT: // switch only
            	return null;
            case DRIVE_STATION_RIGHT_LEVER_LEFT_SCALE_RIGHT: // scale only
                return null;
            case DRIVE_STATION_RIGHT_LEVER_LEFT_SCALE_LEFT: // dont do this
                return null;

            // SWITCH ONLY
            case DRIVE_STATION_LEFT_LEVER_RIGHT: // dont do this
                return null;
            case DRIVE_STATION_LEFT_LEVER_LEFT:
                return null;
            case DRIVE_STATION_CENTER_LEVER_RIGHT:
                return null;
            case DRIVE_STATION_CENTER_LEVER_LEFT:
                return null;
            case DRIVE_STATION_RIGHT_LEVER_RIGHT:
                return null;
            case DRIVE_STATION_RIGHT_LEVER_LEFT: // dont do this
                return null;

            // SCALE ONLY
            case DRIVE_STATION_LEFT_SCALE_RIGHT: // dont do this
                return new CrossBaselineCommand().left(CROSS_BASELINE_Y);
            case DRIVE_STATION_LEFT_SCALE_LEFT:
                return new DriveToScale().left();
            case DRIVE_STATION_CENTER_SCALE_RIGHT: // never directly do
                return null; // TODO
            case DRIVE_STATION_CENTER_SCALE_LEFT: // never directly do
                return null; // TODO
            case DRIVE_STATION_RIGHT_SCALE_RIGHT:
                return new DriveToScale().right();
            case DRIVE_STATION_RIGHT_SCALE_LEFT: // dont do this
                return new CrossBaselineCommand().right(CROSS_BASELINE_Y);

            // CROSSING BASELINE
            case CROSS_BASELINE_RIGHT:
              //  return new CrossBaselineCommand().right(CROSS_BASELINE_Y);
            	return new CrossBaseLineNoEncoder().right(); 
            case CROSS_BASELINE_CENTER:
              //  return new CrossBaselineCommand().center();
            	return new CrossBaseLineNoEncoder().center(); 
            case CROSS_BASELINE_LEFT:
                //return new CrossBaselineCommand().left(CROSS_BASELINE_Y);
            	return new CrossBaseLineNoEncoder().left();  
            default:
            case DO_NOTHING:
                return new DoNothingCommand();
        }
    }
}

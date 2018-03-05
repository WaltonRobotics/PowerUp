package org.usfirst.frc.team2974.robot.util;

import static org.usfirst.frc.team2974.robot.Config.Path.CROSS_BASELINE_Y;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.CROSS_BASELINE_CENTER;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.CROSS_BASELINE_LEFT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.CROSS_BASELINE_RIGHT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.DRIVE_STATION_CENTER_SWITCH_LEFT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.DRIVE_STATION_CENTER_SWITCH_RIGHT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.DRIVE_STATION_LEFT_SCALE_LEFT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.DRIVE_STATION_LEFT_SWITCH_LEFT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.DRIVE_STATION_RIGHT_SCALE_RIGHT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.DRIVE_STATION_RIGHT_SWITCH_RIGHT;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.command.auton.CrossBaseline;
import org.usfirst.frc.team2974.robot.command.auton.DoNothingCommand;
import org.usfirst.frc.team2974.robot.command.auton.DriveSwitchScale;
import org.usfirst.frc.team2974.robot.command.auton.DriveToScale;
import org.usfirst.frc.team2974.robot.command.auton.DriveToSwitch;
import org.usfirst.frc.team2974.robot.command.auton.GamePosition;

/**
 * Used solely to get the autonomous commands from GamePosition.
 */
public class AutonLoader {

	private AutonLoader() {
	}

	public static CommandGroup getAutonCommands(GamePosition gamePosition) {
		switch (gamePosition) {
			case DRIVE_STATION_LEFT_SWITCH_RIGHT_SCALE_RIGHT: // don't do this
				return getAutonCommands(CROSS_BASELINE_LEFT);
			case DRIVE_STATION_LEFT_SWITCH_RIGHT_SCALE_LEFT: // scale only
				return getAutonCommands(DRIVE_STATION_LEFT_SCALE_LEFT);
			case DRIVE_STATION_LEFT_SWITCH_LEFT_SCALE_RIGHT: // switch only
				return getAutonCommands(DRIVE_STATION_LEFT_SWITCH_LEFT);
			case DRIVE_STATION_LEFT_SWITCH_LEFT_SCALE_LEFT:
				return new DriveSwitchScale().left();
			case DRIVE_STATION_CENTER_SWITCH_RIGHT_SCALE_RIGHT: // never do scale from center
				return getAutonCommands(DRIVE_STATION_CENTER_SWITCH_RIGHT);
			case DRIVE_STATION_CENTER_SWITCH_RIGHT_SCALE_LEFT: // never do scale from center
				return getAutonCommands(DRIVE_STATION_CENTER_SWITCH_RIGHT);
			case DRIVE_STATION_CENTER_SWITCH_LEFT_SCALE_RIGHT: // never do scale from center
				return getAutonCommands(DRIVE_STATION_CENTER_SWITCH_LEFT);
			case DRIVE_STATION_CENTER_SWITCH_LEFT_SCALE_LEFT: // never do scale from center
				return getAutonCommands(DRIVE_STATION_CENTER_SWITCH_LEFT);
			case DRIVE_STATION_RIGHT_SWITCH_RIGHT_SCALE_RIGHT:
				return new DriveSwitchScale().right();
			case DRIVE_STATION_RIGHT_SWITCH_RIGHT_SCALE_LEFT: // switch only
				return getAutonCommands(DRIVE_STATION_RIGHT_SWITCH_RIGHT);
			case DRIVE_STATION_RIGHT_SWITCH_LEFT_SCALE_RIGHT: // scale only
				return getAutonCommands(DRIVE_STATION_RIGHT_SCALE_RIGHT);
			case DRIVE_STATION_RIGHT_SWITCH_LEFT_SCALE_LEFT: // don't do this
				return getAutonCommands(CROSS_BASELINE_RIGHT);

			// SWITCH ONLY
			case DRIVE_STATION_LEFT_SWITCH_RIGHT: // don't do this
				return getAutonCommands(CROSS_BASELINE_LEFT);
			case DRIVE_STATION_LEFT_SWITCH_LEFT:
				return new DriveToSwitch().left();
			case DRIVE_STATION_CENTER_SWITCH_RIGHT:
				return new DriveToSwitch().center();
			case DRIVE_STATION_CENTER_SWITCH_LEFT:
				return new DriveToSwitch().center();
			case DRIVE_STATION_RIGHT_SWITCH_RIGHT:
				return new DriveToSwitch().right();
			case DRIVE_STATION_RIGHT_SWITCH_LEFT: // don't do this
				return getAutonCommands(CROSS_BASELINE_RIGHT);

			// SCALE ONLY
			case DRIVE_STATION_LEFT_SCALE_RIGHT: // don't do this
				return getAutonCommands(CROSS_BASELINE_LEFT);
			case DRIVE_STATION_LEFT_SCALE_LEFT:
				return new DriveToScale().left();
			case DRIVE_STATION_CENTER_SCALE_RIGHT: // never directly do
				return getAutonCommands(CROSS_BASELINE_CENTER);
			case DRIVE_STATION_CENTER_SCALE_LEFT: // never directly do
				return getAutonCommands(CROSS_BASELINE_CENTER);
			case DRIVE_STATION_RIGHT_SCALE_RIGHT:
				return new DriveToScale().right();
			case DRIVE_STATION_RIGHT_SCALE_LEFT: // don't do this
				return getAutonCommands(CROSS_BASELINE_RIGHT);

			// CROSSING BASELINE
			case CROSS_BASELINE_RIGHT:
				return new CrossBaseline().right(CROSS_BASELINE_Y);
			case CROSS_BASELINE_CENTER:
				return new CrossBaseline().center();
			case CROSS_BASELINE_LEFT:
				return new CrossBaseline().left(CROSS_BASELINE_Y);
			default:
			case DO_NOTHING:
				return new DoNothingCommand();
		}
	}
}

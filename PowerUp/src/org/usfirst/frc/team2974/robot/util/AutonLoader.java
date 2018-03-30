package org.usfirst.frc.team2974.robot.util;

import static org.usfirst.frc.team2974.robot.Config.Path.CROSS_BASELINE_Y;
import static org.usfirst.frc.team2974.robot.Robot.confuseEnemy;
import static org.usfirst.frc.team2974.robot.Robot.drivetrain;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.CROSS_BASELINE_CENTER;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.CROSS_BASELINE_LEFT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.CROSS_BASELINE_RIGHT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.DRIVE_STATION_CENTER_SWITCH_LEFT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.DRIVE_STATION_CENTER_SWITCH_RIGHT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.DRIVE_STATION_LEFT_SWITCH_LEFT;
import static org.usfirst.frc.team2974.robot.command.auton.GamePosition.DRIVE_STATION_RIGHT_SWITCH_RIGHT;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.command.auton.CrossBaseline;
import org.usfirst.frc.team2974.robot.command.auton.DoNothingCommand;
import org.usfirst.frc.team2974.robot.command.auton.DriveToScale;
import org.usfirst.frc.team2974.robot.command.auton.DriveToSwitch;
import org.usfirst.frc.team2974.robot.command.auton.GamePosition;

/**
 * Used solely to get the autonomous commands from GamePosition.
 */
public final class AutonLoader {

	private AutonLoader() {
	}

	public static CommandGroup getAutonCommands(GamePosition gamePosition) {
		drivetrain.startControllerMotion(gamePosition.getPosition());

		switch (gamePosition) {
			case DRIVE_STATION_LEFT_SWITCH_RIGHT_SCALE_RIGHT: // behind switch auton
				return new DriveToSwitch().startLeftEndRight();
			case DRIVE_STATION_LEFT_SWITCH_RIGHT_SCALE_LEFT: // scale only
			{
				DriveToScale driveToSwitch = new DriveToScale().left();

				if (Robot.getDoNumberOfCubes() == 2) {
					driveToSwitch.toCube();

					if (!confuseEnemy()) {
						driveToSwitch.backToScale();
					}
				}

				return driveToSwitch;
			}
//				return getAutonCommands(DRIVE_STATION_LEFT_SCALE_LEFT);
			case DRIVE_STATION_LEFT_SWITCH_LEFT_SCALE_RIGHT: // switch only
				return getAutonCommands(DRIVE_STATION_LEFT_SWITCH_LEFT);
			case DRIVE_STATION_LEFT_SWITCH_LEFT_SCALE_LEFT:// scale only
			{
				DriveToScale driveToSwitch = new DriveToScale().left();

				if (Robot.getDoNumberOfCubes() == 2) {
					driveToSwitch.toCube();

					if (!confuseEnemy()) {
						driveToSwitch.toSwitch();
					}
				}

				return driveToSwitch;
			}
			case DRIVE_STATION_CENTER_SWITCH_RIGHT_SCALE_RIGHT: // never do scale from center
				return getAutonCommands(DRIVE_STATION_CENTER_SWITCH_RIGHT);
			case DRIVE_STATION_CENTER_SWITCH_RIGHT_SCALE_LEFT: // never do scale from center
				return getAutonCommands(DRIVE_STATION_CENTER_SWITCH_RIGHT);
			case DRIVE_STATION_CENTER_SWITCH_LEFT_SCALE_RIGHT: // never do scale from center
				return getAutonCommands(DRIVE_STATION_CENTER_SWITCH_LEFT);
			case DRIVE_STATION_CENTER_SWITCH_LEFT_SCALE_LEFT: // never do scale from center
				return getAutonCommands(DRIVE_STATION_CENTER_SWITCH_LEFT);
			case DRIVE_STATION_RIGHT_SWITCH_RIGHT_SCALE_RIGHT: {
				DriveToScale driveToSwitch = new DriveToScale().right();

				if (Robot.getDoNumberOfCubes() == 2) {
					driveToSwitch.toCube();

					if (!confuseEnemy()) {
						driveToSwitch.backToScale();
					}
				}

				return driveToSwitch;
			}
			case DRIVE_STATION_RIGHT_SWITCH_RIGHT_SCALE_LEFT: // switch only
				return getAutonCommands(DRIVE_STATION_RIGHT_SWITCH_RIGHT);
			case DRIVE_STATION_RIGHT_SWITCH_LEFT_SCALE_RIGHT: // scale only
			{
				DriveToScale driveToSwitch = new DriveToScale().right();

				if (Robot.getDoNumberOfCubes() == 2) {
					driveToSwitch.toCube();

					if (!confuseEnemy()) {
						driveToSwitch.backToScale();
					}
				}

				return driveToSwitch;
			}
//				return getAutonCommands(DRIVE_STATION_RIGHT_SCALE_RIGHT);
			case DRIVE_STATION_RIGHT_SWITCH_LEFT_SCALE_LEFT: // Behind switch auton
				return new DriveToSwitch().startRightEndLeft();

			// SWITCH ONLY
//			case DRIVE_STATION_LEFT_SWITCH_RIGHT: // don't do this
//				return new DriveToSwitch().startLeftEndRight();
			case DRIVE_STATION_LEFT_SWITCH_LEFT:
				return new DriveToSwitch().left();
			case DRIVE_STATION_CENTER_SWITCH_RIGHT: {
				DriveToSwitch driveToSwitch = new DriveToSwitch().center();

				if (Robot.getDoNumberOfCubes() == 2) {
					driveToSwitch.toPyramid();

					if (!confuseEnemy()) {
						driveToSwitch.nextCube();
					}
				}

				return driveToSwitch;
			}
			case DRIVE_STATION_CENTER_SWITCH_LEFT: {
				DriveToSwitch driveToSwitch = new DriveToSwitch().center();

				if (Robot.getDoNumberOfCubes() == 2) {
					driveToSwitch.toPyramid();

					if (!confuseEnemy()) {
						driveToSwitch.nextCube();
					}
				}

				return driveToSwitch;
			}
			case DRIVE_STATION_RIGHT_SWITCH_RIGHT:
				return new DriveToSwitch().right();
//			case DRIVE_STATION_RIGHT_SWITCH_LEFT: // don't do this
//				return new DriveToSwitch().startRightEndLeft();

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

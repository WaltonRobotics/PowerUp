package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Elevator.MEDIUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.LOW_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.L0;
import static org.usfirst.frc.team2974.robot.Config.Path.L4;
import static org.usfirst.frc.team2974.robot.Config.Path.L5;
import static org.usfirst.frc.team2974.robot.Config.Path.R0;
import static org.usfirst.frc.team2974.robot.Config.Path.R4;
import static org.usfirst.frc.team2974.robot.Config.Path.R5;

import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.frc.team2974.robot.util.AutonUtil;

/**
 * Drives to the switch from the chosen (left(), right(), center()) starting position.
 */
public class DriveToSwitch extends AutonOption {

	public DriveToSwitch left() {
		return AutonUtil.driveToSinglePoint(this, MEDIUM_HEIGHT, L0, L4, L5);
	}

	public DriveToSwitch right() {
		return AutonUtil.driveToSinglePoint(this, MEDIUM_HEIGHT, R0, R4, R5);
	}

	public DriveToSwitch center() {
		addParallel(new ElevatorTarget(MEDIUM_HEIGHT));
		addSequential(new CrossBaseline().center()); // :)
		addSequential(new WaitCommand(0.5));
		addSequential(new DropCube());
		addSequential(new ElevatorTarget(LOW_HEIGHT));
		
		setOptionSelected(true);

		return this;
	}
}

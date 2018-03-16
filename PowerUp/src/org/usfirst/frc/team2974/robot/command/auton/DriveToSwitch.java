package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Elevator.LOW_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.MEDIUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.L0;
import static org.usfirst.frc.team2974.robot.Config.Path.L1;
import static org.usfirst.frc.team2974.robot.Config.Path.L10;
import static org.usfirst.frc.team2974.robot.Config.Path.L4;
import static org.usfirst.frc.team2974.robot.Config.Path.L5;
import static org.usfirst.frc.team2974.robot.Config.Path.L9;
import static org.usfirst.frc.team2974.robot.Config.Path.R0;
import static org.usfirst.frc.team2974.robot.Config.Path.R1;
import static org.usfirst.frc.team2974.robot.Config.Path.R10;
import static org.usfirst.frc.team2974.robot.Config.Path.R4;
import static org.usfirst.frc.team2974.robot.Config.Path.R5;
import static org.usfirst.frc.team2974.robot.Config.Path.R9;

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
//		addSequential(new WaitCommand(0.5));
		addSequential(new DropCube());

		addParallel(new ElevatorTarget(LOW_HEIGHT)); // Lowers elevator and backs up
		addSequential(new CrossBaseline().backUp());

		addSequential(new CrossBaseline().toPyramid());
		//FIXME Intaking cube does not work
		addParallel(new IntakeCube()); //runs intake and moves forward
		addSequential(new CrossBaseline().fromPyramid());
		addSequential(new CrossBaseline().returnToSwitch());
		setOptionSelected(true);

		return this;
	}

	public DriveToSwitch startRightEndLeft() {
		//test these points
		return AutonUtil.driveToSinglePoint(this, MEDIUM_HEIGHT, R0, R1, L10, L9);
	}

	public DriveToSwitch startLeftEndRight() {
		//test these points
		return AutonUtil.driveToSinglePoint(this, MEDIUM_HEIGHT, L0, L1, R10, R9);
	}
}

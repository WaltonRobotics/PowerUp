package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Elevator.LOW_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.MEDIUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.util.AutonUtil;
import org.waltonrobotics.controller.Pose;

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

		addParallel(AutonUtil.createSequence(new DropCube(), new ElevatorTarget(LOW_HEIGHT)));

		setOptionSelected(true);

		return this;
	}

	public DriveToSwitch toPyramid() {
		backUp();

		addParallel(AutonUtil.createSequence(
				new WaitCommand(4) /*TODO make a sort of wait for distance motion command*/,
				new IntakeCube()
		));

		toPyramidC();

		setOptionSelected(true);
		return this;
	}

	public DriveToSwitch nextCube() {
		fromPyramid(); //moves back from pyramid
		returnToSwitch(); //splines to correct side of switch

		setOptionSelected(true);

		return this;
	}

	public DriveToSwitch startRightEndLeft() {
		return AutonUtil
			.driveToSinglePoint(this, MEDIUM_HEIGHT, R0, R1, new Pose(2, 5.8, Math.toRadians(180)),
				L13, L7, L5);
	}

	public DriveToSwitch startLeftEndRight() {
		return AutonUtil.driveToSinglePoint(this, MEDIUM_HEIGHT, L0, L1, L6, R13, R7, R5);
	}

	private void backUp() {
		// From both left and right, splines to the center
		if (Robot.getSwitchPosition() == 'R') {
			addSequential(SimpleSpline.pathFromPosesWithAngleAndScale(true, 2, 1, C1, C4));
		} else {
			addSequential(SimpleSpline.pathFromPosesWithAngleAndScale(true, 2, 1, C2, C4));
		}
	}

	private void returnToSwitch() {
		addParallel(new ElevatorTarget(MEDIUM_HEIGHT));
		// From both left and right, splines to the center
		if (Robot.getSwitchPosition() == 'R') {
			addSequential(SimpleSpline.pathFromPosesWithAngleAndScale(false, 1, 2, C6, C7));
		} else {
			addSequential(SimpleSpline.pathFromPosesWithAngleAndScale(false, 1, 2, C6, C8));

		}

//		addSequential(new WaitCommand(1));
		addSequential(new DropCube());
	}

	private void toPyramidC() {
		// moves forward to the pyramid to pick up a cube
		if (Robot.getSwitchPosition() == 'R') {
			addSequential(SimpleSpline.pathFromPosesWithAngle(false, C4, C5));
		} else {
			addSequential(SimpleSpline.pathFromPosesWithAngle(false, C4, C9));
		}
	}

	private void fromPyramid() {
		// moves forward to the pyramid to pick up a cube
		addSequential(SimpleSpline.pathFromPosesWithAngle(true, C5, C6));
	}
}

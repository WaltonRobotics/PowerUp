package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Elevator.HIGH_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.LOW_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.MEDIUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.ACCELERATION_MAX;
import static org.usfirst.frc.team2974.robot.Config.Path.ANGLE_180;
import static org.usfirst.frc.team2974.robot.Config.Path.C1;
import static org.usfirst.frc.team2974.robot.Config.Path.C2;
import static org.usfirst.frc.team2974.robot.Config.Path.C4;
import static org.usfirst.frc.team2974.robot.Config.Path.C5;
import static org.usfirst.frc.team2974.robot.Config.Path.C6;
import static org.usfirst.frc.team2974.robot.Config.Path.C7;
import static org.usfirst.frc.team2974.robot.Config.Path.C8;
import static org.usfirst.frc.team2974.robot.Config.Path.C9;
import static org.usfirst.frc.team2974.robot.Config.Path.L0;
import static org.usfirst.frc.team2974.robot.Config.Path.L1;
import static org.usfirst.frc.team2974.robot.Config.Path.L13;
import static org.usfirst.frc.team2974.robot.Config.Path.L15;
import static org.usfirst.frc.team2974.robot.Config.Path.L16;
import static org.usfirst.frc.team2974.robot.Config.Path.L4;
import static org.usfirst.frc.team2974.robot.Config.Path.L5;
import static org.usfirst.frc.team2974.robot.Config.Path.L7;
import static org.usfirst.frc.team2974.robot.Config.Path.R0;
import static org.usfirst.frc.team2974.robot.Config.Path.R1;
import static org.usfirst.frc.team2974.robot.Config.Path.R13;
import static org.usfirst.frc.team2974.robot.Config.Path.R15;
import static org.usfirst.frc.team2974.robot.Config.Path.R16;
import static org.usfirst.frc.team2974.robot.Config.Path.R4;
import static org.usfirst.frc.team2974.robot.Config.Path.R5;
import static org.usfirst.frc.team2974.robot.Config.Path.R7;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.util.AutonUtil;
import org.waltonrobotics.command.SimpleLine;
import org.waltonrobotics.command.SimpleSpline;
import org.waltonrobotics.metadata.Pose;

/**
 * Drives to the switch from the chosen (left(), right(), center()) starting position.
 */
public class DriveToSwitch extends AutonOption {

	private final double TRANSITION_VELOCITY = 1.5;

	public DriveToSwitch left() {
		return AutonUtil.driveToSinglePoint(this, MEDIUM_HEIGHT, L0, L4, L5);
	}

	public DriveToSwitch right() {
		return AutonUtil.driveToSinglePoint(this, MEDIUM_HEIGHT, R0, R4, R5);
	}

	public DriveToSwitch center() {
		addParallel(new ElevatorTarget(MEDIUM_HEIGHT));
		addSequential(new CrossBaseline().center()); // :)
//		addSequential(new ElevatorTarget(MEDIUM_HEIGHT));

//		addSequential(new DropCube());
//		addSequential(new ElevatorTarget(LOW_HEIGHT));
		addParallel(AutonUtil.createSequence(new DropCube(), new ElevatorTarget(LOW_HEIGHT)));

		setOptionSelected(true);

		return this;
	}

	public DriveToSwitch toPyramid() {
		backUp();

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
		CommandGroup commandGroup = new CommandGroup();
		commandGroup.addSequential(new WaitCommand(2));
		commandGroup.addSequential(new ElevatorTarget(HIGH_HEIGHT));

		addParallel(commandGroup);
		addSequential(SimpleSpline.pathFromPosesWithAngle(3, ACCELERATION_MAX, 0, TRANSITION_VELOCITY, false, R0, R1,
			new Pose(2, 5.5, ANGLE_180), // TODO: MOVE TO CONFIG
			new Pose(L13.getX(), L13.getY(), R13.getAngle())));
		addSequential(SimpleSpline
			.pathFromPosesWithAngle(TRANSITION_VELOCITY, ACCELERATION_MAX, false,
				new Pose(L13.getX(), L13.getY(), R13.getAngle()), L7,
				L15, L16));

		addSequential(SimpleLine.lineWithDistance(true, L16, -0.7));
		addSequential(new DropCube());

		setOptionSelected(true);
		return this;

//		return AutonUtil
//			.driveToSinglePoint(this, 3.0, ACCELERATION_MAX, .5, HIGH_HEIGHT, false, R0, R1,
//				new Pose(2, 5.8, ANGLE_180), // TODO: MOVE TO CONFIG
//				L13, L7, L15, L16);
	}

	public DriveToSwitch startLeftEndRight() {
		CommandGroup commandGroup = new CommandGroup();
		commandGroup.addSequential(new WaitCommand(2));
		commandGroup.addSequential(new ElevatorTarget(HIGH_HEIGHT));

		addParallel(commandGroup);
		addSequential(SimpleSpline.pathFromPosesWithAngle(3, ACCELERATION_MAX, 0, TRANSITION_VELOCITY, false, L0, L1,
			new Pose(-2, 5.5, ANGLE_180), // TODO: MOVE TO CONFIG
			new Pose(R13.getX(), R13.getY(), L13.getAngle())));
		addSequential(SimpleSpline
			.pathFromPosesWithAngle(TRANSITION_VELOCITY, ACCELERATION_MAX, false,
				new Pose(R13.getX(), R13.getY(), L13.getAngle()),
				R7,
				R15, R16));
		addSequential(SimpleLine.lineWithDistance(true, R16, -.7));
		addSequential(new DropCube());

		setOptionSelected(true);

		return this;
//		return AutonUtil.driveToSinglePoint(this, 3.0, ACCELERATION_MAX, .5, HIGH_HEIGHT, false, L0, L1,
//			new Pose(-2, 5.5, ANGLE_180), // TODO: MOVE TO CONFIG
//			R13, R7, R15, R16);
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
		CommandGroup group = new CommandGroup();
		group.addParallel(new ElevatorTarget(MEDIUM_HEIGHT));
		// From both left and right, splines to the center
		if (Robot.getSwitchPosition() == 'R') {
			group.addSequential(SimpleSpline.pathFromPosesWithAngleAndScale(false, 1, 2, C6, C7));
		} else {
			group.addSequential(SimpleSpline.pathFromPosesWithAngleAndScale(false, 1, 2, C6, C8));
		}

//		addSequential(new WaitCommand(1));
		group.addSequential(new DropCube());

		addSequential(group);
	}

	private void toPyramidC() {
		CommandGroup group = new CommandGroup();
		group.addParallel(AutonUtil.createSequence(new WaitCommand(1.0), new IntakeCube()));

		// moves forward to the pyramid to pick up a cube
		if (Robot.getSwitchPosition() == 'R') {
//			SimpleSpline toCube = SimpleSpline.pathFromPosesWithAngle(false, C4, C5);
			group.addSequential(SimpleSpline.pathFromPosesWithAngle(false, C4, C5));

//			addParallel(AutonUtil.createSequence(
//				new WaitUntilPercent(toCube.getSpline(), .90),
//				new IntakeCube()
//			));

//			addSequential(toCube);
		} else {
//			SimpleSpline toCube = SimpleSpline.pathFromPosesWithAngle(false, C4, C9);
			group.addSequential(SimpleSpline.pathFromPosesWithAngle(false, C4, C9));

//			addParallel(AutonUtil.createSequence(
//				new WaitUntilPercent(toCube.getSpline(), .90),
//				new IntakeCube()
//			));

//			addSequential(toCube);
		}

		addSequential(group);
	}

	private void fromPyramid() {
		// moves forward to the pyramid to pick up a cube
		addSequential(SimpleSpline.pathFromPosesWithAngle(true, C5, C6));
	}
}

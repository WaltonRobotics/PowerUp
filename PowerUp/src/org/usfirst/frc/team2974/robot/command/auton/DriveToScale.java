package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Elevator.HIGH_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.LOW_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.MEDIUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.ACCELERATION_MAX;
import static org.usfirst.frc.team2974.robot.Config.Path.L0;
import static org.usfirst.frc.team2974.robot.Config.Path.L1;
import static org.usfirst.frc.team2974.robot.Config.Path.L11;
import static org.usfirst.frc.team2974.robot.Config.Path.L14;
import static org.usfirst.frc.team2974.robot.Config.Path.L2;
import static org.usfirst.frc.team2974.robot.Config.Path.L3;
import static org.usfirst.frc.team2974.robot.Config.Path.L9;
import static org.usfirst.frc.team2974.robot.Config.Path.R0;
import static org.usfirst.frc.team2974.robot.Config.Path.R1;
import static org.usfirst.frc.team2974.robot.Config.Path.R11;
import static org.usfirst.frc.team2974.robot.Config.Path.R14;
import static org.usfirst.frc.team2974.robot.Config.Path.R2;
import static org.usfirst.frc.team2974.robot.Config.Path.R3;
import static org.usfirst.frc.team2974.robot.Config.Path.R9;
import static org.usfirst.frc.team2974.robot.Config.Path.VELOCITY_MAX;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.WaitForChildren;
import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.util.AutonUtil;
import org.waltonrobotics.controller.Pose;

/**
 * Drives to scale from the left or the right starting position.
 */
public class DriveToScale extends AutonOption {


	public DriveToScale left() {
		return driveToScale(new Pose[]{L0, L1, L2, L3}, L11);
	}

	@Override
	public AutonOption center() {
		return this;
	}

	public DriveToScale right() {
		return driveToScale(new Pose[]{R0, R1, R2, R3}, R11);
	}

	private DriveToScale driveToScale(Pose[] toScale, Pose forwardPoint) {
		addParallel(AutonUtil.createSequence(new WaitUntilPercent(.45), new ElevatorTarget(HIGH_HEIGHT)));
		addSequential(SimpleSpline.pathFromPosesWithAngle(false, toScale));
		addSequential(new ElevatorTarget(HIGH_HEIGHT));

		forwardsDropBackwards(toScale[toScale.length - 1], forwardPoint);
		setOptionSelected(true);

		return this;
	}

	private void forwardsDropBackwards(Pose startPosition, Pose endPosition) {
		addSequential(SimpleSpline
			.pathFromPosesWithAngleAndScale(
				VELOCITY_MAX / 10,
				ACCELERATION_MAX,
				false,
				0.1,
				0.1,
				startPosition,
				endPosition));

//		addSequential(new WaitCommand(1)); // TODO remove this?
		addSequential(new DropCube());

		addSequential(SimpleSpline
			.pathFromPosesWithAngleAndScale(
				VELOCITY_MAX / 10,
				ACCELERATION_MAX,
				true,
				0.1,
				0.1,
				endPosition,
				startPosition));

		setOptionSelected(true);
	}

	public void toCube() {
		if (Robot.getSwitchPosition() == 'R') {
			scaleSwitch(R3, R14, R9);
		} else {
			scaleSwitch(L3, L14, L9);
		}

		setOptionSelected(true);
	}

	public void toSwitch() {
		addSequential(new ElevatorTarget(MEDIUM_HEIGHT));
		addSequential(new DropCube());

		setOptionSelected(true);
	}

	public void backToScale() { //FIXME fix sequencing
		if (Robot.getScalePosition() == 'R') {
			driveBackToScale(R9, R14, R3);
			forwardsDropBackwards(R3, R11);
		} else {
			driveBackToScale(L9, L14, L3);
			forwardsDropBackwards(L3, L11);
		}
		setOptionSelected(true);

	}

	private void driveBackToScale(Pose startPosition, Pose pointTurn, Pose endPosition) {
		addParallel(AutonUtil.createSequence(new WaitUntilPercent(.5), new ElevatorTarget(HIGH_HEIGHT)));
		addSequential(
			SimpleSpline.pathFromPosesWithAngleAndScale(true, 0.7, 1.2, startPosition, pointTurn));

		addSequential(SimpleTurn.pointTurn(pointTurn, endPosition));
		setOptionSelected(true);
	}

	private void scaleSwitch(Pose scaleEndPosition, Pose pointTurn, Pose cubePosition) {
		addParallel(new ElevatorTarget(LOW_HEIGHT));

		addSequential(SimpleTurn.pointTurn(scaleEndPosition, pointTurn));
		addSequential(new ElevatorTarget(LOW_HEIGHT));

		addParallel(AutonUtil.createSequence(new WaitUntilPercent(.5), new IntakeCube()));
		addSequential(
			SimpleSpline.pathFromPosesWithAngleAndScale(false, 1.2, 0.7, pointTurn, cubePosition));

		setOptionSelected(true);

	}
}

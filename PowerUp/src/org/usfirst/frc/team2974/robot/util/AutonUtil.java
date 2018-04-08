package org.usfirst.frc.team2974.robot.util;

import static org.usfirst.frc.team2974.robot.Config.Path.ACCELERATION_MAX;
import static org.usfirst.frc.team2974.robot.Config.Path.VELOCITY_MAX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.command.auton.AutonOption;
import org.usfirst.frc.team2974.robot.command.auton.DropCube;
import org.usfirst.frc.team2974.robot.command.auton.ElevatorTarget;
import org.usfirst.frc.team2974.robot.command.auton.SimpleSpline;
import org.usfirst.frc.team2974.robot.command.auton.WaitUntilPercent;
import org.waltonrobotics.controller.Pose;

public final class AutonUtil {

	private AutonUtil() {
	}

	public static <T extends AutonOption> T driveToSinglePoint(T auton, double elevatorHeight,
		Pose... splinePoints) {
		return driveToSinglePoint(auton, elevatorHeight, false, splinePoints);
	}

	public static <T extends AutonOption> T driveToSinglePoint(T auton, double elevatorHeight,
		boolean isBackwards,
		Pose... splinePoints) {
		return driveToSinglePoint(auton, VELOCITY_MAX, ACCELERATION_MAX, elevatorHeight, isBackwards, splinePoints);
	}

	public static <T extends AutonOption> T driveToSinglePoint(T auton, double maxVelocity, double maxAcceleration,
		double elevatorHeight,
		boolean isBackwards,
		Pose... splinePoints) {
		return driveToSinglePoint(auton, maxVelocity, maxAcceleration, 0, elevatorHeight, isBackwards, splinePoints);
	}

	public static <T extends AutonOption> T driveToSinglePoint(T auton, double maxVelocity, double maxAcceleration,
		double putUpAtPercent, double elevatorHeight,
		boolean isBackwards,
		Pose... splinePoints) {

		SimpleSpline driveToPoint = SimpleSpline
			.pathFromPosesWithAngle(maxVelocity, maxAcceleration, isBackwards, splinePoints);

		auton.addParallel(
			AutonUtil.createSequence(new WaitUntilPercent(driveToPoint.getSpline(), putUpAtPercent),
				new ElevatorTarget(elevatorHeight)));
		auton.addSequential(driveToPoint);
		auton.addSequential(new ElevatorTarget(elevatorHeight));
//		auton.addSequential(new WaitCommand(1));
		auton.addSequential(new DropCube());

		auton.setOptionSelected(true);

		return auton;
	}

	public static CommandGroup createSequence(Command... commands) {
		CommandGroup group = new CommandGroup();
		for (Command c : commands) {
			group.addSequential(c);
		}
		return group;
	}
}

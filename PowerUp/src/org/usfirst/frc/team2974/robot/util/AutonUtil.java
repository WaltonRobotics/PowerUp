package org.usfirst.frc.team2974.robot.util;

import org.usfirst.frc.team2974.robot.command.auton.AutonOption;
import org.usfirst.frc.team2974.robot.command.auton.DropCube;
import org.usfirst.frc.team2974.robot.command.auton.ElevatorTarget;
import org.usfirst.frc.team2974.robot.command.auton.SimpleSpline;
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
		auton.addParallel(new ElevatorTarget(elevatorHeight)); // TODO add this back
		auton.addSequential(SimpleSpline.pathFromPosesWithAngle(isBackwards, splinePoints));
//		auton.addSequential(new WaitCommand(1));
		auton.addSequential(new DropCube()); // TODO add this back in

		auton.setOptionSelected(true);

		return auton;
	}
}

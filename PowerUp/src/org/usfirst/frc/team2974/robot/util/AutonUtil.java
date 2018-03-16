package org.usfirst.frc.team2974.robot.util;

import edu.wpi.first.wpilibj.command.WaitCommand;
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
		auton.addParallel(new ElevatorTarget(elevatorHeight));
		auton.addSequential(SimpleSpline.pathFromPosesWithAngle(isBackwards, splinePoints));
		auton.addSequential(new WaitCommand(1));
		auton.addSequential(new DropCube());

		auton.setOptionSelected(true);

		return auton;
	}
}

package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Elevator.HIGH_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.LOW_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.L10;
import static org.usfirst.frc.team2974.robot.Config.Path.L2;
import static org.usfirst.frc.team2974.robot.Config.Path.L3;
import static org.usfirst.frc.team2974.robot.Config.Path.L5;
import static org.usfirst.frc.team2974.robot.Config.Path.L6;
import static org.usfirst.frc.team2974.robot.Config.Path.L7;
import static org.usfirst.frc.team2974.robot.Config.Path.L8;
import static org.usfirst.frc.team2974.robot.Config.Path.L9;
import static org.usfirst.frc.team2974.robot.Config.Path.R10;
import static org.usfirst.frc.team2974.robot.Config.Path.R2;
import static org.usfirst.frc.team2974.robot.Config.Path.R3;
import static org.usfirst.frc.team2974.robot.Config.Path.R5;
import static org.usfirst.frc.team2974.robot.Config.Path.R6;
import static org.usfirst.frc.team2974.robot.Config.Path.R7;
import static org.usfirst.frc.team2974.robot.Config.Path.R8;
import static org.usfirst.frc.team2974.robot.Config.Path.R9;

import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Drives to the switch, puts a cube in, gets another cube, drives to the scale, puts a cube in
 */
public class DriveSwitchScale extends AutonOption {

	public DriveSwitchScale left() {
		addSequential(new DriveToSwitch().left()); // should also drop cube in
		addSequential(SimpleSpline.pathFromPosesWithAngle(true, L5, L7, L8));
		addParallel(new ElevatorTarget(LOW_HEIGHT));
		addSequential(SimpleSpline.pathFromPosesWithAngle(false, L8, L9));
		addSequential(new FindCube());
		// TODO: gather cube too
		addSequential(SimpleSpline.pathFromPosesWithAngle(true, L9, L10));
		addParallel(new ElevatorTarget(HIGH_HEIGHT));
		addSequential(SimpleSpline.pathFromPosesWithAngle(false, L10, L6, L2, L3));
		addSequential(new WaitCommand(0.5));
		addSequential(new DropCube());

		setOptionSelected(true);

		return this;
	}

	@Override
	public AutonOption center() {
		return this;
	}

	public DriveSwitchScale right() {
		addSequential(new DriveToSwitch().right()); // should also drop cube in
		addSequential(SimpleSpline.pathFromPosesWithAngle(true, R5, R7, R8));
		addParallel(new ElevatorTarget(LOW_HEIGHT));
		addSequential(SimpleSpline.pathFromPosesWithAngle(false, R8, R9));
		addSequential(new FindCube());
		addSequential(SimpleSpline.pathFromPosesWithAngle(true, R9, R10));
		addParallel(new ElevatorTarget(HIGH_HEIGHT));
		addSequential(SimpleSpline.pathFromPosesWithAngle(false, R10, R6, R2, R3));
		addSequential(new WaitCommand(0.5));
		addSequential(new DropCube());
		setOptionSelected(true);

		return this;
	}
}

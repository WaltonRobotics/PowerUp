package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Robot;

/**
 *
 */
public class ElevatorTarget extends Command {

	private static int elevatorCommand = 0;
	private final double position;
	private int index;

	public ElevatorTarget(double position) {
		this.position = Math
			.min(Math.max(position,
				Robot.getChoosenRobot().getMinimumElevatorHeight() / Robot.getChoosenRobot()
					.getInchesToNativeUnits()),
				Robot.getChoosenRobot().getMaximumElevatorHeight() / Robot.getChoosenRobot()
					.getInchesToNativeUnits());

		if (!elevator.isMotionControlled()) {
			elevator.enableControl();
		}

		index = elevatorCommand++;
		requires(elevator);
	}

	@Override
	protected void initialize() {
		System.out.println("Initializing elevator" + index);
		elevator.setTarget(position);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(elevator.getCurrentPosition() - position) <= 1;
	}

	@Override
	protected void end() {
//        elevator.disableControl();
	}

	@Override
	protected void interrupted() {
		end();
	}
}

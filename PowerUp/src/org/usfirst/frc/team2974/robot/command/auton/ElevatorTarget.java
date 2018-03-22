package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Robot.elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Config.Elevator;
import org.usfirst.frc.team2974.robot.Robot;

/**
 *
 */
public class ElevatorTarget extends Command {

	private final double position;

	public ElevatorTarget(double position) {
		this.position = Math
			.min(Math.max(position,
				Robot.getChoosenRobot().getMinimumElevatorHeight() / Elevator.INCHES_TO_NU),
				Robot.getChoosenRobot().getMaximumElevatorHeight() / Elevator.INCHES_TO_NU);

		if (!elevator.isMotionControlled()) {
			elevator.enableControl();
		}

		requires(elevator);
	}

	@Override
	protected void initialize() {
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

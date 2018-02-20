package org.usfirst.frc.team2974.robot.command.teleop;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.Config.Elevator.NUDGE_DISTANCE;
import static org.usfirst.frc.team2974.robot.OI.*;
import static org.usfirst.frc.team2974.robot.Robot.elevator;

public class ElevatorCommand extends Command {

	public ElevatorCommand() {
		requires(elevator);
	}

	@Override
	protected void initialize() {
		elevator.disableControl();
	}

	@Override
	protected void execute() {
		if(!elevator.isMotionControlled()) {
			elevator.setPower(-gamepad.getLeftY());
		} else {
			if(elevatorUp.get() && !elevator.atTopPosition()) {
				elevator.nudge(NUDGE_DISTANCE);
			}

			if (elevatorDown.get() && !elevator.atLowerPosition()) {
				elevator.nudge(-NUDGE_DISTANCE);
			}
		}

		if(elevatorZero.get()) {
			elevator.zeroEncoder();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}

package org.usfirst.frc.team2974.robot.command.teleop;

import static org.usfirst.frc.team2974.robot.Config.Elevator.HIGH_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.LOW_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.MEDIUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.NUDGE_DISTANCE;
import static org.usfirst.frc.team2974.robot.Config.Elevator.TOLERANCE;
import static org.usfirst.frc.team2974.robot.OI.elevatorHigh;
import static org.usfirst.frc.team2974.robot.OI.elevatorLow;
import static org.usfirst.frc.team2974.robot.OI.elevatorMedium;
import static org.usfirst.frc.team2974.robot.OI.elevatorNudgeDown;
import static org.usfirst.frc.team2974.robot.OI.elevatorNudgeUp;
import static org.usfirst.frc.team2974.robot.OI.elevatorToggleControl;
import static org.usfirst.frc.team2974.robot.OI.elevatorZero;
import static org.usfirst.frc.team2974.robot.OI.gamepad;
import static org.usfirst.frc.team2974.robot.Robot.elevator;

import edu.wpi.first.wpilibj.command.Command;

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
//		System.out.println(elevator.isMotionControlled());
		if (elevator.isMotionControlled()) {
//			System.out.println("Getting button inputs");

			if (elevatorNudgeUp.get() && !elevator.atTopPosition()) {
				elevator.nudge(NUDGE_DISTANCE);
			}

			if (elevatorNudgeDown.get() && !elevator.atLowerPosition()) {
				elevator.nudge(-NUDGE_DISTANCE);
			}

			if (elevatorHigh.get()) {
				elevator.setTarget(HIGH_HEIGHT);
			} else if (elevatorMedium.get()) {
				elevator.setTarget(MEDIUM_HEIGHT);
			} else if (elevatorLow.get()) {
				elevator.setTarget(LOW_HEIGHT);
			}
		} else {
//			System.out.println("Joystick input receiving");
//			System.out.println("Abs get Y " + Math.abs(gamepad.getLeftY()));
			if (Math.abs(gamepad.getLeftY()) /*TODO look at why getLeftY return 0*/ > TOLERANCE) {
				elevator.setPower(-gamepad.getLeftY());
			} else if (Math.abs(gamepad.getRightY()) > TOLERANCE) {
				elevator.setPower(-gamepad.getRightY());
			} else {
				elevator.setPower(0);
			}

		}

		if (elevatorToggleControl.get()) {
			if (elevator.isMotionControlled()) {
				elevator.disableControl();
			} else {
				elevator.enableControl();
			}
		}

		if (elevatorZero.get()) {
			elevator.startZero();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}

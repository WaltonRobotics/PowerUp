package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Robot.intakeOutput;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCube extends Command {

	public IntakeCube() {
	}

	@Override
	protected void initialize() {
		intakeOutput.resetTime();
		intakeOutput.highIntake();
//		System.out.println("Intake cube intialize");
	}

	@Override
	protected void end() {
		intakeOutput.off();
//		System.out.println("intake cube end");
	}

	@Override
	protected void interrupted() {
//		System.out.println("Intake cube interupted");
		end();
	}

	@Override
	protected boolean isFinished() {
		return intakeOutput.timeElapsed(1.0);
	}
}

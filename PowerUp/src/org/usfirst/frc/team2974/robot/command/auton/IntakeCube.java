package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Robot.intakeOutput;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCube extends Command {

	public IntakeCube() {
		requires(intakeOutput);
	}

	@Override
	protected void initialize() {
		intakeOutput.resetTime();
		intakeOutput.highIntake();
	}

	@Override
	protected void end() {
		intakeOutput.off();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return intakeOutput.timeElapsed(3.0);
	}
}

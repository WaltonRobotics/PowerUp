package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Robot.intakeOutput;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DropCube extends Command {

	public DropCube() {
		requires(intakeOutput);
	}

	@Override
	protected void initialize() {
		intakeOutput.resetTime();
		intakeOutput.highOutput();
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
		return intakeOutput.timeElapsed();
	}
}

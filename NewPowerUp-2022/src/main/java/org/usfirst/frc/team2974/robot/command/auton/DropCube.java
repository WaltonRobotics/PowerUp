package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Robot.intakeOutput;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DropCube extends Command {

	private double startTime;

	public DropCube() {
	}

	@Override
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
		intakeOutput.highOutput();
		System.out.println("Dropping initialize");
	}

	@Override
	protected void end() {
		intakeOutput.off();
		System.out.println("Drop cube end");
	}

	@Override
	protected void interrupted() {
		System.out.println("drop cube interupted");
		end();
	}

	@Override
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() - startTime >= .5;
	}
}

package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.Robot.drivetrain;


/**
 * Waits until a certain percentage done the robot is with its current path.
 */
public class WaitUntilPercent extends Command {

	private final double endPercent;
	private double currentPercent;

	public WaitUntilPercent(double percent) {
		this.endPercent = percent;
		currentPercent = 0;
	}

	protected void initialize() {
	}

	protected void execute() {
		try {
			currentPercent = drivetrain.getPathPercentDone();
		} catch (NullPointerException e) {
			System.out.println("Waiting for Path");
			currentPercent = 0;
		}
	}

	protected boolean isFinished() {
		return currentPercent >= endPercent;
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}
}

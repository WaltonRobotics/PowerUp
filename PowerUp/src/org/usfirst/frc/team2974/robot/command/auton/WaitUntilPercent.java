package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.waltonrobotics.controller.Path;


/**
 * Waits until a certain percentage done the robot is with its current path.
 */
public class WaitUntilPercent extends Command {

	private final Path path;
	private final double endPercent;
	private double currentPercent;

	// TODO MARIUS April 3 2018 be able to pass in the motion you want to be checking as parameter
	public WaitUntilPercent(Path path, double percent) {
		this.path = path;
		this.endPercent = percent;
		currentPercent = 0;
	}

	protected void initialize() {
	}

	protected void execute() {
		try {
			currentPercent = drivetrain.getPercentPathDone(path);
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

package org.usfirst.frc.team2974.robot.command.auton;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Drivetrain;
import org.waltonrobotics.controller.Point;
import org.waltonrobotics.motion.Spline;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.Config.Hardware.ROBOT_WIDTH;

/**
 *
 */
public class SimpleSpline extends Command {
	private double startAngle;
	private double endAngle;
	private Point[] knots;
	private Drivetrain drivetrain = Robot.drivetrain;

	public SimpleSpline(double startAngle, double endAngle, Point... knots) {
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.knots = knots;
	}

	protected void initialize() {
		drivetrain.reset();
		drivetrain.addControllerMotions(new Spline(1, 1, ROBOT_WIDTH, startAngle, endAngle, false, knots));

		Robot.drivetrain.startControllerMotion();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return drivetrain.isControllerFinished();
	}

	// Called once after isFinished returns true
	protected void end() {
		drivetrain.cancelControllerMotion();
		drivetrain.clearControllerMotions();
		drivetrain.setSpeeds(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
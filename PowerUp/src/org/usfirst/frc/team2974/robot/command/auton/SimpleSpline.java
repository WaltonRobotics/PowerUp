package org.usfirst.frc.team2974.robot.command.auton;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Drivetrain;
import org.waltonrobotics.controller.Pose;
import org.waltonrobotics.motion.Spline;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.Config.Path.ACCELERATION_MAX;
import static org.usfirst.frc.team2974.robot.Config.Path.VELOCITY_MAX;
import static org.usfirst.frc.team2974.robot.Robot.drivetrain;
import static org.usfirst.frc.team2974.robot.Config.Hardware.ROBOT_WIDTH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class SimpleSpline extends Command {
	private double startAngle;
	private double endAngle;
	private List<Pose> knots = new ArrayList<>();

	public SimpleSpline(double startAngle, double endAngle, Pose... knots) {
		Collections.addAll(this.knots, knots);
		this.startAngle = startAngle;
		this.endAngle = endAngle;

		requires(drivetrain);
	}

	protected void initialize() {
		drivetrain.reset();
		drivetrain.addControllerMotions(
				new Spline(VELOCITY_MAX, ACCELERATION_MAX, ROBOT_WIDTH, startAngle, endAngle, isBackwards, knots));

		drivetrain.startControllerMotion();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("executing spline");
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

	/**
	 * TODO: implement Creates a SimpleSpline where the first angle is the angle of
	 * the first point and the final angle is the angle of the last point.
	 * 
	 * @param isBackwards
	 *            will the robot move forwards or backwards
	 * @param knots
	 *            the points (with angle) to move through
	 * @return a new SimpleSpline command
	 */
	public static SimpleSpline pathFromPosesWithAngle(boolean isBackwards, Pose... knots) {
		return new SimpleSpline(knots[0].getAngle(), knots[knots.length - 1].getAngle(), isBackwards, knots);
	}
}

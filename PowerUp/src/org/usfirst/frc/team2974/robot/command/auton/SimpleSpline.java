package org.usfirst.frc.team2974.robot.command.auton;

import org.waltonrobotics.controller.Pose;
import org.waltonrobotics.motion.Spline;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.Config.Path.ACCELERATION_MAX;
import static org.usfirst.frc.team2974.robot.Config.Path.VELOCITY_MAX;
import static org.usfirst.frc.team2974.robot.Robot.drivetrain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class SimpleSpline extends Command {
	private double startAngle;
	private double endAngle;
	private boolean isBackwards;
	private List<Pose> knots;
	private double vMax;
	private double aMax;

	public SimpleSpline(double maxVelocity, double maxAcceleration, double startAngle, double endAngle, Pose... knots) {
		this(maxVelocity,maxAcceleration, startAngle, endAngle, false, knots);
	}

	public SimpleSpline(double maxVelocity, double maxAcceleration, double startAngle, double endAngle, boolean isBackwards, Pose... knots) {
		this.knots = new ArrayList<>();
		Collections.addAll(this.knots, knots);
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.isBackwards = isBackwards;
		vMax = maxVelocity;
		aMax = maxAcceleration;

		requires(drivetrain);
	}

	protected void initialize() {
		drivetrain.reset();
		drivetrain.addControllerMotions(
				new Spline(vMax, aMax, 0,0, startAngle, endAngle, isBackwards, knots));

//		drivetrain.startControllerMotion();
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
		System.out.println("spline done");
//		drivetrain.cancelControllerMotion();
//		drivetrain.clearControllerMotions();
		drivetrain.setSpeeds(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	public static SimpleSpline pathFromPosesWithAngle(boolean isBackwards, Pose... knots) {
		return pathFromPosesWithAngle(VELOCITY_MAX, ACCELERATION_MAX, isBackwards, knots);
	}

	/**
	 * Creates a SimpleSpline where the first angle is the angle of
	 * the first point and the final angle is the angle of the last point.
	 *
	 * @param isBackwards
	 *            will the robot move forwards or backwards
	 * @param knots
	 *            the points (with angle) to move through
	 * @return a new SimpleSpline command
	 */
	public static SimpleSpline pathFromPosesWithAngle(double maxVelocity, double maxAcceleration, boolean isBackwards, Pose... knots) {
		return new SimpleSpline(maxVelocity, maxAcceleration, knots[0].getAngle(), knots[knots.length - 1].getAngle(), isBackwards, knots);
	}
}

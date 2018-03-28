package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Path.ACCELERATION_MAX;
import static org.usfirst.frc.team2974.robot.Config.Path.VELOCITY_MAX;
import static org.usfirst.frc.team2974.robot.Robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import java.util.Arrays;
import java.util.List;
import org.waltonrobotics.controller.Pose;
import org.waltonrobotics.motion.Spline;

/**
 *
 */
public class SimpleSpline extends Command {

	private final double startAngle;
	private final double endAngle;
	private final boolean isBackwards;
	private final double startScale;
	private final double endScale;
	private final List<Pose> knots;
	private final double vMax;
	private final double aMax;

	public SimpleSpline(double maxVelocity, double maxAcceleration, double startAngle,
		double endAngle, Pose... knots) {
		this(maxVelocity, maxAcceleration, startAngle, endAngle, false, knots);
	}

	public SimpleSpline(double maxVelocity, double maxAcceleration, double startAngle,
		double endAngle, boolean isBackwards, double startScale, double endScale, Pose... knots) {
		this.startScale = startScale;
		this.endScale = endScale;
		this.knots = Arrays.asList(knots);
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.isBackwards = isBackwards;
		vMax = maxVelocity;
		aMax = maxAcceleration;

		requires(drivetrain);
	}

	public SimpleSpline(double maxVelocity, double maxAcceleration, double startAngle,
		double endAngle, boolean isBackwards, Pose... knots) {
		this(maxVelocity, maxAcceleration, startAngle, endAngle, isBackwards, 1,
			1, knots);
	}

	public static SimpleSpline pointTurn(Pose startPosition, double endAngle) {
		return pointTurn(VELOCITY_MAX / 4, ACCELERATION_MAX, startPosition, endAngle);
	}

	public static SimpleSpline pointTurn(double maxVelocity, double maxAcceleration,
		Pose startPosition, double endAngle) {
		return new SimpleSpline(maxVelocity, maxAcceleration, startPosition.getAngle(), endAngle,
			false, 0, 0, startPosition,
			new Pose(startPosition.getX(), startPosition.getY(), endAngle));
	}

	public static SimpleSpline pathFromPosesWithAngle(boolean isBackwards, Pose... knots) {
		return pathFromPosesWithAngle(VELOCITY_MAX, ACCELERATION_MAX, isBackwards, knots);
	}

	/**
	 * Creates a SimpleSpline where the first angle is the angle of the first point and the final
	 * angle is the angle of the last point.
	 *
	 * @param isBackwards will the robot move forwards or backwards
	 * @param knots the points (with angle) to move through
	 * @return a new SimpleSpline command
	 */
	public static SimpleSpline pathFromPosesWithAngle(double maxVelocity, double maxAcceleration,
		boolean isBackwards, Pose... knots) {
		return new SimpleSpline(maxVelocity, maxAcceleration, knots[0].getAngle(),
			knots[knots.length - 1].getAngle(), isBackwards, knots);
	}

	public static SimpleSpline pathFromPosesWithAngleAndScale(double maxVelocity,
		double maxAcceleration,
		boolean isBackwards, double startScale, double endScale, Pose... knots) {
		return new SimpleSpline(maxVelocity, maxAcceleration, knots[0].getAngle(),
			knots[knots.length - 1].getAngle(), isBackwards, startScale, endScale, knots);
	}

	public static SimpleSpline pathFromPosesWithAngleAndScale(
		boolean isBackwards, double startScale, double endScale, Pose... knots) {
		return pathFromPosesWithAngleAndScale(VELOCITY_MAX, ACCELERATION_MAX, isBackwards,
			startScale, endScale, knots);
	}

	protected void initialize() {
//		drivetrain.reset();
		drivetrain.addControllerMotions(
			new Spline(vMax, aMax, 0, 0, startAngle, endAngle, isBackwards, startScale, endScale,
				knots));

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
}

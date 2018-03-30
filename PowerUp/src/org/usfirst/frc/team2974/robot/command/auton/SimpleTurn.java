package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Path.ACCELERATION_MAX;
import static org.usfirst.frc.team2974.robot.Config.Path.VELOCITY_MAX;
import static org.usfirst.frc.team2974.robot.Robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.waltonrobotics.controller.Pose;
import org.waltonrobotics.motion.PointTurn;

public class SimpleTurn extends Command {

	private final double maxVelocity;
	private final double maxAcceleration;
	private final Pose startPosition;
	private final double endAngle;

	public SimpleTurn(double maxVelocity, double maxAcceleration, Pose startPosition,
		double endAngle) {
		this.maxVelocity = maxVelocity;
		this.maxAcceleration = maxAcceleration;
		this.startPosition = startPosition;

		this.endAngle = endAngle;
	}

	public static SimpleTurn pointTurn(Pose startPosition, double endAngle) {
		return pointTurn(VELOCITY_MAX / 4, ACCELERATION_MAX, startPosition, endAngle);
	}

	public static SimpleTurn pointTurn(double maxVelocity, double maxAcceleration,
		Pose startPosition, double endAngle) {
		return new SimpleTurn(maxVelocity, maxAcceleration, startPosition, endAngle);
	}

	public static SimpleTurn pointTurn(Pose startPosition, Pose endPosition) {
		return pointTurn(VELOCITY_MAX / 4, ACCELERATION_MAX, startPosition, endPosition.getAngle());
	}


	protected void initialize() {
		drivetrain.addControllerMotions(
			new PointTurn(maxVelocity, maxAcceleration, startPosition, endAngle));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return drivetrain.isControllerFinished();
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("turn done");
		drivetrain.setSpeeds(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

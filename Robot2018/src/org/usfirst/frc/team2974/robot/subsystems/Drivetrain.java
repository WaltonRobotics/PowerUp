package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.Drive;
import org.waltonrobotics.AbstractDrivetrain;
import org.waltonrobotics.controller.RobotPair;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Drivetrain extends AbstractDrivetrain {

	private Talon right = RobotMap.right;
	private Talon left = RobotMap.left;
	private Encoder encoderLeft = RobotMap.encoderLeft;
	private Encoder encoderRight = RobotMap.encoderRight;
	
	public Drivetrain() {
	}

	@Override
	public RobotPair getWheelPositions() {
		return new RobotPair(encoderLeft.getDistance(), encoderRight.getDistance());
	}

	@Override
	public void reset() {
		encoderLeft.reset();
		encoderRight.reset();
	}

	@Override
	public void setSpeeds(double leftPower, double rightPower) {
		right.set(rightPower);
		left.set(-leftPower);
	}
	
	@Override
	public void setEncoderDistancePerPulse() {
		encoderLeft.setDistancePerPulse(0.0005652);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
		
	}
}

package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.RobotMap;
import org.usfirst.frc.team2974.robot.commands.Drive;
import org.waltonrobotics.AbstractDrivetrain;
import org.waltonrobotics.controller.RobotPair;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Drivetrain extends AbstractDrivetrain {

	private Talon right = RobotMap.right;
	private Talon left = RobotMap.left;
	private Encoder encoderLeft = RobotMap.encoderLeft;
	private Encoder encoderRight = RobotMap.encoderRight;
	Solenoid shifter = RobotMap.pneumaticsShifter;
	
	public Drivetrain() {
	}

	@Override
	public RobotPair getWheelPositions() {
		return new RobotPair(encoderLeft.getDistance(), -encoderRight.getDistance());
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
		
	}

	@Override
	public void reset() {
		System.out.println("Reset");
		encoderLeft.reset();
		encoderRight.reset();
	}
	
	@Override
	public void setEncoderDistancePerPulse() {
		encoderLeft.setDistancePerPulse(0.0005652);
		encoderRight.setDistancePerPulse(0.0005652);
	}

	@Override
	public void setSpeeds(double leftPower, double rightPower) {
		right.set(-rightPower);
		left.set(leftPower);
	}
	
	public void shiftDown() {
		if (!shifter.get()) {
			shifter.set(true);
		}
	}

	public void shiftUp() {
		if (shifter.get()) {
			shifter.set(false);
		}
	}

	@Override
	public double getKV() {
		return 0.5;
	}

	@Override
	public double getKA() {
		return 0.1;
	}

	@Override
	public double getKP() {
		return 20;
	}

	@Override
	public double getKK() {
		return 0;
	}
}

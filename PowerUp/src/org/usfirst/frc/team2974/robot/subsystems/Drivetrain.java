package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.command.teleop.DriveCommand;
import org.waltonrobotics.AbstractDrivetrain;
import org.waltonrobotics.controller.RobotPair;


import static org.usfirst.frc.team2974.robot.Config.Hardware.DISTANCE_PER_PULSE;
import static org.usfirst.frc.team2974.robot.RobotMap.*;

/**
 *
 */
public class Drivetrain extends AbstractDrivetrain {
	
	public Drivetrain() {
	}

	@Override
	public RobotPair getWheelPositions() {
		return new RobotPair(encoderLeft.getDistance(), encoderRight.getDistance());
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveCommand());
	}

	@Override
	public void reset() {
		System.out.println("Reset Drivetrain");
		encoderLeft.reset();
		encoderRight.reset();
	}
	
	@Override
	public void setEncoderDistancePerPulse() {
		encoderLeft.setDistancePerPulse(DISTANCE_PER_PULSE);
		encoderRight.setDistancePerPulse(DISTANCE_PER_PULSE);
		
		encoderRight.setReverseDirection(true);
		motorRight.setInverted(true);
	}

	@Override
	public void setSpeeds(double leftPower, double rightPower) {
		motorRight.set(rightPower);
		motorLeft.set(leftPower);
	}
	
	public void shiftDown() {
		if (!pneumaticsShifter.get()) {
			pneumaticsShifter.set(true);
		}
	}

	public void shiftUp() {
		if (pneumaticsShifter.get()) {
			pneumaticsShifter.set(false);
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
		return 2;
	}

	@Override
	public double getKK() {
		return 0;
	}
}

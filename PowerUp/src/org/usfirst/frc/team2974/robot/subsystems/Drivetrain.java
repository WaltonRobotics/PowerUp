package org.usfirst.frc.team2974.robot.subsystems;

import org.usfirst.frc.team2974.robot.command.teleop.DriveCommand;
import org.waltonrobotics.AbstractDrivetrain;
import org.waltonrobotics.MotionLogger;
import org.waltonrobotics.controller.RobotPair;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.usfirst.frc.team2974.robot.RobotMap.*;
import org.usfirst.frc.team2974.robot.Config.MotionConstants;

/**
 *
 */
public class Drivetrain extends AbstractDrivetrain {

	private SendableChooser<Boolean> driveMode;

	public Drivetrain(MotionLogger motionLogger) {
		super(motionLogger);
		driveMode = new SendableChooser<>();
		driveMode.addDefault("Tank", true);
		driveMode.addObject("Cheesy", false);
		SmartDashboard.putData("Drive Mode", driveMode);
	}

	@Override
	public RobotPair getWheelPositions() {
		return new RobotPair(encoderLeft.getDistance(), encoderRight.getDistance(), Timer.getFPGATimestamp());
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
		encoderLeft.setDistancePerPulse(0.0005652);
		encoderRight.setDistancePerPulse(0.0005652);

		encoderRight.setReverseDirection(true);
		motorRight.setInverted(true);
	}

	@Override
	public void setSpeeds(double leftPower, double rightPower) {
		motorRight.set(-rightPower);
		motorLeft.set(-leftPower);
//		System.out.println(-leftPower + " " + -rightPower);
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
		return MotionConstants.KV;
	}

	@Override
	public double getKAcc() {
		return MotionConstants.KAcc;
	}

	@Override
	public double getKK() {
		return MotionConstants.KK;
	}

	@Override
	public double getKS() {
		return MotionConstants.KS;
	}
	
	@Override
	public double getKAng() {
		return MotionConstants.KAng;
	}
	
	@Override
	public double getKL() {
		return MotionConstants.KL;
	}

	public boolean isTankDrive() {
		return driveMode.getSelected();
	}

}

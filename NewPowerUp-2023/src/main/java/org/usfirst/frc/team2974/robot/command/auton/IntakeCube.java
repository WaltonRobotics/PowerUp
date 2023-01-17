//package org.usfirst.frc.team2974.robot.command.auton;
//
//import static org.usfirst.frc.team2974.robot.Robot.intakeOutput;
//
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class IntakeCube extends Command {
//
//	private double startTime;
//
//	public IntakeCube() {
//	}
//
//
//	@Override
//	protected void initialize() {
//		startTime = Timer.getFPGATimestamp();
//		intakeOutput.highIntake();
//		System.out.println("Intake cube intialize");
//	}
//
//	@Override
//	protected void end() {
//		intakeOutput.off();
//		System.out.println("intake cube end");
//	}
//
//	@Override
//	protected void interrupted() {
//		System.out.println("Intake cube interupted");
//		end();
//	}
//
//	@Override
//	protected boolean isFinished() {
//		return Timer.getFPGATimestamp() - startTime > 1.5;
//	}
//}

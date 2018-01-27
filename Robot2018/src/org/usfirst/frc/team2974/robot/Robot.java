package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2974.robot.autoncommands.SimpleSpline;
import org.usfirst.frc.team2974.robot.subsystems.Drivetrain;
import org.waltonrobotics.controller.Point;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();

		drivetrain = new Drivetrain();
		oi = new OI();

		chooser.addDefault("Nothing", null);
		chooser.addObject("Wiggle", new SimpleSpline(0, 0, new Point(0, 0), new Point(.5, .5), new Point(1, 0)));
		chooser.addObject("Straight 1 m", new SimpleSpline(0, 0, new Point(0, 0), new Point(.5, 0), new Point(1, 0)));
		chooser.addObject("Turn Right", new SimpleSpline(0, -90, new Point(0, 0), new Point(1, 1)));
		SmartDashboard.putData("Auto mode", chooser);

		drivetrain.setEncoderDistancePerPulse();
		updateSmartDashboard();
		RobotMap.compressor.stop(); // TODO
	}

	@Override
	public void disabledInit() {
		drivetrain.reset();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void autonomousInit() {
		drivetrain.reset();
		autonomousCommand = chooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		drivetrain.reset();
		// RobotMap.compressor.start();

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	private void updateSmartDashboard() {
		SmartDashboard.putNumber("Left", -RobotMap.encoderLeft.getDistance());
		SmartDashboard.putNumber("Right", RobotMap.encoderRight.getDistance());
		SmartDashboard.putNumber("Left Raw", RobotMap.encoderLeft.get());
		SmartDashboard.putNumber("Right Raw", RobotMap.encoderRight.get());
		SmartDashboard.putData("Auto mode", chooser);
		// System.out.println("Left: " + drivetrain.getWheelPositions().getLeft() + "\t
		// Right: "
		// + drivetrain.getWheelPositions().getRight());
	}
}

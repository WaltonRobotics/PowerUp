/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.teleop.DriveCommand;
import frc.robot.commands.teleop.driveMode.ArcadeDrive;
import frc.robot.commands.teleop.driveMode.CurvatureDrive;
import frc.robot.commands.teleop.driveMode.DriveMode;
import frc.robot.commands.teleop.driveMode.TankDrive;
import frc.robot.subsystems.Drivetrain;

import static frc.robot.Constants.Inputs.JOYSTICKS_HID_KEY;
import static frc.robot.Constants.Inputs.XBOX_CONTROLLER_HID_KEY;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static Drivetrain drivetrain;

    public static SendableChooser<DriveMode> driveModeChooser;
    public static SendableChooser<String> driveInputDeviceChooser;

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();

        driveModeChooser = new SendableChooser<>();
        driveModeChooser.setDefaultOption("Curvature", new CurvatureDrive());
        driveModeChooser.addOption("Tank", new TankDrive());
        SmartDashboard.putData("Drive Mode Selector", driveModeChooser);

        driveInputDeviceChooser = new SendableChooser<>();
        driveInputDeviceChooser.setDefaultOption("Joysticks", "Joysticks");
        driveInputDeviceChooser.setDefaultOption("Gamepad", "Gamepad");
        driveInputDeviceChooser.setDefaultOption("Xbox", "Xbox");
        SmartDashboard.putData("Drive Input Device Chooser", driveInputDeviceChooser);

        CommandScheduler.getInstance().setDefaultCommand(drivetrain, new DriveCommand());
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous runs the autonomous command selected by your class.
     */
    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {

    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}

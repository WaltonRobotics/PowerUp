package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2974.robot.command.auton.GamePosition;
import org.usfirst.frc.team2974.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2974.robot.subsystems.IntakeOutput;
import org.waltonrobotics.MotionLogger;
import org.waltonrobotics.controller.Pose;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static IntakeOutput intakeOutput;

	private CommandGroup autonCommands;
	public static MotionLogger motionLogger;

	private SendableChooser<Boolean> doNothingChooser;
	private SendableChooser<Character> startLocation;

	private static String gameData; // for ease of access

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		motionLogger = new MotionLogger("/home/lvuser/");
		drivetrain = new Drivetrain(motionLogger);
		intakeOutput = new IntakeOutput();

		doNothingChooser = new SendableChooser<>();
		doNothingChooser.addObject("Do Nothing!", true);
		doNothingChooser.addDefault("Please move!", false);
		SmartDashboard.putData("Do Nothing", doNothingChooser);

		startLocation = new SendableChooser<>();
		startLocation.addObject("Left (1)", 'l');
		startLocation.addObject("Right (3)", 'r');
		startLocation.addDefault("Center (2)", 'c');

		drivetrain.setEncoderDistancePerPulse();
		updateSmartDashboard();
	}

	@Override
	public void disabledInit() {
		drivetrain.reset();
		motionLogger.writeMotionDataCSV();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void autonomousInit() {
    	if(doNothingChooser.getSelected()) { // if should do nothing
			System.out.println(">:( Nothing has been chosen. Scrubs.");
			autonCommands = GamePosition.DO_NOTHING.getCommand();
    		return; // skips the rest of the init! WARNING: PUT NEEDED CODE BEFORE THIS!
		}
    	motionLogger.initialize();

		if(gameData == null || gameData.isEmpty())
			gameData = DriverStation.getInstance().getGameSpecificMessage(); // "LRL" or something

		char startPosition = startLocation.getSelected();

		System.out.println("Start Position: " + startPosition + " Game Data: " + gameData);

        autonCommands = GamePosition.getGamePosition(startPosition, gameData).getCommand();

        if(autonCommands != null)
        	autonCommands.start();
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
		if (autonCommands != null)
			autonCommands.cancel();
		drivetrain.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void testInit() {
		// randomize gameData
		gameData =
				(Math.random() > .5 ? "L" : "R") + // switch
				(Math.random() > .5 ? "L" : "R") + // scale
				(Math.random() > .5 ? "L" : "R"); // doesn't matter

		SmartDashboard.putString("Randomized GameData", gameData);

		autonomousInit();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		autonomousPeriodic(); // yes
	}

	/**
	 * Put things in here you want to update for SmartDashboard.
	 */
	private void updateSmartDashboard() {
		SmartDashboard.putNumber("Left", RobotMap.encoderLeft.getDistance());
		SmartDashboard.putNumber("Right", RobotMap.encoderRight.getDistance());

		// Selectors
		SmartDashboard.putData("Start Location", startLocation);
	}

	/**
	 * @return Either 'L' for left position or 'R' for right position.
	 */
	public static char getSwitchPosition() {
		return gameData.charAt(0); // 0 = switch position in string
	}

	/**
	 * @return Either 'L' for left position or 'R' for right position.
	 */
	public static char getScalePosition() {
		return gameData.charAt(1); // 1 = scale position in string
	}
}

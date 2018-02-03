package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2974.robot.command.auton.GamePosition;
import org.usfirst.frc.team2974.robot.command.auton.SimpleSpline;
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

	CommandGroup autonCommands;
	private Command autonomousCommand;
	
	private SendableChooser<Command> chooser;

	private SendableChooser<Boolean> doNothingChooser;
	private SendableChooser<Character> startLocation;
	private SendableChooser<Integer> autonChooserScale;
	private SendableChooser<Integer> autonChooserSwitch;
	private SendableChooser<Integer> autonChooserVault;

	// these are for the game data
	private static final int SWITCH_POSITION = 0;
	private static final int SCALE_POSITION = 1;

	// these are for the auton choosers
	private static final int SHOULD = 2;
	private static final int COULD = 1;
	private static final int WILL_NOT = 0;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();

		chooser = new SendableChooser<>();
		chooser.addDefault("Nothing", null);
		chooser.addObject("Wiggle", new SimpleSpline(0, 0, new Point(0, 0), new Point(.5, .5), new Point(1, 0)));
		chooser.addObject("Straight 2 m", new SimpleSpline(0, 0, new Point(0, 0), new Point(2, 0)));
		chooser.addObject("Turn Right", new SimpleSpline(0, -90, new Point(0, 0), new Point(1, 1)));
		SmartDashboard.putData("Auto mode", chooser);

		doNothingChooser = new SendableChooser<>();
		doNothingChooser.addObject("Do Nothing!", true);
		doNothingChooser.addDefault("Please move!", false);
		SmartDashboard.putData("Do Nothing", doNothingChooser);

		startLocation = new SendableChooser<>();
		startLocation.addObject("Left", 'L');
		startLocation.addObject("Right", 'R');
		startLocation.addDefault("Center", 'C');

		autonChooserScale = setUpAuton();
		autonChooserSwitch = setUpAuton();
		autonChooserVault = setUpAuton();

		drivetrain.setEncoderDistancePerPulse();
		updateSmartDashboard();
		// RobotMap.compressor.stop(); // TODO
	}

	/**
	 * This sets up the sendable choosers for autonomous.
	 * 
	 * @return new chooser for auton modes
	 */
	private SendableChooser<Integer> setUpAuton() {
		SendableChooser<Integer> chooser = new SendableChooser<>();
		chooser.addObject("Should", SHOULD); // will do this no matter what
		chooser.addObject("Could", COULD); // will do if and only if it is in your 'area'.
		chooser.addDefault("Will Not", WILL_NOT); // will never do

		return chooser;
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
    	if(doNothingChooser.getSelected()) { // if should do nothing
    		autonCommands = GamePosition.DO_NOTHING.getCommand();
    		return; // skips the rest of the init! WARNING: PUT NEEDED CODE BEFORE THIS!
		}

		// last character doesn't matter
		String gameData = DriverStation.getInstance().getGameSpecificMessage(); // "LRL" or something

		char startPosition = startLocation.getSelected();
		int scaleChosen = autonChooserScale.getSelected();
		int switchChosen = autonChooserSwitch.getSelected();
		int vaultChosen = autonChooserVault.getSelected();

		// TODO: FIXME: AT THIS MOMENT THE VAULT IS NOT TAKEN INTO CONSIDERATION

		String gamePosition = "";
		gamePosition += Character.toLowerCase(startPosition);
		gamePosition += makeGamePosition(startPosition, switchChosen, onSide(gameData, SWITCH_POSITION, startPosition));
		gamePosition += makeGamePosition(startPosition, scaleChosen, onSide(gameData, SCALE_POSITION, startPosition));
		gamePosition += 'N'; // N for not used :)
		
        autonCommands = GamePosition.getGamePosition(gamePosition).getCommand();
//        autonCommands.start();
		// this is for testing
		drivetrain.reset();
		autonomousCommand = chooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();

	}

	/**
	 * Checks if the robot is on the side of the entity
	 * 
	 * @param gameData
	 *            the game data
	 * @param entityPosition
	 *            the entity position in the game data
	 * @param startPosition
	 *            the robot's starting position
	 * @return true if they are on the same side, false otherwise
	 */
	private boolean onSide(String gameData, int entityPosition, char startPosition) {
		return gameData.charAt(entityPosition) == startPosition;
	}

	/**
	 *
	 * @param startPosition
	 *            the starting position of the robot
	 * @param chosenValue
	 *            the chosen value for either
	 * @param onSide
	 *            if the game entity is on the side with the robot
	 * @return the character if
	 */
	private char makeGamePosition(char startPosition, int chosenValue, boolean onSide) {
		if (chosenValue == SHOULD || (chosenValue == COULD && onSide)) {
			return startPosition;
		}
		return '.';
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

	@Override
	public void testInit() {

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}

	/**
	 * Put things in here you want to update for SmartDashboard.
	 */
	private void updateSmartDashboard() {
		SmartDashboard.putNumber("Left", -RobotMap.encoderLeft.getDistance());
		SmartDashboard.putNumber("Right", RobotMap.encoderRight.getDistance());
		SmartDashboard.putNumber("Left Raw", RobotMap.encoderLeft.get());
		SmartDashboard.putNumber("Right Raw", RobotMap.encoderRight.get());

		// Selectors
		SmartDashboard.putData("Auto mode", chooser); // for testing
		SmartDashboard.putData("Start Location", startLocation);
		SmartDashboard.putData("Auton Scale", autonChooserScale);
		SmartDashboard.putData("Auton Switch", autonChooserSwitch);
		SmartDashboard.putData("Auton Vault", autonChooserVault);
	}
}

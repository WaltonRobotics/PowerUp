package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2974.robot.autoncommands.SimpleSpline;
import org.usfirst.frc.team2974.robot.command.auton.GamePosition;
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

	CommandGroup autonCommands;
	private Command autonomousCommand;
	
	SendableChooser<Command> chooser = new SendableChooser<>();
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
		RobotMap.init();

		drivetrain = new Drivetrain();
		oi = new OI();

		chooser.addDefault("Nothing", null);
		chooser.addObject("Wiggle", new SimpleSpline(0, 0, new Point(0, 0), new Point(.5, .5), new Point(1, 0)));
		chooser.addObject("Straight 1 m", new SimpleSpline(0, 0, new Point(0, 0), new Point(.5, 0), new Point(1, 0)));
		chooser.addObject("Turn Right", new SimpleSpline(0, -90, new Point(0, 0), new Point(1, 1)));
		SmartDashboard.putData("Auto mode", chooser);
		
		startLocation = new SendableChooser<>();
        startLocation.addObject("Left", 'L');
        startLocation.addObject("Right", 'R');
        startLocation.addDefault("Center", 'C');

        autonChooserScale = setUpAuton();
        autonChooserSwitch = setUpAuton();
        autonChooserVault = setUpAuton();

        SmartDashboard.putData("Start Location", startLocation);
        SmartDashboard.putData("Auton Scale", autonChooserScale);
        SmartDashboard.putData("Auton Switch", autonChooserSwitch);
        SmartDashboard.putData("Auton Vault", autonChooserVault);

		drivetrain.setEncoderDistancePerPulse();
		updateSmartDashboard();
		RobotMap.compressor.stop(); // TODO
	}
	
	/**
     * This sets up the sendable choosers for autonomous.
     * @return
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
		// last character doesn't matter
        String gameData = DriverStation.getInstance().getGameSpecificMessage(); // "LRL" or something
        gameData = gameData.substring(0, 2); // now "LR" or something like that

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

        autonCommands = GamePosition.getGamePosition(gamePosition).getCommands();
//        autonCommands.start();
        
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

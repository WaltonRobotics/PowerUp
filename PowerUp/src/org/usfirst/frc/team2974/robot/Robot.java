/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.command.auton.GamePosition;
import org.usfirst.frc.team2974.robot.command.teleop.DriveCommand;
import org.usfirst.frc.team2974.robot.smartdashboard.SmartDashboardManager;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {

    // these are for the game data
    private static final int SWITCH_POSITION = 0;
    private static final int SCALE_POSITION = 1;

    // these are for the auton choosers
    private static final int SHOULD = 2;
    private static final int COULD = 1;
    private static final int WILL_NOT = 0;

    /////////////////////// SUBSYSTEMS ////////////////////////////////////

    //// DriveTrain             ////
    // public static DriveTrain driveTrain = new DriveTrain();
    //// Climber                ////
    // public static Climber climber = new Climber();
    //// Intestine (Intake/Out) ////
    // public static Intestine intestine = new Intestine();
    //// Lift (for cube)        ////
    // public static Lift lift = new Lift();

    private CommandGroup autonCommands;

    /////////////////////// DASHBOARD  ///////////////////////////////////

    private SendableChooser<Character> startLocation;
    private SendableChooser<Integer> autonChooserScale;
    private SendableChooser<Integer> autonChooserSwitch;
    private SendableChooser<Integer> autonChooserVault;

    //

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
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

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     * <p>
     * <p>You can add additional auto modes by adding additional comparisons to
     * the switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
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
        autonCommands.start();
    }

    private boolean onSide(String gameData, int entityPosition, char startPosition) {
        return gameData.charAt(entityPosition) == startPosition;
    }

    /**
     *
     * @param startPosition the starting position of the robot
     * @param chosenValue the chosen value for either
     * @param onSide if the game entity is on the side with the robot
     * @return
     */
    private char makeGamePosition(char startPosition, int chosenValue, boolean onSide) {
        if(chosenValue == SHOULD || (chosenValue == COULD && onSide)) {
            return startPosition;
        }
        return '.';
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
		update();
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
	    update();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
	    update();
    }

    public void update(){
	    SmartDashboardManager.update();
        Scheduler.getInstance().run();
    }
}

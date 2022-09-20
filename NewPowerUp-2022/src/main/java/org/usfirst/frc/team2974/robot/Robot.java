package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.usfirst.frc.team2974.robot.command.teleop.*;
import org.usfirst.frc.team2974.robot.command.teleop.driveMode.CurvatureDrive;
import org.usfirst.frc.team2974.robot.command.teleop.driveMode.DriveMode;
import org.usfirst.frc.team2974.robot.command.teleop.driveMode.TankDrive;
import org.usfirst.frc.team2974.robot.lib.config.RobotConfig;
import org.usfirst.frc.team2974.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2974.robot.subsystems.Elevator;
import org.usfirst.frc.team2974.robot.subsystems.IntakeOutput;
import org.usfirst.frc.team2974.robot.subsystems.PlaneBreaker;
import org.usfirst.frc.team2974.robot.util.ElevatorLogger;

import static org.usfirst.frc.team2974.robot.Config.Hardware.LEFT_MOTOR_CHANNEL;
import static org.usfirst.frc.team2974.robot.Config.Hardware.RIGHT_MOTOR_CHANNEL;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as
 * described in the IterativeRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends TimedRobot {

  private static final int DEFAULT_CAMERA_COMPRESSION_QUALITY = 80; // between 0 and 100, 100 being the max, -1 being left to Shuffleboard

  private Talon motorLeft = new Talon(LEFT_MOTOR_CHANNEL);
  private Talon motorRight = new Talon(RIGHT_MOTOR_CHANNEL);
  private double startTime;

  public static Drivetrain drivetrain;
  public static IntakeOutput intakeOutput;
  public static PlaneBreaker planeBreaker;
  public static Elevator elevator;

  public static ElevatorLogger elevatorLogger;

  private static Config.Robot currentRobot;
  public static RobotConfig robotConfig= new CompPowerUp();

  private static String gameData; // for ease of access
  private static int doNumberOfCubes = 2;
  private static boolean confuseEnemy = false;
  private static boolean doBehindSwitch = true;
  private SequentialCommandGroup autonCommands;
  private SendableChooser<Character> startLocation;
  private SendableChooser<Integer> numberCubes;
  private SendableChooser<Boolean> confuseEnenmy;
  private SendableChooser<Boolean> behindSwitch;
  public static SendableChooser<DriveMode> driveModeChooser;
  public static SendableChooser<String> driveInputDeviceChooser;

  Timer timer = new Timer();

  public static Config.Robot getChoosenRobot() {
    return currentRobot;
  }

  @Override
  public void robotInit() {
    currentRobot = RobotMap.robotIdentifier.get() ? Config.Robot.COMPETITION : Config.Robot.PRACTICE;

    elevatorLogger = new ElevatorLogger("/home/lvuser/");
    drivetrain = new Drivetrain();
    intakeOutput = new IntakeOutput();
    planeBreaker = new PlaneBreaker();
    elevator = new Elevator(elevatorLogger);
    SmartDashboard.putNumber("Steer K", 0.06);
    SmartDashboard.putNumber("Steer B", 0.1);
    SmartDashboard.putBoolean("Slow Speed", false);

    driveModeChooser = new SendableChooser<>();
    driveModeChooser.setDefaultOption("Curvature", new CurvatureDrive());
    driveModeChooser.addOption("Tank", new TankDrive());
    SmartDashboard.putData("Drive Mode Selector", driveModeChooser);

    driveInputDeviceChooser = new SendableChooser<>();
    driveInputDeviceChooser.addOption("Joysticks", "Joysticks");
    driveInputDeviceChooser.setDefaultOption("Gamepad", "Gamepad");
    SmartDashboard.putData("Drive Input Device Chooser", driveInputDeviceChooser);

    SmartDashboard.putData("Drive Mode Selector", driveModeChooser);
    CommandScheduler.getInstance().setDefaultCommand(drivetrain, new DriveCommand());
    CommandScheduler.getInstance().setDefaultCommand(elevator, new ElevatorCommand());
    CommandScheduler.getInstance().setDefaultCommand(intakeOutput, new IntakeCommand());

    updateSmartDashboard();
  }


  @Override
  public void disabledInit() {
    gameData = null;
    drivetrain.reset();
//    drivetrain.getMotionLogger().writeMotionDataCSV(true);
//		elevatorLogger.writeMotionDataCSV();
  }

  @Override
  public void disabledPeriodic() {
    CommandScheduler.getInstance().run();
    updateSmartDashboard();
  }

  @Override
  public void autonomousInit() {
    drivetrain.shiftUp();
    timer.reset();
    timer.start();
  }

  /**
   * This function is called periodically during autonomous
   */
  @Override
  public void autonomousPeriodic() {


    if (timer.get() < 3) {
      motorLeft.set(.5);
      motorRight.set(.5);
    } else {
      motorLeft.set(0);
      motorRight.set(0);
    }

  }

  @Override
  public void teleopInit() {
    if (autonCommands != null) {
      autonCommands.cancel();
    }

    //planeBreaker.moveUp();
//    drivetrain.cancelControllerMotion();
    elevator.disableControl();
    drivetrain.shiftUp(); // start in high gear
    drivetrain.reset();
  }

  /**
   * This function is called periodically during operator control
   */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
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
    SmartDashboard.putNumber("Left", RobotMap.encoderLeft.getDistance());
    SmartDashboard.putNumber("Right", RobotMap.encoderRight.getDistance());

    SmartDashboard.putBoolean("Puppy Dogging", OI.rightJoystick.getTrigger());

    // Selectors
//    SmartDashboard.putData("DriveTeam/Start Location", startLocation);
//    SmartDashboard.putData("DriveTeam/Number Of Cubes", numberCubes);
//    SmartDashboard.putData("DriveTeam/Confuse enemy", confuseEnenmy);
//    SmartDashboard.putData("DriveTeam/Behind Switch", behindSwitch);
//
//    // Elevator
//    SmartDashboard.putNumber("Elevator Position (inches)", elevator.getCurrentPosition());
//    SmartDashboard.putNumber("Elevator Position (NU)", elevator.getCurrentPositionNU());
//    SmartDashboard.putBoolean("Elevator Limit Switch", RobotMap.elevatorLimitLower.get());
//    SmartDashboard.putNumber("Elevator Error", elevator.getError());
//    SmartDashboard.putBoolean("Elevator isZeroing", elevator.isZeroing());
//    SmartDashboard.putBoolean("Elevator isZeroed", elevator.isZeroed());
//    SmartDashboard.putString("Elevator power mode", elevatorMotor.getControlMode().name());
//    SmartDashboard.putBoolean("Elevator elevator mode", elevator.isMotionControlled());
//    SmartDashboard.putString("Gear", pneumaticsShifter.get() ? "Low" : "High");

  }

  /**
   * Used for testing purposes, insert into autonomousInit() when needed
   */
  private void randomizeGameData() {
    // randomize gameData
    gameData = (Math.random() > 0.5 ? "L" : "R") + // switch
        (Math.random() > 0.5 ? "L" : "R") + // scale
        (Math.random() > 0.5 ? "L" : "R"); // doesn't matter

    SmartDashboard.putString("Randomized GameData", gameData);
  }
}

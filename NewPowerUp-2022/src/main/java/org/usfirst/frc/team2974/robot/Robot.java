package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.TimedRobot;
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

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as
 * described in the IterativeRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends TimedRobot {

  private static final int DEFAULT_CAMERA_COMPRESSION_QUALITY = 80; // between 0 and 100, 100 being the max, -1 being left to Shuffleboard

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

  public static Config.Robot getChoosenRobot() {
    return currentRobot;
  }

  public static int getDoNumberOfCubes() {
    return doNumberOfCubes;
  }

  // TODO: 4/2/2018 EVAN lets make a better name for this
  public static boolean confuseEnemy() {
    return confuseEnemy;
  }

  public static boolean doBehindSwitch() {
    return doBehindSwitch;
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

  /**
   * This function is run when the robot is first started up and should be used for any initialization code.
   */
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
//
//    startLocation = new SendableChooser<>();
//    startLocation.addObject("Do Nothing", ' ');
//    startLocation.addObject("Left", 'l');
//    startLocation.addObject("Right", 'r');
//    startLocation.addDefault("Center", 'c');
//
//    numberCubes = new SendableChooser<>();
//    numberCubes.addObject("1 Cube sequence", 1);
//    numberCubes.addDefault("2 Cube sequence", 2);
//
//    confuseEnenmy = new SendableChooser<>();
//    confuseEnenmy.addDefault("Do complete 2 cube", false);
//    confuseEnenmy.addObject("Stop before", true);
//
//    behindSwitch = new SendableChooser<>();
//    behindSwitch.addDefault("Do", true);
//    behindSwitch.addObject("DONT", false);
//
//    //		Drive train
//    SmartDashboard.putNumber("Speed Percentage", 0.50 /*.75*/);
//    SmartDashboard.putData("Zero elevator", new InstantCommand() {
//      @Override
//      protected void initialize() {
//        elevator.zero();
//      }
//    });
//
//    SmartDashboard.putNumber("LOW_POWER", LOW_POWER);

//    initCamera();
//    UsbCamera camera = CameraServer.startAutomaticCapture();
//    camera.setResolution(1280 / 2, 720 / 2);
//    camera.setFPS(5);

//		SmartDashboard.putData("Move forwards", SimpleLine.lineWithDistance(new Pose(0, 0, 0), 6));

    updateSmartDashboard();
  }

//  private void initCamera() {
//    new Thread(() -> {
//      SmartDashboard.putNumber(PARKING_LINE_OFFSET, 100);
//      SmartDashboard.putNumber(PARKING_LINE_FOCUS_X, WIDTH / 2.0);
//      SmartDashboard.putNumber(PARKING_LINE_FOCUS_Y, 80);
//      SmartDashboard.putNumber(PARKING_LINE_PERCENTAGE, .69);
//
//      CameraServer cameraServer = CameraServer.getInstance();
//
//      UsbCamera fishEyeCamera = cameraServer.startAutomaticCapture();
//      fishEyeCamera.setResolution(WIDTH, HEIGHT);
//
//      CvSink cvSink = cameraServer.getVideo();
//      CvSource outputStream = cameraServer.putVideo("Fisheye Camera", WIDTH, HEIGHT);
//      outputStream.setFPS(FPS);
//
//      MjpegServer fisheyeServer = cameraServer.addServer("Fisheye Camera Server");
//      fisheyeServer.setSource(outputStream);
//
//      fisheyeServer.getProperty("compression").set(DEFAULT_CAMERA_COMPRESSION_QUALITY);
//      fisheyeServer.getProperty("default_compression").set(DEFAULT_CAMERA_COMPRESSION_QUALITY);
//
////      if (!fishEyeCamera.isConnected()) {
////        fishEyeCamera.close();
////        fisheyeServer.close();
////        return;
////      }
//
//      Mat source = new Mat();
//
//      while (!Thread.interrupted()) {
//        cvSink.grabFrame(source);
//        ParkingLines.setFocusPoint(
//            SmartDashboard.getNumber(PARKING_LINE_FOCUS_X, WIDTH / 2.0),
//            SmartDashboard.getNumber(PARKING_LINE_FOCUS_Y, HEIGHT / 2.0)
//        );
//        ParkingLines.setPercentage(SmartDashboard.getNumber(PARKING_LINE_PERCENTAGE, 1));
//        ParkingLines.setXOffset(SmartDashboard.getNumber(PARKING_LINE_OFFSET, 0));
//
//        ParkingLines.drawParkingLines(source);
//        outputStream.putFrame(source);
//      }
//    }).start();
//  }


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
//    drivetrain.cancelControllerMotion();
////		drivetrain.startControllerMotion();
//    elevator.startZero();
//    drivetrain.getMotionLogger().initialize();
//    elevatorLogger.initialize();
//    drivetrain.shiftUp();
//    planeBreaker.moveUp();
//
//    confuseEnemy = confuseEnenmy.getSelected();
//    doNumberOfCubes = numberCubes.getSelected();
//    doBehindSwitch = behindSwitch.getSelected();
//
//    while ((gameData == null) || gameData.isEmpty()) {
//      gameData = DriverStation.getGameSpecificMessage(); // "LRL" or something
//    }
//
//    char startPosition = startLocation.getSelected();
//
//    if (startPosition == ' ') { // if should do nothing
//      System.out.println(">:( Nothing has been chosen. Scrubs.");
//      autonCommands = GamePosition.DO_NOTHING.getCommand();
//      return; // skips the rest of the init! WARNING: PUT NEEDED CODE BEFORE THIS!
//    }
//
//    System.out.printf("Start Position: %s", startPosition);
//    System.out.printf("Given GameData: %s", gameData);
//    System.out.printf("Game Position loaded: %s", GamePosition.getGamePosition(startPosition, gameData));
//
//    autonCommands = GamePosition.getGamePosition(startPosition, gameData).getCommand();
//
//    if (autonCommands != null) {
//      autonCommands.start();
//    }
  }

  /**
   * This function is called periodically during autonomous
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
    updateSmartDashboard();
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

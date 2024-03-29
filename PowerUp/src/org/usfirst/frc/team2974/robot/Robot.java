package org.usfirst.frc.team2974.robot;

import static org.usfirst.frc.team2974.robot.Config.IntakeOutput.LOW_POWER;
import static org.usfirst.frc.team2974.robot.RobotMap.elevatorMotor;
import static org.usfirst.frc.team2974.robot.RobotMap.pneumaticsShifter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.command.auton.GamePosition;
import org.usfirst.frc.team2974.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2974.robot.subsystems.Elevator;
import org.usfirst.frc.team2974.robot.subsystems.IntakeOutput;
import org.usfirst.frc.team2974.robot.subsystems.PlaneBreaker;
import org.usfirst.frc.team2974.robot.util.ElevatorLogger;
import org.waltonrobotics.MotionLogger;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as
 * described in the IterativeRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static IntakeOutput intakeOutput;
	public static PlaneBreaker planeBreaker;
	public static Elevator elevator;

	public static ElevatorLogger elevatorLogger;
	public static MotionLogger motionLogger;

	private static Config.Robot currentRobot;

	private static String gameData; // for ease of access
	private static int doNumberOfCubes = 2;
	private static boolean confuseEnemy = false;
	private static boolean doBehindSwitch = true;
	private CommandGroup autonCommands;
	private SendableChooser<Character> startLocation;
	private SendableChooser<Integer> numberCubes;
	private SendableChooser<Boolean> confuseEnenmy;
	private SendableChooser<Boolean> behindSwitch;

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

		motionLogger = new MotionLogger("/home/lvuser/");
		elevatorLogger = new ElevatorLogger("/home/lvuser/");
		drivetrain = new Drivetrain(motionLogger);
		intakeOutput = new IntakeOutput();
		planeBreaker = new PlaneBreaker();
		elevator = new Elevator(elevatorLogger);

		startLocation = new SendableChooser<>();
		startLocation.addObject("Do Nothing", ' ');
		startLocation.addObject("Left", 'l');
		startLocation.addObject("Right", 'r');
		startLocation.addDefault("Center", 'c');

		numberCubes = new SendableChooser<>();
		numberCubes.addObject("1 Cube sequence", 1);
		numberCubes.addDefault("2 Cube sequence", 2);

		confuseEnenmy = new SendableChooser<>();
		confuseEnenmy.addDefault("Do complete 2 cube", false);
		confuseEnenmy.addObject("Stop before", true);

		behindSwitch = new SendableChooser<>();
		behindSwitch.addDefault("Do", true);
		behindSwitch.addObject("DONT", false);

		//		Drive train
		SmartDashboard.putNumber("Speed Percentage", 0.50 /*.75*/);
		SmartDashboard.putData("Zero elevator", new InstantCommand() {
			@Override
			protected void initialize() {
				elevator.zero();
			}
		});


		SmartDashboard.putNumber("LOW_POWER", LOW_POWER);

		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(1280 / 2, 720 / 2);
		camera.setFPS(5);

//		SmartDashboard.putData("Move forwards", SimpleLine.lineWithDistance(new Pose(0, 0, 0), 6));

		updateSmartDashboard();
	}

	@Override
	public void disabledInit() {
		drivetrain.cancelControllerMotion();
		gameData = null;
		drivetrain.reset();
		motionLogger.writeMotionDataCSV();
//		elevatorLogger.writeMotionDataCSV();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void autonomousInit() {
		drivetrain.cancelControllerMotion();
//		drivetrain.startControllerMotion();
		elevator.startZero();
		motionLogger.initialize();
		elevatorLogger.initialize();
		drivetrain.shiftUp();
		planeBreaker.moveUp();

		confuseEnemy = confuseEnenmy.getSelected();
		doNumberOfCubes = numberCubes.getSelected();
		doBehindSwitch = behindSwitch.getSelected();

		while ((gameData == null) || gameData.isEmpty()) {
			gameData = DriverStation.getInstance().getGameSpecificMessage(); // "LRL" or something
		}

		char startPosition = startLocation.getSelected();

		if (startPosition == ' ') { // if should do nothing
			System.out.println(">:( Nothing has been chosen. Scrubs.");
			autonCommands = GamePosition.DO_NOTHING.getCommand();
			return; // skips the rest of the init! WARNING: PUT NEEDED CODE BEFORE THIS!
		}

		System.out.printf("Start Position: %s", startPosition);
		System.out.printf("Given GameData: %s", gameData);
		System.out.printf("Game Position loaded: %s", GamePosition.getGamePosition(startPosition, gameData));

		autonCommands = GamePosition.getGamePosition(startPosition, gameData).getCommand();

		if (autonCommands != null) {
			autonCommands.start();
		}
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
		if (autonCommands != null) {
			autonCommands.cancel();
		}

		drivetrain.cancelControllerMotion();
		elevator.disableControl();
		drivetrain.shiftUp(); // start in high gear
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

		// Selectors
		SmartDashboard.putData("DriveTeam/Start Location", startLocation);
		SmartDashboard.putData("DriveTeam/Number Of Cubes", numberCubes);
		SmartDashboard.putData("DriveTeam/Confuse enemy", confuseEnenmy);
		SmartDashboard.putData("DriveTeam/Behind Switch", behindSwitch);

		// Elevator
		SmartDashboard.putNumber("Elevator Position (inches)", elevator.getCurrentPosition());
		SmartDashboard.putNumber("Elevator Position (NU)", elevator.getCurrentPositionNU());
		SmartDashboard.putBoolean("Elevator Limit Switch", RobotMap.elevatorLimitLower.get());
		SmartDashboard.putNumber("Elevator Error", elevator.getError());
		SmartDashboard.putBoolean("Elevator isZeroing", elevator.isZeroing());
		SmartDashboard.putBoolean("Elevator isZeroed", elevator.isZeroed());
		SmartDashboard.putString("Elevator power mode", elevatorMotor.getControlMode().name());
		SmartDashboard.putBoolean("Elevator elevator mode", elevator.isMotionControlled());
		SmartDashboard.putString("Gear", pneumaticsShifter.get() ? "Low" : "High");

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

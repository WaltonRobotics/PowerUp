package org.usfirst.frc.team2974.robot;

import static org.usfirst.frc.team2974.robot.RobotMap.elevatorMotor;
import static org.usfirst.frc.team2974.robot.RobotMap.pneumaticsShifter;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
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
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the IterativeRobot documentation. If you change the name of this class
 * or the package after creating this project, you must also update the manifest file in the
 * resource directory.
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
	private CommandGroup autonCommands;
	//    private static SendableChooser<Config.Robot> robotChooser = new SendableChooser<>();
	private SendableChooser<Character> startLocation;
	private SendableChooser<Integer> numberCubes;

	public static Config.Robot getChoosenRobot() {
		return currentRobot;
	}

	public static int getDoNumberOfCubes() {
		return doNumberOfCubes;
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
	 * This function is run when the robot is first started up and should be used for any
	 * initialization code.
	 */
	@Override
	public void robotInit() {
		currentRobot =
			RobotMap.robotIdentifier.get() ? Config.Robot.COMPETITION : Config.Robot.PRACTICE;

		motionLogger = new MotionLogger("/home/lvuser/");
		elevatorLogger = new ElevatorLogger("/home/lvuser/");
		drivetrain = new Drivetrain(motionLogger);
		intakeOutput = new IntakeOutput();
		planeBreaker = new PlaneBreaker();
		elevator = new Elevator(elevatorLogger);

//
//		SmartDashboard.putData("Intake", new IntakeCube());
//		SmartDashboard.putData("Drop Cube", new DropCube());
		startLocation = new SendableChooser<>();
		startLocation.addObject("Do Nothing", ' ');
		startLocation.addObject("Left", 'l');
		startLocation.addObject("Right", 'r');
		startLocation.addDefault("Center", 'c');

		numberCubes = new SendableChooser<>();
		numberCubes.addObject("1 Cube sequence", 1);
		numberCubes.addDefault("2 Cube sequence", 2);

//		SmartDashboard.putData(new Command() {
//			@Override
//			protected void initialize() {
//				elevator.startZero();
//			}
//
//			@Override
//			protected boolean isFinished() {
//				return true;
//			}
//		});

//        robotChooser.addObject("Practice", Config.Robot.PRACTICE);
//        robotChooser.addDefault("Competition", Config.Robot.COMPETITION);

//        SmartDashboard.putData("TEST AUTON", SimpleSpline.pathFromPosesWithAngle(false, new Pose(0, 0, 90), new Pose(0, 1, 90), new Pose(1, 2, 0), new Pose(2, 2, 0)));
//		SmartDashboard.putData("6m drive straight",
//			SimpleSpline.pathFromPosesWithAngle(false, new Pose(0, 0, 90), new Pose(0, 6, 90)));

		//		Drive train
		SmartDashboard.putNumber("Speed Percentage", .55);

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
//        drivetrain.shiftDown();
		drivetrain.shiftUp();
		planeBreaker.moveUp();

		doNumberOfCubes = numberCubes.getSelected();

//		if (doNothingChooser.getSelected()) { // if should do nothing
//			System.out.println(">:( Nothing has been chosen. Scrubs.");
//			autonCommands = GamePosition.DO_NOTHING.getCommand();
//			return; // skips the rest of the init! WARNING: PUT NEEDED CODE BEFORE THIS!
//		}

		while ((gameData == null) || gameData.isEmpty()) {
			gameData = DriverStation.getInstance().getGameSpecificMessage(); // "LRL" or something
		}

		char startPosition = startLocation.getSelected();

		if (startPosition == ' ') { // if should do nothing
			System.out.println(">:( Nothing has been chosen. Scrubs.");
			autonCommands = GamePosition.DO_NOTHING.getCommand();
			return; // skips the rest of the init! WARNING: PUT NEEDED CODE BEFORE THIS!
		}

		//TODO remove this when we have the elevator able to reach the scale
//		gameData = gameData.substring(0, 1) + "..";

		System.out.println(startPosition);
		System.out.println(gameData);
		System.out.println(GamePosition.getGamePosition(startPosition, gameData));

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
//        elevator.startZero(); TODO: check with tim

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
		SmartDashboard.putData("Drive Team/Start Location", startLocation);
		SmartDashboard.putData("Drive Team/Number Of Cubes", numberCubes);

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

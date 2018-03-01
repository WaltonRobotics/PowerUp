package org.usfirst.frc.team2974.robot.subsystems;

import static org.usfirst.frc.team2974.robot.Config.Elevator.ACCELERATION;
import static org.usfirst.frc.team2974.robot.Config.Elevator.CRUISE_SPEED;
import static org.usfirst.frc.team2974.robot.Config.Elevator.INCHES_TO_NU;
import static org.usfirst.frc.team2974.robot.Config.Elevator.KD;
import static org.usfirst.frc.team2974.robot.Config.Elevator.KF;
import static org.usfirst.frc.team2974.robot.Config.Elevator.KI;
import static org.usfirst.frc.team2974.robot.Config.Elevator.KP;
import static org.usfirst.frc.team2974.robot.Config.Elevator.MAXIMUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.MINUMUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.TIMEOUT;
import static org.usfirst.frc.team2974.robot.RobotMap.elevatorLimitLower;
import static org.usfirst.frc.team2974.robot.RobotMap.elevatorMotor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.command.teleop.ElevatorCommand;
import org.usfirst.frc.team2974.robot.util.ElevatorLogger;

/**
 * The elevator subsystem, which raises and lowers the intake/outtake <p> TODO: finish me
 */
public class Elevator extends Subsystem {

	private ElevatorLogger logger;
	private boolean isMotionControlled;

	private double power;

	private boolean zeroing;
	private boolean zeroed;
	private Timer timer = new Timer();

	public Elevator(ElevatorLogger logger) {
		zeroing = true;
		initConstants();
		this.logger = logger;
	}

	public double getError() {
		if (isMotionControlled) {
			return elevatorMotor.getClosedLoopError(0);
		} else {
			return 0;
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorCommand());
	}

	@Override
	public void periodic() {
		logger.addMotionData(
			new ElevatorLogger.ElevatorData(Timer.getFPGATimestamp(), getCurrentPosition(),
				getCurrentPositionNU(), power));

		if (zeroing) {
			setPower(-0.2);

			if (!elevatorLimitLower.get() || timer
				.hasPeriodPassed(2.0 /*TODO tune time to be as small as possible*/)) {
				zeroing = false;
				timer.stop();
				enableControl();
				zeroEncoder();
			}
		}
	}

	public void initConstants() {
		elevatorMotor.setNeutralMode(NeutralMode.Brake);
		elevatorMotor
			.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, TIMEOUT);
		elevatorMotor.setSensorPhase(false); // PRACTICE
		elevatorMotor.setInverted(false);

		elevatorMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, TIMEOUT);
		elevatorMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, TIMEOUT);

		elevatorMotor.configNominalOutputForward(0, TIMEOUT);
		elevatorMotor.configNominalOutputReverse(0, TIMEOUT);
		elevatorMotor.configPeakOutputForward(1, TIMEOUT);
		elevatorMotor.configPeakOutputReverse(-1, TIMEOUT);

		elevatorMotor.selectProfileSlot(0, 0);

		elevatorMotor.config_kP(0, KP, TIMEOUT);
		elevatorMotor.config_kI(0, KI, TIMEOUT);
		elevatorMotor.config_kD(0, KD, TIMEOUT);
		elevatorMotor.config_kF(0, KF, TIMEOUT);

		elevatorMotor.configMotionCruiseVelocity(CRUISE_SPEED, TIMEOUT);
		elevatorMotor.configMotionAcceleration(ACCELERATION, TIMEOUT);

		zeroed = false;

		elevatorMotor.configForwardSoftLimitThreshold(MAXIMUM_HEIGHT, 10);
		elevatorMotor.configReverseSoftLimitThreshold(MINUMUM_HEIGHT, 10);

		elevatorMotor.configForwardSoftLimitEnable(true, 10);
		elevatorMotor.configReverseSoftLimitEnable(false, 10);

		/* pass false to FORCE OFF the feature.  Otherwise the enable flags above are honored */
		elevatorMotor.overrideLimitSwitchesEnable(true);


	}

	public void zeroEncoder() {
		elevatorMotor.setSelectedSensorPosition(0, 0, TIMEOUT); // TODO: figure out later

		/* +14 rotations forward when using CTRE Mag encoder */
		elevatorMotor.configForwardSoftLimitThreshold(MAXIMUM_HEIGHT, 10); // TODO: FIX
		/* -15 rotations reverse when using CTRE Mag encoder */
		elevatorMotor.configReverseSoftLimitThreshold(MINUMUM_HEIGHT, 10); // TODO: FIX

		elevatorMotor.configForwardSoftLimitEnable(true, 10);
		elevatorMotor.configReverseSoftLimitEnable(true, 10);

		/* pass false to FORCE OFF the feature.  Otherwise the enable flags above are honored */
		elevatorMotor.overrideLimitSwitchesEnable(true);

		zeroed = true;
	}

	public void nudge(double distance) {
		if (isMotionControlled && zeroed) {
			setTarget(getCurrentPosition() + distance);
		}
	}

	public void enableControl() {
		if (zeroed) {
			isMotionControlled = true;
			elevatorMotor.set(ControlMode.MotionMagic, elevatorMotor.getSelectedSensorPosition(0));
		}
	}

	public void disableControl() {
		isMotionControlled = false;
		elevatorMotor.set(ControlMode.Disabled, 0);
	}

	public boolean isZeroed() {
		return zeroed;
	}

	public boolean isZeroing() {
		return zeroing;
	}

	public boolean isMotionControlled() {
		return isMotionControlled;
	}

	/**
	 * Gets the current position of the elevator in inches.
	 */
	public double getCurrentPosition() {
		return elevatorMotor.getSelectedSensorPosition(0) / INCHES_TO_NU;
	}

	/**
	 * Gets the current position of the elevator in native units
	 */
	public double getCurrentPositionNU() {
		return elevatorMotor.getSelectedSensorPosition(0);
	}

	/**
	 * Sets the target position from the zero point.
	 *
	 * @param inches the position is in inches, duh
	 */
	public void setTarget(double inches) {
//		System.out.println("Target: " + inches);

		if (zeroed) {
			elevatorMotor.set(
				ControlMode.MotionMagic,
				Math.min(MAXIMUM_HEIGHT,
					Math.max(MINUMUM_HEIGHT, inches * INCHES_TO_NU)) /* This is a hard cap */
			);
		}
	}

	public void setPower(double percent) {
		percent = Math.min(.75, Math.max(-.75, percent)); // throttle power in

		power = percent;
		SmartDashboard.putNumber("Elevator Power", percent);

//		System.out.println("Setting Power "  + percent);
		elevatorMotor.set(ControlMode.PercentOutput, percent);
	}

	public boolean atTopPosition() {
		return getCurrentPositionNU() >= MAXIMUM_HEIGHT;
	}

	public boolean atLowerPosition() {
		return getCurrentPositionNU() <= MINUMUM_HEIGHT;
	}

	public void startZero() {
		if (!zeroed) {
			zeroing = true;
			zeroed = false;
			elevatorMotor.configReverseSoftLimitEnable(false, 10);
//            zeroEncoder();
			disableControl();

//			System.out.println("Heloo ");
			timer.start();
		}
	}
}


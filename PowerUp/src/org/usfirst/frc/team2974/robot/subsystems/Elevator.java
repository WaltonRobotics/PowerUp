package org.usfirst.frc.team2974.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2974.robot.command.teleop.ElevatorCommand;

import static org.usfirst.frc.team2974.robot.Config.Elevator.*;
import static org.usfirst.frc.team2974.robot.RobotMap.elevatorMotor;

/**
 * The elevator subsystem, which raises and lowers the intake/outtake
 *
 * TODO: finish me
 */
public class Elevator extends Subsystem {

	private boolean isMotionControlled;

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorCommand());
    }

    public void initConstants() {
        elevatorMotor.setNeutralMode(NeutralMode.Brake);
        elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, TIMEOUT);
        elevatorMotor.setSensorPhase(true); // FIXME: make sensor go up as elevator raises
        elevatorMotor.setInverted(true); // FIXME: make motor go up on positive power

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
    }

    public void zeroEncoder() {
        elevatorMotor.setSelectedSensorPosition(0, 0, TIMEOUT); // TODO: figure out later
    }

    public void nudge(double distance) {
        if(isMotionControlled) {
            setTarget(getCurrentPosition() + distance);
        } else {
            setPower(Math.signum(distance)); // TODO: joystick value
        }
    }

    public void enableControl() {
        elevatorMotor.set(ControlMode.MotionMagic, elevatorMotor.getSelectedSensorPosition(0));
    }

    public void disableControl() {
        elevatorMotor.set(ControlMode.Disabled, 0);
    }

    /**
     * Gets the current position of the elevator in inches.
     * @return
     */
    public double getCurrentPosition() {
        return elevatorMotor.getSelectedSensorPosition(0) / INCHES_TO_NU;
    }

    /**
     * Sets the target position from the zero point.
     * @param inches the position is in inches, duh
     */
    public void setTarget(double inches) {
        elevatorMotor.set(ControlMode.MotionMagic, inches * INCHES_TO_NU);
    }

    public void setPower(double percent) {
        elevatorMotor.set(ControlMode.PercentOutput, percent);
    }
}


package org.usfirst.frc.team2974.robot.subsystems;

import static org.usfirst.frc.team2974.robot.Config.IntakeOutput.HOLD_POWER;
import static org.usfirst.frc.team2974.robot.Config.IntakeOutput.LOW_POWER;
import static org.usfirst.frc.team2974.robot.Config.IntakeOutput.MAX_POWER;
import static org.usfirst.frc.team2974.robot.RobotMap.intakeMotorLeft;
import static org.usfirst.frc.team2974.robot.RobotMap.intakeMotorRight;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.command.teleop.IntakeCommand;

/**
 * The Intake/Outtake for the power cubes.
 */
public class IntakeOutput extends Subsystem {

  private final Timer timer = new Timer();

  public IntakeOutput() {
    intakeMotorRight.setInverted(true);
    timer.start();
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new IntakeCommand());
  }

  /**
   * Sets the motor powers.
   */
  private synchronized void setMotorPowers(double left, double right) {
    intakeMotorLeft.set(ControlMode.PercentOutput, left);
    intakeMotorRight.set(ControlMode.PercentOutput, right);
  }

  public void highIntake() {
    setMotorPowers(MAX_POWER, MAX_POWER);
  }

  public void highOutput() {
    setMotorPowers(-MAX_POWER, -MAX_POWER);
  }

  public void halfOutput() {
    setMotorPowers(-MAX_POWER / 2, -MAX_POWER / 2);
  }

  public void lowIntake() {
    setMotorPowers(SmartDashboard.getNumber("LOW_POWER", LOW_POWER), SmartDashboard.getNumber("LOW_POWER", LOW_POWER));
  }

  public void hold() {
    setMotorPowers(HOLD_POWER, HOLD_POWER);
  }

  public void off() {
    setMotorPowers(0, 0);
  }

  public boolean timeElapsed(double time) {
    return timer.hasPeriodPassed(time);
  }

  public boolean timeElapsed() {
    return timer.hasPeriodPassed(2.0);
  }

  public synchronized void resetTime() {
    timer.reset();
    timer.start();
  }

  public double getLeftMotorCurrent() {
    return intakeMotorLeft.getMotorOutputVoltage();
  }

  public double getRightMotorCurrent() {
    return intakeMotorRight.getMotorOutputVoltage();
  }
}

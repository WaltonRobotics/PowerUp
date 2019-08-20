package org.usfirst.frc.team2974.robot.command.teleop;

import static org.usfirst.frc.team2974.robot.OI.leftJoystick;
import static org.usfirst.frc.team2974.robot.OI.rightJoystick;
import static org.usfirst.frc.team2974.robot.Robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {

  public DriveCommand() {
    requires(drivetrain);
  }

  public double getLeftThrottle() {
    if (Math.abs(leftJoystick.getY()) < 0.3) {
      return 0;
    }
    return leftJoystick.getY();
  }

  public double getRightThrottle() {
    if (Math.abs(rightJoystick.getY()) < 0.3) {
      return 0;
    }
    return rightJoystick.getY();
  }


  private void tankDrive() {
    double leftPower = -getLeftThrottle();
    double rightPower = -getRightThrottle();

    drivetrain.setSpeeds(leftPower, rightPower);
  }
  // Called just before this Command runs the first time

  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run

  @Override
  protected void execute() {
    tankDrive();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
    drivetrain.setSpeeds(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    end();
  }
}

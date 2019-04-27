package org.usfirst.frc.team2974.robot.command.teleop;

import static org.usfirst.frc.team2974.robot.OI.gamepad;
import static org.usfirst.frc.team2974.robot.OI.leftJoystick;
import static org.usfirst.frc.team2974.robot.OI.rightJoystick;
import static org.usfirst.frc.team2974.robot.OI.shiftDown;
import static org.usfirst.frc.team2974.robot.OI.shiftUp;
import static org.usfirst.frc.team2974.robot.Robot.drivetrain;
import static org.usfirst.frc.team2974.robot.Robot.robotConfig;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.OI;
import org.waltonrobotics.controller.MotionController;
import org.waltonrobotics.metadata.CameraData;
import org.waltonrobotics.metadata.ErrorVector;
import org.waltonrobotics.metadata.MotionState;
import org.waltonrobotics.metadata.PathData;
import org.waltonrobotics.metadata.Pose;
import org.waltonrobotics.metadata.RobotPair;

/**
 *
 */
public class DriveCommand extends Command {

  EnhancedBoolean rightTriggerPress = new EnhancedBoolean();
  private CameraDataMotionLogger motionLogger = new CameraDataMotionLogger();
  private boolean hasFound;
  private Pose offset;
  private double cameraFilter = .5;

  public DriveCommand() {
    requires(drivetrain);
  }

  public double getLeftThrottle() {
    if (Math.abs(gamepad.getLeftY()) < 0.3) {
      return 0;
    }
    return gamepad.getLeftY();
  }

  public double getRightThrottle() {
    if (Math.abs(gamepad.getRightY()) < 0.3) {
      return 0;
    }
    return gamepad.getRightY();
  }

  public double getTurn() {
    if (Math.abs(rightJoystick.getX()) < 0.1) {
      return 0;
    }
    return rightJoystick.getX();
  }

  private void cheesyDrive() {
    double throttle = (-getLeftThrottle() + 1) / 2;
    double forward = -getRightThrottle();
    double turn = -getTurn();

    drivetrain.setSpeeds(throttle * (forward - turn), throttle * (forward + turn));
  }

  private void tankDrive() {
    double leftPower = -getLeftThrottle();
    double rightPower = -getRightThrottle();
//

    drivetrain.setSpeeds(leftPower, rightPower);
  }
  // Called just before this Command runs the first time

  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run

  @Override
  protected void execute() {
    if (drivetrain.isTankDrive()) {
      tankDrive();
    } else {
      cheesyDrive();
    }

    if (shiftUp.get()) //|| shiftUpAlt.get())
    {
      drivetrain.shiftUp();
    } else if (shiftDown.get()) // || shiftDownAlt.get())
    {
      drivetrain.shiftDown();
    }
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

package org.usfirst.frc.team2974.robot.command.teleop;

import static org.usfirst.frc.team2974.robot.OI.leftJoystick;
import static org.usfirst.frc.team2974.robot.OI.rightJoystick;
import static org.usfirst.frc.team2974.robot.OI.stop;
import static org.usfirst.frc.team2974.robot.Robot.drivetrain;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2974.robot.Config.Camera;
import org.usfirst.frc.team2974.robot.OI;

/**
 *
 */
public class DriveCommand extends CommandBase {

  private final EnhancedBoolean rightTriggerPress = new EnhancedBoolean();
  private boolean isAligning = false;
  private boolean limelightHasValidTarget;
  private double limelightDriveCommand;
  private double limelightSteerCommand;

  public DriveCommand() {
    addRequirements(drivetrain);
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

    rightTriggerPress.set(OI.rightJoystick.getTrigger());

    if (rightTriggerPress.isRisingEdge()) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline")
          .setDouble(0);
    } else if (rightTriggerPress.isFallingEdge()) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline")
          .setDouble(0);
    }


    double leftPower = -getLeftThrottle();
    double rightPower = -getRightThrottle();

    if (rightTriggerPress.get()) {
      if (limelightHasValidTarget) {
//        System.out.println("Puppy dogging");
        drivetrain.setArcadeSpeeds(limelightDriveCommand, limelightSteerCommand);
        isAligning = true;
      } else {
        isAligning = false;
      }
    } else if (rightTriggerPress.isFallingEdge()) {
      isAligning = false;

    }
    if(!stop.get()) {
      if (!isAligning || !limelightHasValidTarget) {
        drivetrain.setSpeeds(leftPower, rightPower);
      }
    } else {
      drivetrain.setSpeeds(0, 0);
    }
  }
  // Called just before this Command runs the first time

  @Override
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run

  @Override
  public void execute() {
    updateLimelightTracking();

    tankDrive();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
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

  public void updateLimelightTracking() {
    // These numbers must be tuned for your Robot!  Be careful!
    double STEER_K = SmartDashboard.getNumber("Steer K", 0.05); // how hard to turn toward the target
    double STEER_B = SmartDashboard.getNumber("Steer B", 0.1);
    double DRIVE_K = SmartDashboard
        .getNumber("Drive K", 0.26); // how hard to drive fwd toward the target

    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

//    System.out.println(String.format("%s, %s, %s, %s", tx, ty, ta, tv));

    if (tv < 1.0) {
      limelightHasValidTarget = false;
      limelightDriveCommand = 0.0;
      limelightSteerCommand = 0.0;
      return;
    } else {
      limelightHasValidTarget = true;
    }

    // Start with proportional steering
    double distance =
        (0.0003645262 * ty * ty * ty) + (-0.0008723340 * ty * ty) + (0.0425549550 * ty)
            + 0.5546679097;
    SmartDashboard.putNumber("Camera Distance", distance);

    distance = Math.max(0.75, distance);
    distance = Math.min(5, distance);

    double f = 1 - STEER_B * Math.pow(ta - 2, 2);

    double steerCmd = (tx * STEER_K * f) / distance;
    limelightSteerCommand = steerCmd;

    // try to drive forward until the target area reaches our desired area
    double driveCmd = (-getLeftThrottle() - getRightThrottle()) / 2.0;
//    double maxSpeed = 1;
//    double minSpeed = .3;
//    double driveCmd;
//
//    double decelerationDistance = 1.5;
//    double minDistance = .5;
//    double alpha = (distance - minDistance) / (decelerationDistance - minDistance);
//
//    alpha = Math.max(0, Math.min(1, alpha));
//
//    driveCmd = alpha * maxSpeed + (1 - alpha) * minSpeed;
//    driveCmd = Math.min(1, driveCmd);
//
//    SmartDashboard.putNumber("Drive Speed", driveCmd);
//    SmartDashboard.putNumber("Alpha", alpha);


    limelightDriveCommand = driveCmd;
  }

}

package org.usfirst.frc.team2974.robot.command.teleop;

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
//
//    rightTriggerPress.set(OI.rightJoystick.getTrigger());
////		if (!drivetrain.isShiftDown() && Robot.elevator.getCurrentPositionNU()
////			>= Robot.getChoosenRobot().getMaximumElevatorHeight() * (1.0 / 2.0)) {
////			double percentage = SmartDashboard.getNumber("Speed Percentage", .5);
////			leftPower = Math.pow(leftPower, 3) * percentage;
////			rightPower = Math.pow(rightPower, 3) * percentage;
////		}
//
//    // if (Robot.elevator.getCurrentPositionNU()
//    // >= (Robot.getChoosenRobot().getMaximumElevatorHeight() * (0.5))) {
////			double percentage = SmartDashboard.getNumber("Speed Percentage", 0.50);
//    // double percentage =
//    // 1.4 - (Robot.elevator.getCurrentPositionNU() / Robot.getChoosenRobot().getMaximumElevatorHeight());
//    // 	leftPower *= percentage;
//    // 	rightPower *= percentage;
//    // }
//
//    if (rightTriggerPress.get() && !hasFound) {
//      CameraData cameraData = drivetrain.getCurrentCameraData();
////      CameraData cameraData = new CameraData(
////          SmartDashboard.getNumber(CAMERA_DATA_X, 0),
////          SmartDashboard.getNumber(CAMERA_DATA_Y, 0),
////          SmartDashboard.getNumber(CAMERA_DATA_HEIGHT, 0),
////          SmartDashboard.getNumber(CAMERA_DATA_ANGLE, 0),
////          (int) SmartDashboard.getNumber(CAMERA_DATA_NUMBER_OF_TARGETS, 0),
////          SmartDashboard.getNumber(CAMERA_DATA_TIME, 0)
////      );
//
//      if (cameraData.getNumberOfTargets() == 0) {
//        hasFound = false;
//      } else {
//        System.out.println("Found target");
//        drivetrain.setStartingPosition(cameraData.getCameraPose());
//        offset = new Pose();
//
//        hasFound = true;
//      }
//    }
//
//    if (rightTriggerPress.get() && hasFound) {
//      Pose actualPathData = drivetrain.getActualPosition();
//
//      CameraData cameraData = drivetrain.getCurrentCameraData();
//
//      if (cameraData.getTime() != -1.0) {
//        Pose camera = cameraData.getCameraPose();
//
//        Pose difference = new Pose(camera.getX() - actualPathData.getX(), camera.getY() - actualPathData.getY(),
//            camera.getAngle() - actualPathData.getAngle());
//
//        offset = new Pose((offset.getX() * (1.0 - cameraFilter)) + (difference.getX() * cameraFilter),
//            (offset.getY() * (1.0 - cameraFilter)) + (difference.getY() * cameraFilter),
//            (offset.getAngle() * (1 - cameraFilter)) + (difference.getAngle() * cameraFilter));
//      }
//
//      //Get cameradata and get 90 percent of it in
//      double distance = Math
//          .max(Math.abs(actualPathData.getX()) - 0.5, 0.2);
//
//      PathData targetPathData = new PathData(new Pose(actualPathData.getX(), 0));
//
//      Pose correctedPosition = new Pose();
//      if (distance <= 0.5) {
//        correctedPosition = actualPathData
//            .offset(offset.getX(), offset.getY(), offset.getAngle()); //FIXME overload to use with Pose
//      }
//
//      ErrorVector currentError = MotionController.findCurrentError(targetPathData, correctedPosition);
//
//      double centerPower = (leftPower + rightPower) / 2.0;
//      double steerPowerXTE = Math.abs(centerPower) * robotConfig.getKS() * currentError.getXTrack();
//
//      double steerPowerAngle = robotConfig.getKAng() * currentError.getAngle();
//
//      System.out.println(distance);
//      if (distance <= 1.5) {
//        centerPower *= distance;
//      }
//
//      double steerPower = Math.max(-1.0, Math.min(1.0, steerPowerXTE + steerPowerAngle));
//
//      centerPower = Math
//          .max(-1.0 + Math.abs(steerPower),
//              Math.min(1.0 - Math.abs(steerPower), centerPower));
//
//      leftPower = centerPower - steerPower;
//      rightPower = centerPower + steerPower;
//
//      motionLogger.addMotionData(
//          new CameraMotionData(
//              actualPathData,
//              targetPathData.getCenterPose(),
//              currentError,
//              new RobotPair(
//                  leftPower,
//                  rightPower,
//                  Timer.getFPGATimestamp()
//              ),
//              0,
//              MotionState.MOVING,
//              cameraData,
//              offset
//          ));
//
//    }
//
//    if (rightTriggerPress.isFallingEdge() && hasFound) {
//      motionLogger.writeMotionDataCSV(true);
//      hasFound = false;
//    }

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

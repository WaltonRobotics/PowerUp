package org.usfirst.frc.team2974.robot.subsystems;

import static org.usfirst.frc.team2974.robot.RobotMap.encoderLeft;
import static org.usfirst.frc.team2974.robot.RobotMap.encoderRight;
import static org.usfirst.frc.team2974.robot.RobotMap.motorLeft;
import static org.usfirst.frc.team2974.robot.RobotMap.motorRight;
import static org.usfirst.frc.team2974.robot.RobotMap.pneumaticsShifter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.usfirst.frc.team2974.robot.Robot;
// import org.usfirst.frc.team2974.robot.command.teleop.DriveCommand;
import org.usfirst.frc.team2974.robot.lib.metadata.RobotPair;

/**
 *
 */
public class Drivetrain extends SubsystemBase {

  private final SendableChooser<Boolean> driveMode;
  private final double slowSpeed = 0.5;

  public Drivetrain() {
    driveMode = new SendableChooser<>();
    driveMode.setDefaultOption("Tank", true);
    driveMode.addOption("Cheesy", false);
    SmartDashboard.putData("Drive Team/Drive Mode", driveMode);

    motorRight.setInverted(true);

    setEncoderDistancePerPulse();
  }

  public RobotPair getWheelPositions() {
    return new RobotPair(encoderLeft.getDistance(), encoderRight.getDistance(),
        Timer.getFPGATimestamp());
  }

  public void reset() {
    System.out.println("Reset Drivetrain");
    encoderLeft.reset();
    encoderRight.reset();
  }

  public void setEncoderDistancePerPulse() {
    double distancePerPulse = Robot.getChoosenRobot().getDistancePerPulse();

    encoderLeft.setDistancePerPulse(distancePerPulse);
    encoderRight.setDistancePerPulse(distancePerPulse);
    encoderRight.setReverseDirection(true);
    motorRight.setInverted(true);
  }

  public void setSpeeds(double leftPower, double rightPower) {
    if (SmartDashboard.getBoolean("Slow Speed", true)) {
      if(Math.abs(leftPower + rightPower) >= 1.5) {
        leftPower *= (slowSpeed + 0.25);
        rightPower *= (slowSpeed + 0.25);
      } else if (Math.abs(leftPower + rightPower) < 0.75) {
        leftPower /= slowSpeed;
        rightPower /= slowSpeed;
      }

      motorRight.set(-rightPower * slowSpeed);
      motorLeft.set(-leftPower * slowSpeed);
    } else {
      motorRight.set(-rightPower);
      motorLeft.set(-leftPower);
    }

  }

  public void shiftDown() {
    pneumaticsShifter.set(true);
  }

  public void shiftUp() {
    pneumaticsShifter.set(false);
  }

  public boolean isShiftDown() {
    return pneumaticsShifter.get();
  }

  public boolean isTankDrive() {
    return driveMode.getSelected();
  }

  public void setArcadeSpeeds(double xSpeed, double zRotation) {
    xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
    zRotation = Math.copySign(zRotation * zRotation, zRotation);

//    double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);
    double leftMotorOutput;
    double rightMotorOutput;

//    if (xSpeed >= 0.0D) {
//      if (zRotation >= 0.0D) {
//        leftMotorOutput = maxInput;
//        rightMotorOutput = xSpeed - zRotation;
//      } else {
//        leftMotorOutput = xSpeed + zRotation;
//        rightMotorOutput = maxInput;
//      }
//    } else if (zRotation >= 0.0D) {
//      leftMotorOutput = xSpeed + zRotation;
//      rightMotorOutput = maxInput;
//    } else {
//      leftMotorOutput = maxInput;
//      rightMotorOutput = xSpeed - zRotation;
//    }

    xSpeed = Math
        .max(-1.0 + Math.abs(zRotation),
            Math.min(1.0 - Math.abs(zRotation), xSpeed));

    leftMotorOutput = xSpeed + zRotation;
    rightMotorOutput = xSpeed - zRotation;

    setSpeeds(leftMotorOutput, rightMotorOutput);
  }

}

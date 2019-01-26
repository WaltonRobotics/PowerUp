package frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2974.robot.Config.MotionConstants;
import frc.team2974.robot.Config.Path;
import frc.team2974.robot.Robot;
import frc.team2974.robot.command.teleop.DriveCommand;
import org.waltonrobotics.AbstractDrivetrain;
import org.waltonrobotics.MotionLogger;
import org.waltonrobotics.controller.RobotPair;

import static frc.team2974.robot.RobotMap.*;

/**
 *
 */
public class Drivetrain extends AbstractDrivetrain {

  private final SendableChooser<Boolean> driveMode;
  private double distancePerPulse = Robot.getChosenRobot().getDistancePerPulse();

  public Drivetrain(MotionLogger motionLogger) {
    super(motionLogger, ()-> false, ()->null);
    driveMode = new SendableChooser<>();
    driveMode.addDefault("Tank", true);
    driveMode.addObject("Cheesy", false);
    SmartDashboard.putData("Drive Team/Drive Mode", driveMode);

    motorRight.setInverted(true);

    setEncoderDistancePerPulse();
  }

  @Override
  public RobotPair getWheelPositions() {
    return new RobotPair(encoderLeft.getDistance(), encoderRight.getDistance(),
        Timer.getFPGATimestamp());
  }

  @Override
  public double getRobotWidth() {
    return Robot.getChosenRobot().getRobotWidth();
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
  }

  @Override
  public void reset() {
    System.out.println("Reset Drivetrain");
    encoderLeft.reset();
    encoderRight.reset();
  }

  @Override
  public void setEncoderDistancePerPulse() {


    encoderLeft.setDistancePerPulse(distancePerPulse);
    encoderRight.setDistancePerPulse(distancePerPulse);
    encoderRight.setReverseDirection(true);
    motorRight.setInverted(true);
  }

  public double getDistancePerPulse() {
    return distancePerPulse;
  }

  @Override
  public void setSpeeds(double leftPower, double rightPower) {
    motorRight.set(-rightPower);
    motorLeft.set(-leftPower);
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

  @Override
  public double getKV() {
    return MotionConstants.KV;
  }

  @Override
  public double getKAcc() {
    return MotionConstants.KAcc;
  }

  @Override
  public double getKK() {
    return MotionConstants.KK;
  }

  @Override
  public double getKS() {
    return MotionConstants.KS;
  }

  @Override
  public double getKAng() {
    return MotionConstants.KAng;
  }

  @Override
  public double getKL() {
    return MotionConstants.KL;
  }

  @Override
  public double getILag() {
    return MotionConstants.IL;
  }

  @Override
  public double getIAng() {
    return MotionConstants.IAng;
  }

  @Override
  public double getMaxVelocity() {
    return Path.VELOCITY_MAX;
  }

  @Override
  public double getMaxAcceleration() {
    return Path.ACCELERATION_MAX;
  }

  public boolean isTankDrive() {
    return driveMode.getSelected();
  }

}

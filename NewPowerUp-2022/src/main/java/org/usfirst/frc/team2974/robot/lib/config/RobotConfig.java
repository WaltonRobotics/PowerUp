package org.usfirst.frc.team2974.robot.lib.config;

public abstract class RobotConfig {
  private final String robotName;

  public RobotConfig(String robotName) {
    this.robotName = robotName;
  }

  public String getRobotName() {
    return this.robotName;
  }

  public abstract EncoderConfig getRightEncoderConfig();

  public abstract EncoderConfig getLeftEncoderConfig();

  public abstract TalonConfig getLeftTalonConfig();

  public abstract TalonConfig getRightTalonConfig();

  public abstract Controls getRightJoystickConfig();

  public abstract Controls getLeftJoystickConfig();

  public double getMaxVelocity() {
    return (1.0D - this.getKK()) / this.getKV();
  }

  public abstract double getMaxAcceleration();

  public abstract double getKV();

  public abstract double getKAcc();

  public abstract double getKK();

  public abstract double getKS();

  public abstract double getKAng();

  public abstract double getKL();

  public double getILag() {
    return 0.0D;
  }

  public double getIAng() {
    return 0.0D;
  }

  public abstract double getRobotWidth();

  public abstract double getRobotLength();

  public abstract boolean isCurrentRobot();

}

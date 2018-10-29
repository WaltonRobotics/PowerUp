package frc.team2974.robot.subsystems;

import static frc.team2974.robot.RobotMap.planeBreaker;

import edu.wpi.first.wpilibj.command.Subsystem;


public class PlaneBreaker extends Subsystem {

  public PlaneBreaker() {
    initDefaultCommand();
  }

  protected void initDefaultCommand() {

  }

  public void moveUp() {
    planeBreaker.set(true);
  }

  public void moveDown() {
    planeBreaker.set(false);
  }
}

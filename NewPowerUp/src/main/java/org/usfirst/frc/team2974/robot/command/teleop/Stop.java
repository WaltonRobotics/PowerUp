package org.usfirst.frc.team2974.robot.command.teleop;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.OI;
import org.usfirst.frc.team2974.robot.Robot;

/**
 *
 */
public class Stop extends Command {

  public Stop() {
    requires(Robot.drivetrain);
  }

  protected void initialize() {
    System.out.println("Initialize");
    Robot.drivetrain.setSpeeds(0, 0);
    Robot.elevator.disableControl();

  }

  protected void execute() {
    System.out.println("Execute");
    Robot.drivetrain.setSpeeds(0, 0);
  }

  protected boolean isFinished() {
    return !OI.stop.get();
  }

  @Override
  protected void end() {
//    Robot.elevator.zero();
    Robot.elevator.enableControl();
  }
}

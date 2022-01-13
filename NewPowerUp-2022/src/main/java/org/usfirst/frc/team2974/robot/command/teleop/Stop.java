package org.usfirst.frc.team2974.robot.command.teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.usfirst.frc.team2974.robot.OI;
import org.usfirst.frc.team2974.robot.Robot;

/**
 *
 */
public class Stop extends CommandBase {

  public Stop() {
    addRequirements(Robot.drivetrain);
  }

  public void initialize() {
    System.out.println("Initialize");
    Robot.drivetrain.setSpeeds(0, 0);
    Robot.elevator.disableControl();

  }

  public void execute() {
    System.out.println("Execute");
    Robot.drivetrain.setSpeeds(0, 0);
  }

  public boolean isFinished() {
    return !OI.stop.get();
  }

  @Override
  public void end(boolean interrupted) {
    if (!interrupted) {
      Robot.elevator.enableControl();
    }
//    Robot.elevator.zero();
  }
}

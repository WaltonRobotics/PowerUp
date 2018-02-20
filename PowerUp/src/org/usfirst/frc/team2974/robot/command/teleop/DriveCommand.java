package org.usfirst.frc.team2974.robot.command.teleop;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.RobotMap;

import static org.usfirst.frc.team2974.robot.Robot.drivetrain;
import static org.usfirst.frc.team2974.robot.OI.*;

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
        drivetrain.setSpeeds(-getLeftThrottle(), -getRightThrottle());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (drivetrain.isTankDrive()) {
            tankDrive();
        } else {
            cheesyDrive();
        }

        if (shiftUp.get()) //|| shiftUpAlt.get())
            drivetrain.shiftUp();
        else if (shiftDown.get()) // || shiftDownAlt.get())
            drivetrain.shiftDown();
//        else if (RobotMap.pneumaticsShifter.get() && shiftTrigger.get()) {
//            drivetrain.shiftDown();
//        } else if (!RobotMap.pneumaticsShifter.get()) {
//            drivetrain.shiftUp();
//        }
    }

    // Make this return true when this Command no longer needs to run execute()
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

package org.usfirst.frc.team2974.robot.command.teleop;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.setSpeeds(-getLeftThrottle(), -getRightThrottle());
    	if (shiftUp.get() || shiftUpAlt.get())
			drivetrain.shiftUp();
    	if (shiftDown.get() || shiftDownAlt.get())
			drivetrain.shiftDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

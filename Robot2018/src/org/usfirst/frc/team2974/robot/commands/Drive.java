package org.usfirst.frc.team2974.robot.commands;

import org.usfirst.frc.team2974.robot.Robot;
import org.usfirst.frc.team2974.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
	
	private Drivetrain drivetrain = Robot.drivetrain;

    public Drive() {
    	requires(drivetrain);
    }
    
    public double getLeftThrottle() {
		if (Math.abs(Robot.oi.left.getY()) < 0.3) {
			return 0;
		}
		return Robot.oi.left.getY();
	}

	public double getRightThrottle() {
		if (Math.abs(Robot.oi.right.getY()) < 0.3) {
			return 0;
		}
		return Robot.oi.right.getY();

	}

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.setSpeeds(-getLeftThrottle(), -getRightThrottle());
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

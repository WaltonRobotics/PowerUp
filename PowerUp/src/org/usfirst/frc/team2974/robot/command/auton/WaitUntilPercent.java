package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.Robot.drivetrain;


/**
 *
 */
public class WaitUntilPercent extends Command {

    public final double endPercent;
    public double currentPercent;

    public WaitUntilPercent(double percent) {
        this.endPercent = percent;
        currentPercent = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
    		currentPercent = drivetrain.getPathPercentDone();
    	} catch(NullPointerException e) {
    		System.out.println("Waiting for Path");
    		currentPercent = 0;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return currentPercent >= endPercent;
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

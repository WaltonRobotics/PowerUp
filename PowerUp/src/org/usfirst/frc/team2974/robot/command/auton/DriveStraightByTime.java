package org.usfirst.frc.team2974.robot.command.auton;

import org.usfirst.frc.team2974.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2974.robot.Robot;

public class DriveStraightByTime extends Command{
	
	double timeAtStart;
	final double duration;
	double speed;
	
	public DriveStraightByTime(double time) {
		duration = time;
		
	}
	
	protected void initialize() {
		timeAtStart = Timer.getFPGATimestamp();
	}
	
	protected void execute() {
		//set speed to 1
		Robot.drivetrain.setSpeeds(speed, speed);
		System.out.println(Timer.getFPGATimestamp() + "/" + timeAtStart + "/" + duration);
	}
	
	protected boolean isFinished() {
		//checks if the time is greater than the duration set. If so, the program stops.
		if(Timer.getFPGATimestamp() - timeAtStart < duration){
			return false;
		}else{
			return true;
		}
	}

	protected void end() {
		//sets the speed to 0
		Robot.drivetrain.setSpeeds(0,0);
	}
	
	protected void interrupted() {
		end();
	}
}

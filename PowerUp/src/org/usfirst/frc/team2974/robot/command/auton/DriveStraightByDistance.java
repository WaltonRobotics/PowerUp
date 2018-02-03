package org.usfirst.frc.team2974.robot.command.auton;

import org.usfirst.frc.team2974.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2974.robot.Robot;

public class DriveStraightByDistance extends Command{
	
	double timeAtStart;
	double duration;
	double speed = 1;
	final double distance;
	
	public DriveStraightByDistance(double distance) {
		this.distance = distance;
	}
	
	protected void initialize() {
		timeAtStart = Timer.getFPGATimestamp();
	}
	
	protected void execute() {
		//sets the speed to 1
		Robot.drivetrain.setSpeeds(speed, speed);
		//calculates how long the robot has been going based on distance input
		duration = distance / speed;
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

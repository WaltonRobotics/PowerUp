package org.usfirst.frc.team2974.robot.command.auton;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PointTurn extends Command{

	Timer timer = new Timer();
//	double smartDash = SmartDashboard.getNumber("", 0.0);
	double time;
	double timeFinal;
	String side;
	
	public PointTurn(String side, double timeFinal) {
		this.timeFinal = timeFinal;
		this.side = side;
	}
	
	@Override
	protected void initialize() {

		
		// TODO Auto-generated method stub
		super.initialize();
		timer.start();
	}

	@Override
	protected void execute() {
		//sets left wheel to forward, right wheel to backwards
		
		if(side == "left") {
			Robot.drivetrain.setSpeeds(1,-1);
		}
		else {
			Robot.drivetrain.setSpeeds(-1, 1);
		}
		
		time = timer.get();
	}

	@Override
	protected boolean isFinished() {
		//takes input from SmartDashboard in the form of a string and a double, for when to finish
		if (time > timeFinal)  {
		//if the time from the input goes over the current running time, isFinished is true
			return true;
		}
		// TODO Auto-generated method stub
		//if not, isFinished is false
		return false;
	}
	@Override
	protected void end() {
		//sets the speeds of both sides to zero (left speed, right speed);
		Robot.drivetrain.setSpeeds(0,0);
	}
	@Override
	protected void interrupted() {
		end();
	}
	
}

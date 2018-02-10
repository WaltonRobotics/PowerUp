package org.usfirst.frc.team2974.robot.command.auton;

import org.usfirst.frc.team2974.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PointTurn extends Command{

	private Timer timer = new Timer();
	private double time;
	private double timeFinal;
	private String side;

	public PointTurn(String side, double timeFinal) {
		this.timeFinal = timeFinal;
		this.side = side;
	}
	
	@Override
	protected void initialize() {
		timer.start();
	}

	@Override
	protected void execute() {
		//sets left wheel to forward, right wheel to backwards
		
		if(side.equals("left")) {
			Robot.drivetrain.setSpeeds(1,-1);
		} else {
			Robot.drivetrain.setSpeeds(-1, 1);
		}
		
		time = timer.get();
	}

	@Override
	protected boolean isFinished() {
		return time > timeFinal;
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

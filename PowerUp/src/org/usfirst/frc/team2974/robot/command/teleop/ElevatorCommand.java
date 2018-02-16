package org.usfirst.frc.team2974.robot.command.teleop;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.OI.elevatorUp;
import static org.usfirst.frc.team2974.robot.OI.elevatorDown;
import static org.usfirst.frc.team2974.robot.OI.restPosition;
import static org.usfirst.frc.team2974.robot.Robot.elevator;

public class ElevatorCommand extends Command {
	
	protected void initialize() {
		
	}
	
	protected void execute() {

	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void interrupted() {
		isFinished();
	}

}

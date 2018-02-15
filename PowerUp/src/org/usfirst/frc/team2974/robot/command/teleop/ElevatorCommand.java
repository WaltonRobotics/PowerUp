package org.usfirst.frc.team2974.robot.command.teleop;

import static org.usfirst.frc.team2974.robot.OI.elevatorUp;
import static org.usfirst.frc.team2974.robot.OI.elevatorDown;
import static org.usfirst.frc.team2974.robot.OI.restPosition;
import static org.usfirst.frc.team2974.robot.Robot.elevator;
import static org.usfirst.frc.team2974.robot.RobotMap.limitSwitchUp;
import static org.usfirst.frc.team2974.robot.RobotMap.limitSwitchDown;

public class ElevatorCommand {
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		
		if(limitSwitchUp.get() && elevatorUp.get()) {
			elevator.setElevatorMotorSpeeds(0, 0);
		}
		else if(limitSwitchDown.get() && elevatorDown.get()) {
			elevator.setElevatorMotorSpeeds(0, 0);
		
		}
		else if(elevatorUp.get()) {
			elevator.setElevatorMotorSpeeds(1, 1);
		}else if(elevatorDown.get()){
			elevator.setElevatorMotorSpeeds(-1, -1);
		}else if(restPosition.get()) {
			while(!limitSwitchDown.get()) {
				elevator.setElevatorMotorSpeeds(-1, -1);
			}
		}
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void interrupted() {
		isFinished();
	}

}

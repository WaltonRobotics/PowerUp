package org.usfirst.frc.team2974.robot.subsystems;

import static org.usfirst.frc.team2974.robot.RobotMap.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2974.robot.Robot;


public class PlaneBreaker extends Subsystem{
	public PlaneBreaker(){
		initDefaultCommand();
	}
	
	protected void initDefaultCommand() {
		
	}
	
	public void moveUp() {
		planeBreaker.set(true);
	}

	public void moveDown() {
		planeBreaker.set(false);
	}
}

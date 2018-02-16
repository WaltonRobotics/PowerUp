package org.usfirst.frc.team2974.robot.subsystems;

import static org.usfirst.frc.team2974.robot.RobotMap.elevatorMotorRight;
import static org.usfirst.frc.team2974.robot.RobotMap.elevatorMotorLeft;
import static org.usfirst.frc.team2974.robot.RobotMap.limitSwitchDown;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The elevator subsystem, which raises and lowers the intake/outtake
 *
 * TODO: finish me
 */
public class Elevator extends Subsystem {

	

    @Override
    protected void initDefaultCommand() {
        // FIXME
    }
    
    
    //sets the speeds of the elevator's two motors
    public void setElevatorMotorSpeeds(double left, double right) {
    	elevatorMotorRight.set(right);
    	elevatorMotorLeft.set(left);
    }
    
}


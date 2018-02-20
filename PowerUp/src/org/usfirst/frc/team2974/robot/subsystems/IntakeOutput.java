package org.usfirst.frc.team2974.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team2974.robot.command.teleop.IntakeCommand;

import static org.usfirst.frc.team2974.robot.RobotMap.intakeMotorLeft;
import static org.usfirst.frc.team2974.robot.RobotMap.intakeMotorRight;
import static org.usfirst.frc.team2974.robot.Config.IntakeOutput.*;

/**
 * The Intake/Outtake for the power cubes.
 */
public class IntakeOutput extends Subsystem {

	private Timer timer;
	
    public IntakeOutput() {
        intakeMotorRight.setInverted(true);
        timer = new Timer();
        timer.start();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new IntakeCommand());
    }

    /**
     * Sets the motor powers.
     * @param left
     * @param right
     */
    private void setMotorPowers(double left, double right) {
        intakeMotorLeft.set(ControlMode.PercentOutput, left);
        intakeMotorRight.set(ControlMode.PercentOutput, right);
    }

    public void highIntake() {
        setMotorPowers(MAX_POWER, MAX_POWER);
    }

    public void highOutput() {
        setMotorPowers(-MAX_POWER, -MAX_POWER);
    }
    
    public void lowIntake(){
    	setMotorPowers(LOW_POWER, LOW_POWER);
    }
    
    public void hold(){	
    	setMotorPowers(HOLD_POWER, HOLD_POWER); 
    }
    
    public void off(){
    	setMotorPowers(0, 0); 
    }
    
    public boolean timeElapsed(double time){
    	return timer.hasPeriodPassed(time);
    }
    
    public boolean timeElapsed(){
    	return timer.hasPeriodPassed(2);
    }
    
    public void resetTime(){
    	timer.reset();
    }
    
}

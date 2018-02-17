package org.usfirst.frc.team2974.robot.command.teleop;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.OI.intake;
import static org.usfirst.frc.team2974.robot.OI.output;
import static org.usfirst.frc.team2974.robot.Robot.intakeOutput;
import static edu.wpi.first.wpilibj.Timer;

/**
 * This command does the intake/output function of the IntakeOutput subsystem.
 */
public class IntakeCommand extends Command {

    public IntakeCommand() {
        requires(intakeOutput);
    }
    
    public enum IntakeOutput{
    	OFF{
    		public void init(){
    			intakeOutput.off();
    		}
    		public State periodic(){
    			if(output.get()){
    				return IN;
    			}
    			return this;
    		}
    	}, 
    	IN{
    		public void init(){
    			intakeOutput.highIntake();
    			intakeOutput.resetTime();
    		}
    		public State periodic(){
    			if(intakeOutput.timeElapsed()){
    				return HOLD;
    			}
    			return this;
    		}
    	}, 
    	HOLD{
    		public void init(){
    			intakeOutput.hold();
    		}
    		public State periodic(){
    			if(output.get()){
    				return OUT;
    			}
    			return this;
    		}
    	}, 
    	OUT{
    		public void init(){
    			intakeOutput.highOutput();
    			intakeOutput.resetTime();
    		}
    		public State periodic(){
    			if(intakeOutput.timeElapsed()){
    				return OFF;
    			}
    			return this;
    		}
    	};
    	
    	public State periodic(){
    		return this;
    	}
    	public void init(){
    	}
    }
    
    State state = IntakeOutput.OFF;
    
    @Override
    protected void initialize(){
    	state = IntakeOutput.OFF;
    }

    @Override
    protected void execute() {
        
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
	protected void interrupted() {
		isFinished();
	}
}

package org.usfirst.frc.team2974.robot.command.teleop;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.usfirst.frc.team2974.robot.OI.intake;
import static org.usfirst.frc.team2974.robot.OI.output;
import static org.usfirst.frc.team2974.robot.Robot.intakeOutput;

/**
 * This command does the intake/output function of the State subsystem.
 */
public class IntakeCommand extends Command {

    public IntakeCommand() {
        requires(intakeOutput);
    }
    
    public enum State {
    	OFF {
    		public void init(){
    			intakeOutput.off();
				SmartDashboard.putString("Intake State","OFF");
    		}
    		public State periodic(){
    			if(intake.get()){
    				return IN;
    			}
    			else if(output.get()){
    				return OUT;
    			}
    			return this;
    		}
    	}, 
    	IN {
    		public void init(){
				SmartDashboard.putString("Intake State","IN");
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
    	HOLD {
    		public void init(){
    			intakeOutput.hold();
				SmartDashboard.putString("Intake State","HOLD");
    		}
    		public State periodic(){
    			if(output.get()){
    				return OUT;
    			}
    			else if(input.get()){
    				return IN;
    			}
    			return this;
    		}
    	}, 
    	OUT {
    		public void init(){
				SmartDashboard.putString("Intake State","OUT");
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
    	
    	public abstract State periodic();
    	public abstract void init();
    }

	private State state = State.OFF;
    
    @Override
    protected void initialize(){
    	state = State.OFF;
    	state.init();
    }

    @Override
    protected void execute() {
    	State newState = state.periodic();

    	if(state != newState) {
    		state = newState;
    		state.init();
		}

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
	protected void interrupted() {
		isFinished();
	}
}

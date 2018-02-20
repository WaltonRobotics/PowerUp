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
    
    public enum State {
    	OFF {
    		public void init(){
				updateState();
    			intakeOutput.off();
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
				updateState();
    			intakeOutput.highIntake();
    		}

    		public State periodic(){
    			if(!intake.get()) {
    				return OFF;
    			}
    			return this;
    		}
    	},
    	OUT {
    		public void init(){
				updateState();
    			intakeOutput.highOutput();
    		}

    		public State periodic(){
    			if(!output.get()) {
    				return OFF;
    			}
    			return this;
    		}
    	};

    	public void updateState() {
			SmartDashboard.putString("Intake State", this.name()); // this gets the name easily
		}

		public abstract void init();
    	public abstract State periodic();
    }

	private State state = State.OFF;

	public IntakeCommand() {
		requires(intakeOutput);
	}
    
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

	@Override
	protected void end() {
		intakeOutput.off();
	}

	@Override
	protected void interrupted() {
		end();
	}
}

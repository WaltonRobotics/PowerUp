package org.usfirst.frc.team2974.robot.command.teleop;

import static org.usfirst.frc.team2974.robot.OI.intake;
import static org.usfirst.frc.team2974.robot.OI.output;
import static org.usfirst.frc.team2974.robot.OI.outputHalf;
import static org.usfirst.frc.team2974.robot.Robot.intakeOutput;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command does the intake/output function of the State subsystem.
 */
public class IntakeCommand extends CommandBase {

  private State state = State.OFF;

  public IntakeCommand() {
    addRequirements(intakeOutput);
  }

  @Override
  public void initialize() {
    state = State.OFF;
    state.init();
  }

  @Override
  public void execute() {
    State newState = state.periodic();

    if (state != newState) {
      state = newState;
      state.init();
    }

  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    intakeOutput.off();
  }

  public enum State {
    OFF {
      public void init() {
        updateState();
        intakeOutput.off();
      }

      public State periodic() {
        if (intake.getAsBoolean()) {
          return IN;
        } else if (output.getAsBoolean()) {
          return OUT;
        } else if (outputHalf.getAsBoolean()) {
          return OUT_HALF;
        }
        return this;
      }
    },
    IN {
      public void init() {
        updateState();
        intakeOutput.highIntake();
      }

      public State periodic() {
        if (!intake.getAsBoolean()) {
          return OFF;
        }
        return this;
      }
    },
    OUT {
      public void init() {
        updateState();
        intakeOutput.highOutput();
      }

      public State periodic() {
        if (!output.getAsBoolean()) {
          return OFF;
        }
        return this;
      }
    },
    OUT_HALF {
      public void init() {
        updateState();
        intakeOutput.halfOutput();
      }

      public State periodic() {
        if (!outputHalf.getAsBoolean()) {
          return OFF;
        }
        return this;
      }
    };

    public void updateState() {
      SmartDashboard.putString("Intake State", name()); // this gets the name easily
    }

    public abstract void init();

    public abstract State periodic();
  }
}

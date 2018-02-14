package org.usfirst.frc.team2974.robot.command.teleop;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.OI.intake;
import static org.usfirst.frc.team2974.robot.OI.output;
import static org.usfirst.frc.team2974.robot.Robot.intakeOutput;

/**
 * This command does the intake/output function of the IntakeOutput subsystem.
 */
public class IntakeCommand extends Command {

    public IntakeCommand() {
        requires(intakeOutput);
    }

    @Override
    protected void execute() {
        if(output.get()) {
            // output
            intakeOutput.output();
        } else if(intake.get()) {
            // input
            intakeOutput.intake();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}

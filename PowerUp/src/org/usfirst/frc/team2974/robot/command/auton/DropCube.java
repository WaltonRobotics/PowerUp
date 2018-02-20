package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.Robot.intakeOutput;

/**
 *
 */
public class DropCube extends Command {

    public DropCube() {
        super();
        requires(intakeOutput);
    }

    @Override
    protected void initialize() {
        intakeOutput.resetTime();
        intakeOutput.highOutput();
    }

    @Override
    protected void end() {
        intakeOutput.off();
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return intakeOutput.timeElapsed();
    }
}

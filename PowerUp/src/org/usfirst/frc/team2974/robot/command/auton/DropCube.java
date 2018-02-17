package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.Config;

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
    public synchronized void start() {
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
        return intakeOutput.timeElasped();
    }
}

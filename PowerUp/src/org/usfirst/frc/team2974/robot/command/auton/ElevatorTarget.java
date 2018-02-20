package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.Robot.elevator;

/**
 *
 */
public class ElevatorTarget extends Command {

    private double position;

    public ElevatorTarget(double position) {
        this.position = position;

        if(!elevator.isMotionControlled())
            elevator.enableControl();

        requires(elevator);
    }

    @Override
    protected void initialize() {
        elevator.setTarget(position);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(elevator.getCurrentPosition() - position) <= 1;
    }

    @Override
    protected void end() {
//        elevator.disableControl();
    }

    @Override
    protected void interrupted() {
        end();
    }
}

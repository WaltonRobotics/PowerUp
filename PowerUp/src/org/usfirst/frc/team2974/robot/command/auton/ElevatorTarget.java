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

        requires(elevator);
    }

    @Override
    public synchronized void start() {
        elevator.setTarget(position);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(elevator.getCurrentPosition() - position) <= 1;
    }
}

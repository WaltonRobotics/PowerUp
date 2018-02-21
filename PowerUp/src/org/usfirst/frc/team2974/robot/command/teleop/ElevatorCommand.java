package org.usfirst.frc.team2974.robot.command.teleop;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team2974.robot.Config.Elevator.*;
import static org.usfirst.frc.team2974.robot.OI.*;
import static org.usfirst.frc.team2974.robot.Robot.elevator;

public class ElevatorCommand extends Command {

    public ElevatorCommand() {
        requires(elevator);
    }

    @Override
    protected void initialize() {
        elevator.enableControl();
    }

    @Override
    protected void execute() {
        if (!elevator.isMotionControlled()) {
            if(Math.abs(gamepad.getLeftY()) > .1)
                elevator.setPower(-gamepad.getLeftY());
            else if(Math.abs(gamepad.getRightY()) > .1)
                elevator.setPower(-gamepad.getRightY());
            else
                elevator.setPower(0);

        } else {
            if (elevatorNudgeUp.get() && !elevator.atTopPosition()) {
                elevator.nudge(NUDGE_DISTANCE);
            }

            if (elevatorNudgeDown.get() && !elevator.atLowerPosition()) {
                elevator.nudge(-NUDGE_DISTANCE);
            }

            if(elevatorHigh.get()) {
                elevator.setTarget(HIGH_HEIGHT);
            } else if(elevatorMedium.get()) {
                elevator.setTarget(MEDIUM_HEIGHT);
            } else if(elevatorLow.get()) {
                elevator.setTarget(LOW_HEIGHT);
            }
        }

        if(elevatorToggleControl.get()) {
            System.out.println("screeeee toggle pressed!");
            if(elevator.isMotionControlled())
                elevator.disableControl();
            else
                elevator.enableControl();
        }

        if (elevatorZero.get()) {
            elevator.startZero();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}

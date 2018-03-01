package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

import static org.usfirst.frc.team2974.robot.Config.Elevator.MEDIUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.*;

/**
 * Drives to the switch from the chosen (left(), right(), center()) starting position.
 */
public class DriveToSwitch extends CommandGroup {

    private boolean isOptionSelected;

    public DriveToSwitch() {
        super();

        isOptionSelected = false;
    }

    public DriveToSwitch left() {
        addParallel(new ElevatorTarget(MEDIUM_HEIGHT));
        addSequential(SimpleSpline.pathFromPosesWithAngle(false, L0, L4, L5));
        addSequential(new DropCube());

        isOptionSelected = true;

        return this;
    }

    public DriveToSwitch right() {
        addParallel(new ElevatorTarget(MEDIUM_HEIGHT));
        addSequential(SimpleSpline.pathFromPosesWithAngle(false, R0, R4, R5));
        addSequential(new DropCube());

        isOptionSelected = true;

        return this;
    }

    public DriveToSwitch center() {
        addParallel(new ElevatorTarget(MEDIUM_HEIGHT));
        addSequential(new CrossBaseline().center()); // :)
        addSequential(new DropCube());

        isOptionSelected = true;

        return this;
    }


    @Override
    protected void initialize() {
        super.initialize();

        if(!isOptionSelected)
            throw new RuntimeException("Left or Right was not called when the command was initialized! This is a programmer error.");
    }
}

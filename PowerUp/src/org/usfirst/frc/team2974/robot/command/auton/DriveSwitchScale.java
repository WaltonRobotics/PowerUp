package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

import static org.usfirst.frc.team2974.robot.Config.Elevator.MINUMUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.*;
import static org.usfirst.frc.team2974.robot.Config.Elevator.SCALE_MAX_HEIGHT;

/**
 * Drives to the switch,
 * puts a cube in,
 * gets another cube,
 * drives to the scale,
 * puts a cube in
 *
 */
public class DriveSwitchScale extends CommandGroup {

    private boolean isOptionSelected;

    public DriveSwitchScale() {
        super();
        isOptionSelected = false;
    }

    public DriveSwitchScale left() {
        addSequential(new DriveToSwitch().left()); // should also drop cube in
        addSequential(SimpleSpline.pathFromPosesWithAngle(true, L5, L7, L8));
        addParallel(SimpleSpline.pathFromPosesWithAngle(false, L8, L9));
        addParallel(new ElevatorTarget(MINUMUM_HEIGHT));
        addSequential(new FindCube());
        // TODO: gather cube too
        addSequential(SimpleSpline.pathFromPosesWithAngle(true, L9, L10));
        addParallel(SimpleSpline.pathFromPosesWithAngle(false, L10, L6, L2, L3));
        addParallel(new ElevatorTarget(SCALE_MAX_HEIGHT));
        addSequential(new DropCube());

        isOptionSelected = true;

        return this;
    }

    public DriveSwitchScale right() {
        addSequential(new DriveToSwitch().right()); // should also drop cube in
        addSequential(SimpleSpline.pathFromPosesWithAngle(true, R5, R7, R8));
        addParallel(SimpleSpline.pathFromPosesWithAngle(false, R8, R9));
        addParallel(new ElevatorTarget(MINUMUM_HEIGHT));
        addSequential(new FindCube());
        addSequential(SimpleSpline.pathFromPosesWithAngle(true, R9, R10));
        addParallel(SimpleSpline.pathFromPosesWithAngle(false, R10, R6, R2, R3));
        addParallel(new ElevatorTarget(SCALE_MAX_HEIGHT));
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

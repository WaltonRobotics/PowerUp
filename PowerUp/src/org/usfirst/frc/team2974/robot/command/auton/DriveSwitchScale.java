package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

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
        addSequential(SimpleSpline.pathFromPointsWithAngle(true, L5, L7, L8));
        addSequential(SimpleSpline.pathFromPointsWithAngle(false, L8, L9));
        addSequential(new FindCube());
        addSequential(SimpleSpline.pathFromPointsWithAngle(true, L9, L10));
        addSequential(SimpleSpline.pathFromPointsWithAngle(false, L10, L6, L2, L3));
        addSequential(new DropCubeScale(SCALE_MAX_HEIGHT));

        isOptionSelected = true;

        return this;
    }

    public DriveSwitchScale right() {
        addSequential(new DriveToSwitch().right()); // should also drop cube in
        addSequential(SimpleSpline.pathFromPointsWithAngle(true, R5, R7, R8));
        addSequential(SimpleSpline.pathFromPointsWithAngle(false, R8, R9));
        addSequential(new FindCube());
        addSequential(SimpleSpline.pathFromPointsWithAngle(true, R9, R10));
        addSequential(SimpleSpline.pathFromPointsWithAngle(false, R10, R6, R2, R3));
        addSequential(new DropCubeScale(SCALE_MAX_HEIGHT));

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

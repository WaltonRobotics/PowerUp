package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

import static org.usfirst.frc.team2974.robot.Config.Path.*;

/**
 * Drives to scale from the left or the right starting position.
 */
public class DriveToScale extends CommandGroup {

    private boolean isOptionSelected; // all drive commands use this, TODO: should make an abstract class for this

    public DriveToScale() {
        super();

        isOptionSelected = false;
    }

    public DriveToScale left() {
        // FIXME(?): may have to change because it might hit the wall
        addSequential(SimpleSpline.pathFromPosesWithAngle(false, L0, L1, L2));
        addSequential(SimpleSpline.pathFromPosesWithAngle(false, L2, L3));
        addSequential(new DropCube());

        isOptionSelected = true;

        return this;
    }

    public DriveToScale right() {
        addSequential(SimpleSpline.pathFromPosesWithAngle(false, R0, R1, R2, R3));
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

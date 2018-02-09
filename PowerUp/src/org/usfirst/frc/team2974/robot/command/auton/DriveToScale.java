package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.waltonrobotics.controller.Point;

import static org.usfirst.frc.team2974.robot.Config.Path.*;

/**
 * Drives to scale from the left or the right starting position.
 */
public class DriveToScale extends CommandGroup {

    public DriveToScale() {
        super();

    }

    public DriveToScale left() {
        // FIXME(?): may have to change because it might hit the wall
        addSequential(new SimpleSpline(90, 0, L0, L1, L2, L3));
        // TODO: put cube in the thing, yo

        return this;
    }

    public DriveToScale right() {
        addSequential(new SimpleSpline(90, 180, R0, R1, R2, R3));
        // TODO: put cube in the thing, yo

        return this;
    }
}

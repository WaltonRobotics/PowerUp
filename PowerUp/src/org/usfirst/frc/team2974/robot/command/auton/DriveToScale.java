package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.waltonrobotics.controller.Point;

import static org.usfirst.frc.team2974.robot.Config.Path.STARTING_POSITION_LEFT;
import static org.usfirst.frc.team2974.robot.Config.Path.STARTING_POSITION_RIGHT;

/**
 * Drives to scale from the left or the right starting position.
 */
public class DriveToScale extends CommandGroup {

    public DriveToScale() {
        super();

    }

    public DriveToScale left() {
        // FIXME(?): may have to change because it might hit the wall
        addSequential(new SimpleSpline(90, 0,
                STARTING_POSITION_LEFT,
                new Point(-2.286, 7.6111)
        ));

        return this;
    }

    public DriveToScale right() {
        addSequential(new SimpleSpline(90, 180,
                STARTING_POSITION_RIGHT,
                new Point(2.286, 7.6111)
        ));

        return this;
    }
}

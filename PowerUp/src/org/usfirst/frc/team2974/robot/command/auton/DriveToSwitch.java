package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.waltonrobotics.controller.Point;

import static org.usfirst.frc.team2974.robot.Config.Path.STARTING_POSITION_LEFT;
import static org.usfirst.frc.team2974.robot.Config.Path.STARTING_POSITION_RIGHT;

/**
 *
 */
public class DriveToSwitch extends CommandGroup {

    public DriveToSwitch() {
        super();

    }

    public DriveToSwitch left() {
        addSequential(new SimpleSpline(90, 0,
                STARTING_POSITION_LEFT,
                new Point(-1.8288, 4.2672)
        ));

        return this;
    }

    public DriveToSwitch right() {
        addSequential(new SimpleSpline(90, 180,
                STARTING_POSITION_RIGHT,
                new Point(1.8288, 4.2672)
        ));

        return this;
    }

    public DriveToSwitch center() {
        addSequential(new CrossBaselineCommand().center()); // :)

        return this;
    }
}

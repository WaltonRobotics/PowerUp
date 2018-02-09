package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.waltonrobotics.controller.Point;

import static org.usfirst.frc.team2974.robot.Config.Path.*;

/**
 * Drives to the switch from the chosen (left(), right(), center()) starting position.
 */
public class DriveToSwitch extends CommandGroup {

    public DriveToSwitch() {
        super();

    }

    public DriveToSwitch left() {
        addSequential(new SimpleSpline(90, 0, L0, L4, L5));
        // TODO: put the cube in, yo

        return this;
    }

    public DriveToSwitch right() {
        addSequential(new SimpleSpline(90, 180, R0, R4, R5));
        // TODO: put the cube in, yo

        return this;
    }

    public DriveToSwitch center() {
        addSequential(new CrossBaseline().center()); // :)
        // TODO: put the cube in, yo

        return this;
    }
}

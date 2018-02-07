package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.Robot;
import org.waltonrobotics.controller.Point;

import static org.usfirst.frc.team2974.robot.Config.Path.ACCELERATION_MAX;
import static org.usfirst.frc.team2974.robot.Config.Path.VELOCITY_MAX;

/**
 * Command for crossing the baseline.
 * Usage is: new CrossBaselineCommand().left();
 */
public class CrossBaselineCommand extends CommandGroup {

    /**
     * Follow up your constructor with a call to either
     *  left, right, or center to make this command work correctly.
     */
    public CrossBaselineCommand() {
        super();

        // add sequential later.
    }

    /**
     * Called when on the left side of the field.
     * @return this
     */
    public CrossBaselineCommand left(double yMovement) {
        return right(yMovement);
    }

    /**
     * Called when on the right side of the field.
     * @return this
     */
    public CrossBaselineCommand right(double yMovement) {
        // drive forward x meters
        addSequential(new DriveStraightByDistance(VELOCITY_MAX, ACCELERATION_MAX, yMovement));
        return this;
    }

    /**
     * Called when on the center of the field.
     * @return this
     */
    public CrossBaselineCommand center() {
        // either go left or right, depends on switch position
        if(Robot.getSwitchPosition() == 'R') {
            // go right
            addSequential(new SimpleSpline(90, 90, new Point(0, 0), new Point(1.318, 2.698)));
        } else {
            // go left
            addSequential(new SimpleSpline(90, 90, new Point(0, 0), new Point(-1.492, 2.698)));
        }

        return this; // ease of use :) <--- smiley face :)
    }
}

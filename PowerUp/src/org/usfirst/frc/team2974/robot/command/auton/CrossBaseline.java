package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.Robot;
import org.waltonrobotics.controller.Point;

import static org.usfirst.frc.team2974.robot.Config.Path.*;

/**
 * Command for crossing the baseline.
 * Usage is: new CrossBaseline().left();
 */
public class CrossBaseline extends CommandGroup {

    /**
     * Follow up your constructor with a call to either
     *  left, right, or center to make this command work correctly.
     */
    public CrossBaseline() {
        super();

        // add sequential later.
    }

    /**
     * Called when on the left side of the field.
     * @return this
     */
    public CrossBaseline left(double yMovement) {
        return right(yMovement);
    }

    /**
     * Called when on the right side of the field.
     * @return this
     */
    public CrossBaseline right(double yMovement) {
        // drive forward x meters
        addSequential(new DriveStraightByDistance(VELOCITY_MAX, ACCELERATION_MAX, yMovement));
        return this;
    }

    /**
     * Called when on the center of the field.
     * @return this
     */
    public CrossBaseline center() {
        // either go left or right, depends on switch position
        if(Robot.getSwitchPosition() == 'R') {
            // go right
            addSequential(SimpleSpline.pathFromPointsWithAngle(false, C0, C1));
        } else {
            // go left
            addSequential(SimpleSpline.pathFromPointsWithAngle(false, C0, C2));
        }

        return this; // ease of use :) <--- smiley face :)
    }
}
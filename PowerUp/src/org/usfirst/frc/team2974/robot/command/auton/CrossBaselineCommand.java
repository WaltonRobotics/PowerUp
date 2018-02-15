package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Path.MIDDLE_Y_SWITCH;

import org.waltonrobotics.controller.Pose;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command for crossing the baseline.
 * Usage is: new CrossBaselineCommand().leftSide();
 */
public class CrossBaselineCommand extends CommandGroup {

    /**
     * Follow up your constructor with a call to either
     *  leftSide, rightSide, or center to make this command work correctly.
     */
    public CrossBaselineCommand() {
        super();

        // add sequential later.
    }

    /**
     * Called when on the left side of the field.
     * @return this
     */
    public CrossBaselineCommand leftSide() {
        return rightSide();
    }

    /**
     * Called when on the right side of the field.
     * @return this
     */
    public CrossBaselineCommand rightSide() {
        // drive forward x meters
        addSequential(new SimpleSpline(90, 90, new Pose(0, 0), new Pose(0, MIDDLE_Y_SWITCH))); // 5 -> change to whatever
        return this;
    }

    /**
     * Called when on the center of the field.
     * @return this
     */
    public CrossBaselineCommand center() {
        // either go left or right, depends on switch position
        if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
            // go right
            addSequential(new SimpleSpline(90, 90, new Pose(0, 0), new Pose(1.318, 2.698)));
        } else {
            // go left
            addSequential(new SimpleSpline(90, 90, new Pose(0, 0), new Pose(-1.492, 2.698)));
        }

        return this; // ease of use :)
    }
}

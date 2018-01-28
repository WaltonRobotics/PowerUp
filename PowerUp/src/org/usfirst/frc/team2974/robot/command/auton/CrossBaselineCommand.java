package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command for crossing the baseline.
 * Usage is: new CrossBaselineCommand().leftSide();
 */
public class CrossBaselineCommand extends CommandGroup {

    public CrossBaselineCommand() {
        super();

        // drive forward x meters if in center
    }

    /**
     * Called when on the left side of the field.
     * @return this
     */
    public CrossBaselineCommand leftSide() {
        // TODO: do addSequential();

        return this;
    }

    /**
     * Called when on the right side of the field.
     * @return this
     */
    public CrossBaselineCommand rightSide() {

        return this;
    }

    /**
     * Called when on the center of the field.
     * @return this
     */
    public CrossBaselineCommand center() {

        return this;
    }
}

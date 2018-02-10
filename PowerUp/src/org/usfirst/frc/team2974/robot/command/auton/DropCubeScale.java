package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.Config;

/**
 *
 */
public class DropCubeScale extends CommandGroup {

    private double scaleHeight;

    public DropCubeScale() {
        this(Config.Elevator.SCALE_INITIAL_HEIGHT);
    }

    public DropCubeScale(double scaleHeight) {
        this.scaleHeight = scaleHeight;

        // TODO: addSequential stuff
    }
}

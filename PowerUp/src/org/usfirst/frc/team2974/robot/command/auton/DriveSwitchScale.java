package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

import static org.usfirst.frc.team2974.robot.Config.Path.*;

/**
 * Drives to the switch,
 * puts a cube in,
 * gets another cube,
 * drives to the scale,
 * puts a cube in
 *
 * TODO: finish this
 */
public class DriveSwitchScale extends CommandGroup {

    public DriveSwitchScale() {
        super();

    }

    public DriveSwitchScale left() {
        addSequential(new DriveToSwitch().left()); // should also drop cube in
        addSequential(new SimpleSpline(0, 270, true, L5, L7, L8));
        addSequential(new SimpleSpline(270, 270, L8, L9));
        addSequential(new FindCube()); // TODO: get cube
        addSequential(new SimpleSpline(270, 0, true, L9, L10));
        addSequential(new SimpleSpline(0, 90, L10, L6, L2, L3));
        // TODO: drop cube in high

        return this;
    }

    public DriveSwitchScale right() {


        return this;
    }
}

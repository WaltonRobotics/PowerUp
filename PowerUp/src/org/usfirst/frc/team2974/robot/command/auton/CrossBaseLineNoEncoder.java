package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

import static org.usfirst.frc.team2974.robot.Config.Path.*;

public class CrossBaseLineNoEncoder extends CommandGroup {
	public double finalTime = 2; // TODO fix this

	public CrossBaseLineNoEncoder left() {
		addSequential(new DriveStraightByDistance(1, 1, CROSS_BASELINE_Y));
		return this;
	}

	public CrossBaseLineNoEncoder center() {
//		addSequential(new DriveStraightByDistance(1, 1, 3.048));
		// TODO: do this
		return this;
	}

	public CrossBaseLineNoEncoder right() {
		addSequential(new DriveStraightByDistance(1, 1, CROSS_BASELINE_Y));
		return this;
	}
}

// distance to middle of switch = 4.2672 from the sides
// distance to crossbaseline for center = 3.048

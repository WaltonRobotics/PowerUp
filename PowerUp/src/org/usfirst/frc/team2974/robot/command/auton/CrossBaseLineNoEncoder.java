package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossBaseLineNoEncoder extends CommandGroup {
	public double finalTime = 2; // TODO fix this

	public CrossBaseLineNoEncoder left() {
		addSequential(new DriveStraightByDistance(1, 1, 4.2672));
		addSequential(new PointTurn("left", finalTime));
		return this;
	}

	public CrossBaseLineNoEncoder center() {
		addSequential(new DriveStraightByDistance(1, 1, 3.048));
		return this;
	}

	public CrossBaseLineNoEncoder right() {
		addSequential(new DriveStraightByDistance(1, 1, 4.2672));
		addSequential(new PointTurn("right", finalTime));
		return this;
	}
}

// distance to middle of switch = 4.2672 from the sides
// distance to crossbaseline for center = 3.048
package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Elevator.HIGH_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.L0;
import static org.usfirst.frc.team2974.robot.Config.Path.L1;
import static org.usfirst.frc.team2974.robot.Config.Path.L2;
import static org.usfirst.frc.team2974.robot.Config.Path.L3;
import static org.usfirst.frc.team2974.robot.Config.Path.R0;
import static org.usfirst.frc.team2974.robot.Config.Path.R1;
import static org.usfirst.frc.team2974.robot.Config.Path.R2;
import static org.usfirst.frc.team2974.robot.Config.Path.R3;

import org.usfirst.frc.team2974.robot.util.AutonUtil;

/**
 * Drives to scale from the left or the right starting position.
 */
public class DriveToScale extends AutonOption {


	public DriveToScale left() {
		return AutonUtil.driveToSinglePoint(this,
			HIGH_HEIGHT, L0, L1, L2, L3);
	}

	@Override
	public AutonOption center() {
		return this;
	}

	public DriveToScale right() {
		return AutonUtil.driveToSinglePoint(this,
			HIGH_HEIGHT, R0, R1, R2, R3);
	}
}

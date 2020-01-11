package org.usfirst.frc.team2974.robot.command.auton;

/**
 * Finds the power cube and positions itself so it is in front of it.
 *
 * NOTE: this class will probably never be used.
 */
public class FindCube extends AutonOption {

	public FindCube right() {
		return this;
	}

	public FindCube left() {
		return this;
	}

	@Override
	public AutonOption center() {
		return this;
	}
}



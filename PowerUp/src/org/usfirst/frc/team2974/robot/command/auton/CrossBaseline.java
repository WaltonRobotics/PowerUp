package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Path.ACCELERATION_MAX;
import static org.usfirst.frc.team2974.robot.Config.Path.C0;
import static org.usfirst.frc.team2974.robot.Config.Path.C1;
import static org.usfirst.frc.team2974.robot.Config.Path.C2;
import static org.usfirst.frc.team2974.robot.Config.Path.VELOCITY_MAX;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.Robot;
import org.waltonrobotics.command.SimpleSpline;
import org.waltonrobotics.controller.Pose;

/**
 * Command for crossing the baseline. Usage is: new CrossBaseline().left();
 */
public class CrossBaseline extends CommandGroup {

	private boolean isOptionSelected;

	/**
	 * Follow up your constructor with a call to either left, right, or center to make this command work correctly.
	 */
	public CrossBaseline() {
		isOptionSelected = false;

		// add sequential later.
	}

	/**
	 * Called when on the left side of the field.
	 *
	 * @return this
	 */
	public CrossBaseline left(double yMovement) {
		return right(yMovement);
	}

	/**
	 * Called when on the right side of the field.
	 *
	 * @return this
	 */
	public CrossBaseline right(double yMovement) {
		isOptionSelected = true;

		// drive forward x meters

		addSequential(SimpleSpline
			.pathFromPosesWithAngle(VELOCITY_MAX, ACCELERATION_MAX, false,
				new Pose(0, 0, StrictMath.toRadians(90)),
				new Pose(0, yMovement, StrictMath.toRadians(90))));
		return this;
	}

	/**
	 * Called when on the center of the field.
	 *
	 * @return this
	 */
	public CrossBaseline center() {
		isOptionSelected = true;

		// either go left or right, depends on switch position
		if (Robot.getSwitchPosition() == 'R') {
			// go right
			addSequential(SimpleSpline.pathFromPosesWithAngle(false, C0, C1));
		} else {
			// go left
			addSequential(SimpleSpline.pathFromPosesWithAngle(false, C0, C2));
		}

		return this; // ease of use :) <--- smiley face :)
	}

	@Override
	protected void initialize() {
		super.initialize();

		if (!isOptionSelected) {
			throw new RuntimeException(
				"Left or Right was not called when the command was initialized! This is a programmer error.");
		}
	}
}

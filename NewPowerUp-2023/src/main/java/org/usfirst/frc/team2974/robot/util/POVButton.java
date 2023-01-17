package org.usfirst.frc.team2974.robot.util;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import org.usfirst.frc.team2974.robot.Gamepad;

/**
 * Used with the POV buttons on the gamepad.
 */
public class POVButton extends Trigger {

	private final Gamepad gamepad;
	private final int angle;

	public POVButton(Gamepad gamepad, int angle) {
		this.gamepad = gamepad;
		this.angle = angle;
	}

	@Override
	public boolean getAsBoolean() {
		return gamepad.getPOV() == angle;
	}
}

package org.usfirst.frc.team2974.robot.util;

import edu.wpi.first.wpilibj2.command.button.Button;
import org.usfirst.frc.team2974.robot.Gamepad;

/**
 * Used with the POV buttons on the gamepad.
 */
public class POVButton extends Button {

	private final Gamepad gamepad;
	private final int angle;

	public POVButton(Gamepad gamepad, int angle) {
		this.gamepad = gamepad;
		this.angle = angle;
	}

	@Override
	public boolean get() {
		return gamepad.getPOV() == angle;
	}
}

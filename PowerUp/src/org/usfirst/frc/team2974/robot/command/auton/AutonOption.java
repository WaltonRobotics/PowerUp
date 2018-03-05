package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class AutonOption extends CommandGroup {

	private boolean isOptionSelected;

	public AutonOption() {

		isOptionSelected = false;
	}

	public abstract AutonOption right();

	public abstract AutonOption left();

	public abstract AutonOption center();

	@Override
	protected final void initialize() {
		super.initialize();

		if (!isOptionSelected) {
			throw new RuntimeException(
				"Left or Right was not called when the command was initialized! This is a programmer error.");
		}
	}

	public void setOptionSelected(boolean optionSelected) {
		isOptionSelected = optionSelected;
	}
}
